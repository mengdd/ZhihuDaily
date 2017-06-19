package com.ddmeng.zhihudaily.data;

import com.ddmeng.zhihudaily.data.models.display.DisplayStories;

import io.reactivex.Observable;

public interface StoriesDataSource {

    Observable<DisplayStories> getLatestNews();

    void saveNews(DisplayStories displayStories);
}
