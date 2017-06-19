package com.ddmeng.zhihudaily.data.remote;

import com.ddmeng.zhihudaily.data.StoriesDataSource;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.data.models.response.DailyNews;
import com.ddmeng.zhihudaily.data.models.transform.DailyNewsConverter;
import com.ddmeng.zhihudaily.utils.LogUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class StoriesRemoteDataSource implements StoriesDataSource {
    public static final String TAG = "RemoteDataSource";
    private DailyNewsConverter converter;
    private ZhihuService zhihuService;

    @Inject
    public StoriesRemoteDataSource(DailyNewsConverter converter, ZhihuService zhihuService) {
        this.converter = converter;
        this.zhihuService = zhihuService;
    }

    @Override
    public Observable<DisplayStories> getLatestNews() {
        return zhihuService.getLatestNews()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<DailyNews, ObservableSource<DisplayStories>>() {
                    @Override
                    public ObservableSource<DisplayStories> apply(@NonNull DailyNews dailyNews) throws Exception {
                        LogUtils.i(TAG, "get latest news from remote");
                        return Observable.just(converter.getNews(dailyNews));
                    }
                });

    }

    @Override
    public void saveNews(DisplayStories displayStories) {
    }
}
