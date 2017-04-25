package com.ddmeng.zhihudaily.newslist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.zhihudaily.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListFragment extends Fragment implements NewsListContract.View {

    public static final String TAG = "NewsListFragment";

    @BindView(R.id.news_list)
    RecyclerView newsList;


    private NewsListPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter = new NewsListPresenter();
        presenter.attachView(this);
        presenter.fetchLatestNews();
    }
}
