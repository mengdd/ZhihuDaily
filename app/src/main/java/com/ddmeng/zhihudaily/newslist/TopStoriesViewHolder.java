package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.utils.LogUtils;
import com.ddmeng.zhihudaily.widget.AutoScrollViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    public static final String TAG = "TopStoriesViewHolder";

    @BindView(R.id.top_stories_pager)
    AutoScrollViewPager topStoriesPager;
    private final TopStoriesPagerAdapter adapter;

    public TopStoriesViewHolder(View itemView, ImageLoader imageLoader) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        adapter = new TopStoriesPagerAdapter(imageLoader);
        topStoriesPager.setAdapter(adapter);
        topStoriesPager.startAutoScroll();
    }

    public void populate(List<Story> topStories) {
        LogUtils.v(TAG, "populate: " + topStories.size());
        adapter.setTopStories(topStories);
        adapter.notifyDataSetChanged();
    }
}
