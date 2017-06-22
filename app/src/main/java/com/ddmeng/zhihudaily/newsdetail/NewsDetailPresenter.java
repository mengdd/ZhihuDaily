package com.ddmeng.zhihudaily.newsdetail;

import com.ddmeng.zhihudaily.data.StoriesRepository;
import com.ddmeng.zhihudaily.data.models.db.StoryDetail;
import com.ddmeng.zhihudaily.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailPresenter implements NewsDetailContract.Presenter {
    private static final String TAG = "NewsDetailPresenter";
    private NewsDetailContract.View view;
    private StoriesRepository storiesRepository;
    private String id;
    private String shareUrl;

    public NewsDetailPresenter(String id, StoriesRepository storiesRepository) {
        this.id = id;
        this.storiesRepository = storiesRepository;
    }

    @Override
    public void attachView(NewsDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void init() {
        view.initViews();
    }

    @Override
    public void fetchNewsDetail() {
        storiesRepository.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull StoryDetail storyDetail) {
                        LogUtils.d(TAG, "onNext: " + storyDetail.getId());
                        shareUrl = storyDetail.getShareUrl();
                        if (view != null) {
                            view.setNewsDetail(storyDetail);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.e(TAG, "onError: " + e);

                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(TAG, "onComplete");
                    }
                });

    }

    @Override
    public String getShareUrl() {
        return shareUrl;
    }
}
