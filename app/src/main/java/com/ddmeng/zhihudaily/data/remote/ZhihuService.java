package com.ddmeng.zhihudaily.data.remote;

import com.ddmeng.zhihudaily.data.models.DailyNews;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ZhihuService {

    @GET("news/latest")
    Observable<DailyNews> getLatestNews();
}
