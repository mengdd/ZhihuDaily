package com.ddmeng.zhihudaily.newsdetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.ZhihuDailyApplication;
import com.ddmeng.zhihudaily.data.models.StoryDetail;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsDetailFragment extends Fragment implements NewsDetailContract.View {
    public static final String TAG = "NewsDetailFragment";

    private static final String ARG_ID = "arg_id";
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_top_image)
    ImageView topImageView;
    @BindView(R.id.detail_title)
    TextView titleView;
    @BindView(R.id.detail_body)
    TextView bodyView;

    private NewsDetailContract.Presenter presenter;
    private ImageLoader imageLoader;

    public static NewsDetailFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoaderFactory imageLoaderFactory = ((ZhihuDailyApplication) getActivity()
                .getApplication()).getMainComponent().getImageLoaderFactory();
        imageLoader = imageLoaderFactory.createImageLoader(this);
        Bundle args = getArguments();
        String id = args.getString(ARG_ID);
        presenter = new NewsDetailPresenter(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        presenter.attachView(this);
        presenter.init();
        presenter.fetchNewsDetail();

    }

    @Override
    public void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    @Override
    public void setNewsDetail(StoryDetail storyDetail) {
        imageLoader.load(storyDetail.getImage(), topImageView);
        titleView.setText(storyDetail.getTitle());
        bodyView.setText(storyDetail.getBody());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }
}
