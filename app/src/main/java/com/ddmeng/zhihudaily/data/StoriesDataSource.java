package com.ddmeng.zhihudaily.data;

import com.ddmeng.zhihudaily.data.models.display.DisplayStories;

import io.reactivex.Observable;

public interface StoriesDataSource {

    Observable<DisplayStories> getNews();

    void saveNews(DisplayStories displayStories);
}
