package com.ddmeng.zhihudaily.data;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.utils.DateUtils;
import com.ddmeng.zhihudaily.utils.LogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class StoriesRepository implements StoriesDataSource {


    private static final String TAG = "StoriesRepository";
    private StoriesDataSource remoteDataSource;
    private StoriesDataSource localDataSource;

    @Nullable
    private Map<String, DisplayStories> memoryCachedDisplayStories;


    private boolean isMemoryCacheDirty = false;

    public StoriesRepository(StoriesDataSource remoteDataSource, StoriesDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Observable<DisplayStories> getLatestNews() {
        String currentDataString = DateUtils.getCurrentDataString();
        return getNewsForDate(currentDataString);
    }

    @Override
    public Observable<DisplayStories> getNewsForDate(String date) {
        if (memoryCachedDisplayStories != null &&
                memoryCachedDisplayStories.get(date) != null &&
                !isMemoryCacheDirty) {
            return Observable.just(memoryCachedDisplayStories.get(date));
        }

        Observable<DisplayStories> remoteNews = getAndSaveRemoteNews(date);

        if (isMemoryCacheDirty) {
            return remoteNews;
        } else {
            // Query the local storage if available. If not, query the network.
            Observable<DisplayStories> localNews = getAndCacheLocalNews(date);
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
        saveToMemoryCache(displayStories);

    }

    public void refreshNews() {
        isMemoryCacheDirty = true;
    }

    private Observable<DisplayStories> getAndSaveRemoteNews(String date) {
        Observable<DisplayStories> getNewsObservable;
        if (TextUtils.isEmpty(date) || DateUtils.getCurrentDataString().equals(date)) {
            getNewsObservable = remoteDataSource.getLatestNews();
        } else {
            getNewsObservable = remoteDataSource.getNewsForDate(date);
        }

        return getNewsObservable
                .compose(saveToLocal())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        isMemoryCacheDirty = false;
                    }
                });
    }

    private Observable<DisplayStories> getAndCacheLocalNews(String date) {
        Observable<DisplayStories> getNewsObservable;
        if (TextUtils.isEmpty(date) || DateUtils.getCurrentDataString().equals(date)) {
            getNewsObservable = localDataSource.getLatestNews();
        } else {
            getNewsObservable = localDataSource.getNewsForDate(date);
        }
        return getNewsObservable
                .compose(saveToMemory());
    }

    private ObservableTransformer<DisplayStories, DisplayStories> saveToLocal() {
        return new ObservableTransformer<DisplayStories, DisplayStories>() {
            @Override
            public ObservableSource<DisplayStories> apply(@NonNull Observable<DisplayStories> upstream) {
                return upstream.map(new Function<DisplayStories, DisplayStories>() {
                    @Override
                    public DisplayStories apply(@NonNull DisplayStories displayStories) throws Exception {
                        LogUtils.d(TAG, "save remote news to local");
                        localDataSource.saveNews(displayStories);
                        saveToMemoryCache(displayStories);
                        return displayStories;
                    }
                });
            }
        };
    }

    private ObservableTransformer<DisplayStories, DisplayStories> saveToMemory() {
        return new ObservableTransformer<DisplayStories, DisplayStories>() {
            @Override
            public ObservableSource<DisplayStories> apply(@NonNull Observable<DisplayStories> upstream) {
                return upstream.map(new Function<DisplayStories, DisplayStories>() {
                    @Override
                    public DisplayStories apply(@NonNull DisplayStories displayStories) throws Exception {
                        LogUtils.d(TAG, "cache local news: " + displayStories);
                        saveToMemoryCache(displayStories);
                        return displayStories;
                    }
                });
            }
        };
    }

    private void saveToMemoryCache(DisplayStories displayStories) {
        if (memoryCachedDisplayStories == null) {
            memoryCachedDisplayStories = new LinkedHashMap<>();
        }
        memoryCachedDisplayStories.put(displayStories.getDate(), displayStories);
    }
}
