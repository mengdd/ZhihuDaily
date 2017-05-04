package com.ddmeng.zhihudaily.newsdetail;

import com.ddmeng.zhihudaily.data.models.StoryDetail;
import com.ddmeng.zhihudaily.data.remote.ServiceGenerator;
import com.ddmeng.zhihudaily.data.remote.ZhihuService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailPresenter implements NewsDetailContract.Presenter {
    private NewsDetailContract.View view;
    private String id;
    private String shareUrl;

    public NewsDetailPresenter(String id) {
        this.id = id;
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
        ZhihuService service = ServiceGenerator.createService(ZhihuService.class);
        service.getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StoryDetail>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull StoryDetail storyDetail) {
                        shareUrl = storyDetail.getShareUrl();
                        if (view != null) {
                            view.setNewsDetail(storyDetail);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public String getShareUrl() {
        return shareUrl;
    }
}
