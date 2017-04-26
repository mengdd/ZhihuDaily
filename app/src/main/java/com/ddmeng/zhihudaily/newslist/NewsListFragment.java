package com.ddmeng.zhihudaily.newslist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.DailyNews;
import com.ddmeng.zhihudaily.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListFragment extends Fragment implements NewsListContract.View {

    public static final String TAG = "NewsListFragment";

    @BindView(R.id.news_list)
    RecyclerView newsList;


    private NewsListContract.Presenter presenter;
    private NewsListAdapter newsListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter = new NewsListPresenter();
        presenter.attachView(this);
        presenter.init();
        presenter.fetchLatestNews();
    }

    @Override
    public void initViews() {
        newsListAdapter = new NewsListAdapter();
        newsList.setAdapter(newsListAdapter);
        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
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
}
