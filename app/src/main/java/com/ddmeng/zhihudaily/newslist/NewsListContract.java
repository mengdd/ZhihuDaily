package com.ddmeng.zhihudaily.newslist;

import com.ddmeng.zhihudaily.base.BasePresenter;
import com.ddmeng.zhihudaily.base.BaseView;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;

public interface NewsListContract {
    interface View extends BaseView {
        void initViews();

        void setDisplayStories(DisplayStories displayStories);

        void appendDisplayStories(DisplayStories displayStories);

        void hideLoading();
    }

    interface Presenter extends BasePresenter<NewsListContract.View> {
        void init();

        void fetchLatestNews();

        void onRefresh();

        void onLoadMore(int page);
    }
}
