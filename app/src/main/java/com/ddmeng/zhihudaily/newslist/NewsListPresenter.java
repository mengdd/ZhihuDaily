package com.ddmeng.zhihudaily.newslist;

import com.ddmeng.zhihudaily.data.models.DailyNews;
import com.ddmeng.zhihudaily.data.remote.ServiceGenerator;
import com.ddmeng.zhihudaily.data.remote.ZhihuService;
import com.ddmeng.zhihudaily.utils.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewsListContract.Presenter {
    public static final String TAG = "NewsListPresenter";
    private NewsListContract.View view;

    public void fetchLatestNews() {
        ZhihuService service = ServiceGenerator.createService(ZhihuService.class);
        service.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DailyNews>() {
                    @Override
                    public void onNext(@NonNull DailyNews dailyNews) {
                        LogUtils.d(TAG, "onNext: " + dailyNews.getDate());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.e(TAG, "onError", e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete");
                    }
                });
    }

    @Override
    public void attachView(NewsListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
