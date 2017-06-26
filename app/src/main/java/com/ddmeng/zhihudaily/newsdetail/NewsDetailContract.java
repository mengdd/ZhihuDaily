package com.ddmeng.zhihudaily.newsdetail;

import com.ddmeng.zhihudaily.base.BasePresenter;
import com.ddmeng.zhihudaily.base.BaseView;
import com.ddmeng.zhihudaily.data.models.db.StoryDetail;

public interface NewsDetailContract {

    interface View extends BaseView {
        void initViews();

        void setNewsDetail(StoryDetail storyDetail);
    }

    interface Presenter extends BasePresenter<NewsDetailContract.View> {
        void init();

        void fetchNewsDetail();

        String getShareUrl();

        void dispose();
    }
}
