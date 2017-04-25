package com.ddmeng.zhihudaily.newslist;

import com.ddmeng.zhihudaily.base.BasePresenter;
import com.ddmeng.zhihudaily.base.BaseView;

public interface NewsListContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<NewsListContract.View> {
    }
}
