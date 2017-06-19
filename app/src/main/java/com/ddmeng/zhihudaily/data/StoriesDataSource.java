package com.ddmeng.zhihudaily.data;

import com.ddmeng.zhihudaily.data.models.display.DisplayStories;

import io.reactivex.Observable;

public interface StoriesDataSource {

    Observable<DisplayStories> getLatestNews();

    Observable<DisplayStories> getNewsForDate(String date);

    void saveNews(DisplayStories displayStories);
}
