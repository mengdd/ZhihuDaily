package com.ddmeng.zhihudaily.data.remote;

import com.ddmeng.zhihudaily.data.models.response.DailyNews;
import com.ddmeng.zhihudaily.data.models.response.StoryDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuService {

    @GET("news/latest")
    Observable<DailyNews> getLatestNews();

    @GET("news/{id}")
    Observable<StoryDetail> getNewsDetail(@Path("id") String id);
}
