package com.ddmeng.zhihudaily.data.local;

import com.ddmeng.zhihudaily.data.StoriesDataSource;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;

import javax.inject.Inject;

import io.reactivex.Observable;

public class StoriesLocalDataSource implements StoriesDataSource {
    @Inject
    public StoriesLocalDataSource() {
    }

    @Override
    public Observable<DisplayStories> getNews() {
        //TODO
        return Observable.just(new DisplayStories());
    }
}
