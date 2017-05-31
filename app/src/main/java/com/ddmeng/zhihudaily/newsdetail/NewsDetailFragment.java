package com.ddmeng.zhihudaily.newsdetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.ZhihuDailyApplication;
import com.ddmeng.zhihudaily.data.models.response.StoryDetail;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.imageloader.ImageLoaderFactory;
import com.ddmeng.zhihudaily.utils.LogUtils;
import com.ddmeng.zhihudaily.utils.WebUtils;

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
    @BindView(R.id.web_view)
    WebView webView;

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
        initToolbar();
        initWebView();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_detail_options_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return onBackPressed();
            case R.id.action_share:
                sendShareAction();
                return true;
            case R.id.action_star:
                break;
            case R.id.action_comment:
                break;
            case R.id.action_thumb_up:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean onBackPressed() {
        return getFragmentManager().popBackStackImmediate();
    }

    private void sendShareAction() {
        String shareUrl = presenter.getShareUrl();
        if (TextUtils.isEmpty(shareUrl)) {
            return;
        }
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareUrl);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.action_share)));
    }

    @Override
    public void setNewsDetail(StoryDetail storyDetail) {
        imageLoader.load(storyDetail.getImage(), topImageView);
        titleView.setText(storyDetail.getTitle());

        String htmlWithCss = WebUtils.buildHtmlWithCss(storyDetail.getBody(), storyDetail.getCss());
        LogUtils.v(TAG, "html with Css: " + htmlWithCss);
        webView.loadData(htmlWithCss, WebUtils.MIME_TYPE, WebUtils.ENCODING);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
    }
}
