package com.ddmeng.zhihudaily.newslist;

import com.ddmeng.zhihudaily.data.StoriesRepository;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.utils.DateUtils;
import com.ddmeng.zhihudaily.utils.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsListPresenter implements NewsListContract.Presenter {
    public static final String TAG = "NewsListPresenter";
    private NewsListContract.View view;

    private StoriesRepository storiesRepository;

    public NewsListPresenter(StoriesRepository storiesRepository) {
        this.storiesRepository = storiesRepository;
    }

    @Override
    public void init() {
        if (view != null) {
            view.initViews();
        }
    }

    @Override
    public void fetchLatestNews() {
        storiesRepository.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.d(TAG, "doAfterTerminate");
                        if (view != null) {
                            view.hideLoading();
                        }
                    }
                })
                .subscribe(new DisposableObserver<DisplayStories>() {
                    @Override
                    public void onNext(@NonNull DisplayStories displayStories) {
                        LogUtils.d(TAG, "onNext: " + displayStories);
                        if (view != null) {
                            view.setDisplayStories(displayStories);
                        }
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
    public void onRefresh() {
        storiesRepository.refreshNews();
        fetchLatestNews();
    }

    @Override
    public void onLoadMore(int page) {
        String dateBefore = DateUtils.getDateStringMinusDays(page);
        storiesRepository.getNewsForDate(dateBefore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DisplayStories>() {
                    @Override
                    public void onNext(@NonNull DisplayStories displayStories) {
                        LogUtils.d(TAG, "onNext for load more: " + displayStories);
                        if (view != null) {
                            view.appendDisplayStories(displayStories);
                        }
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
