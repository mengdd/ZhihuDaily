package com.ddmeng.zhihudaily.newslist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.zhihudaily.MainActivity;
import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.ZhihuDailyApplication;
import com.ddmeng.zhihudaily.data.models.DailyNews;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.newsdetail.NewsDetailFragment;
import com.ddmeng.zhihudaily.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListFragment extends Fragment implements NewsListContract.View, NewsListAdapter.Callback {

    public static final String TAG = "NewsListFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.news_list)
    RecyclerView newsList;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private NewsListContract.Presenter presenter;
    private NewsListAdapter newsListAdapter;
    private ImageLoader imageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        ImageLoaderFactory imageLoaderFactory = ((ZhihuDailyApplication) getActivity()
                .getApplication()).getMainComponent().getImageLoaderFactory();
        imageLoader = imageLoaderFactory.createImageLoader(this);
        presenter = new NewsListPresenter();
        presenter.attachView(this);
        presenter.init();
        presenter.fetchLatestNews();
    }

    @Override
    public void initViews() {
        toolbar.setTitle(R.string.home);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new NewsListAdapter(imageLoader, this);
        newsList.setAdapter(newsListAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setDailyNews(DailyNews dailyNews) {
        LogUtils.i(TAG, "setDailyNews: " + dailyNews.getStories().size());
        newsListAdapter.setDailyNews(dailyNews);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onStoryClicked(String id) {
        ((MainActivity) getActivity()).replaceFragment(NewsDetailFragment.newInstance(id), NewsDetailFragment.TAG);
    }
}
