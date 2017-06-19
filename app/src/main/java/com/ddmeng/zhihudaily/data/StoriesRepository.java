package com.ddmeng.zhihudaily.data;

import android.support.annotation.Nullable;

import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class StoriesRepository implements StoriesDataSource {


    private static final String TAG = "StoriesRepository";
    private StoriesDataSource remoteDataSource;
    private StoriesDataSource localDataSource;

    @Nullable
    DisplayStories memoryCachedDisplayStories;


    boolean isMemoryCacheDirty = false;

    public StoriesRepository(StoriesDataSource remoteDataSource, StoriesDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Observable<DisplayStories> getLatestNews() {
        // Respond immediately with cache if available and not dirty
        if (memoryCachedDisplayStories != null && !isMemoryCacheDirty) {
            return Observable.just(memoryCachedDisplayStories);
        }

        Observable<DisplayStories> remoteNews = getAndSaveRemoteNews();

        if (isMemoryCacheDirty) {
            return remoteNews;
        } else {
            // Query the local storage if available. If not, query the network.
            Observable<DisplayStories> localNews = getAndCacheLocalNews();
            return Observable.concat(localNews, remoteNews)
                    .filter(new Predicate<DisplayStories>() {
                        @Override
                        public boolean test(@NonNull DisplayStories displayStories) throws Exception {
                            return displayStories.getListStories() != null &&
                                    !displayStories.getListStories().isEmpty();
                        }
                    })
                    .firstElement()
                    .toObservable();
        }
    }

    @Override
    public void saveNews(DisplayStories displayStories) {
        remoteDataSource.saveNews(displayStories);
        localDataSource.saveNews(displayStories);
        memoryCachedDisplayStories = displayStories;
    }

    public void refreshNews() {
        isMemoryCacheDirty = true;
    }

    private Observable<DisplayStories> getAndSaveRemoteNews() {
        return remoteDataSource.getLatestNews().map(new Function<DisplayStories, DisplayStories>() {
            @Override
            public DisplayStories apply(@NonNull DisplayStories displayStories) throws Exception {
                LogUtils.d(TAG, "save remote news");
                localDataSource.saveNews(displayStories);
                memoryCachedDisplayStories = displayStories;
                return displayStories;
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                isMemoryCacheDirty = false;
            }
        });
    }

    private Observable<DisplayStories> getAndCacheLocalNews() {
        return localDataSource.getLatestNews().map(new Function<DisplayStories, DisplayStories>() {
            @Override
            public DisplayStories apply(@NonNull DisplayStories displayStories) throws Exception {
                LogUtils.d(TAG, "cache local news: " + displayStories);
                memoryCachedDisplayStories = displayStories;
                return displayStories;
            }
        });
    }

}
