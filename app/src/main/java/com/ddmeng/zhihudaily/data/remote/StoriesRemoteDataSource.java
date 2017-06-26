package com.ddmeng.zhihudaily.data.remote;

import com.ddmeng.zhihudaily.data.StoriesDataSource;
import com.ddmeng.zhihudaily.data.models.db.StoryDetail;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.data.models.response.DailyNews;
import com.ddmeng.zhihudaily.data.models.transform.DailyNewsConverter;
import com.ddmeng.zhihudaily.data.models.transform.StoryDetailConverter;
import com.ddmeng.zhihudaily.utils.DateUtils;
import com.ddmeng.zhihudaily.utils.LogUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class StoriesRemoteDataSource implements StoriesDataSource {
    public static final String TAG = "RemoteDataSource";
    private DailyNewsConverter newsConverter;
    private StoryDetailConverter detailConverter;
    private ZhihuService zhihuService;

    @Inject
    public StoriesRemoteDataSource(ZhihuService zhihuService, DailyNewsConverter newsConverter,
                                   StoryDetailConverter storyDetailConverter) {
        this.zhihuService = zhihuService;
        this.newsConverter = newsConverter;
        this.detailConverter = storyDetailConverter;
    }

    @Override
    public Observable<DisplayStories> getLatestNews() {
        return zhihuService.getLatestNews()
                .subscribeOn(Schedulers.io())
                .compose(convertToDisplayStories());

    }

    @Override
    public Observable<DisplayStories> getNewsForDate(final String date) {
        String nextDate = DateUtils.getDateStringPlusDays(date, 1);
        LogUtils.i(TAG, "getNewsForDate: " + date + ", actual request date: " + nextDate);
        return zhihuService.getNewsBefore(nextDate)
                .subscribeOn(Schedulers.io())
                .compose(convertToDisplayStories());
    }

    @Override
    public Observable<StoryDetail> getNewsDetail(String id) {
        return zhihuService.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .compose(convertToStoryDetail());
    }

    @Override
    public void saveNews(DisplayStories displayStories) {
    }

    @Override
    public void saveDetail(StoryDetail detail) {
    }

    private ObservableTransformer<DailyNews, DisplayStories> convertToDisplayStories() {
        return new ObservableTransformer<DailyNews, DisplayStories>() {
            @Override
            public ObservableSource<DisplayStories> apply(@NonNull Observable<DailyNews> upstream) {
                return upstream.map(new Function<DailyNews, DisplayStories>() {
                    @Override
                    public DisplayStories apply(@NonNull DailyNews dailyNews) throws Exception {
                        LogUtils.i(TAG, "get news from remote: date: " + dailyNews.getDate());
                        return newsConverter.getNews(dailyNews);
                    }
                });
            }
        };
    }


    private ObservableTransformer<com.ddmeng.zhihudaily.data.models.response.StoryDetail, StoryDetail> convertToStoryDetail() {
        return new ObservableTransformer<com.ddmeng.zhihudaily.data.models.response.StoryDetail, StoryDetail>() {
            @Override
            public ObservableSource<StoryDetail> apply(@NonNull Observable<com.ddmeng.zhihudaily.data.models.response.StoryDetail> upstream) {
                return upstream.map(new Function<com.ddmeng.zhihudaily.data.models.response.StoryDetail, StoryDetail>() {
                    @Override
                    public StoryDetail apply(@NonNull com.ddmeng.zhihudaily.data.models.response.StoryDetail storyDetail) throws Exception {
                        LogUtils.i(TAG, "get detail from remote: " + storyDetail.getId());
                        return detailConverter.convertStoryDetail(storyDetail);
                    }
                });
            }
        };
    }
}
