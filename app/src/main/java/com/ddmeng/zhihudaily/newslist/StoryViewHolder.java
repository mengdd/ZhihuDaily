package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.Story;
import com.ddmeng.zhihudaily.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoryViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "StoryViewHolder";
    @BindView(R.id.story_title)
    TextView titleView;

    public StoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Story story) {
        LogUtils.i(TAG, "populate: " + story);
        titleView.setText(story.getTitle());
    }
}
