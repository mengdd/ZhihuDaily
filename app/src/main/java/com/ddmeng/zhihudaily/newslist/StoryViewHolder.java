package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoryViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "StoryViewHolder";
    @BindView(R.id.story_title)
    TextView titleView;
    @BindView(R.id.story_image)
    ImageView storyImageView;

    private ImageLoader imageLoader;
    private NewsListAdapter.Callback callback;
    private String id;

    public StoryViewHolder(View itemView, ImageLoader imageLoader, NewsListAdapter.Callback callback) {
        super(itemView);
        this.imageLoader = imageLoader;
        this.callback = callback;
        ButterKnife.bind(this, itemView);
    }

    public void populate(Story story) {
        LogUtils.v(TAG, "populate: " + story);
        id = story.getId();
        titleView.setText(story.getTitle());
        imageLoader.load(story.getListImage(), storyImageView);
    }

    @OnClick(R.id.story_card_view)
    void onStoryClick() {
        callback.onStoryClicked(id);
    }
}
