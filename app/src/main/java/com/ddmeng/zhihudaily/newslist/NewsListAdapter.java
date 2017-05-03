package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.DailyNews;
import com.ddmeng.zhihudaily.data.models.Story;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.utils.LogUtils;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface Callback {
        void onStoryClicked(String id);
    }

    public static final String TAG = "NewsListAdapter";

    public static final int VIEW_TYPE_STORY = 0;
    public static final int VIEW_TYPE_TOP_NEWS = 1;

    private DailyNews dailyNews;
    private ImageLoader imageLoader;
    private Callback callback;

    public NewsListAdapter(ImageLoader imageLoader, Callback callback) {
        this.imageLoader = imageLoader;
        this.callback = callback;
    }

    public void setDailyNews(DailyNews dailyNews) {
        this.dailyNews = dailyNews;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0: {
                return VIEW_TYPE_TOP_NEWS;
            }
            default: {
                return VIEW_TYPE_STORY;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_TOP_NEWS: {
                View view = layoutInflater.inflate(R.layout.top_stories_view_holder, parent, false);
                return new TopStoriesViewHolder(view, imageLoader);
            }
            case VIEW_TYPE_STORY: {
                View view = layoutInflater.inflate(R.layout.story_view_holder, parent, false);
                return new StoryViewHolder(view, imageLoader, callback);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogUtils.d(TAG, "onBindViewHolder: " + position);
        if (holder instanceof StoryViewHolder) {
            Story story = dailyNews.getStories().get(position - 1);
            ((StoryViewHolder) holder).populate(story);
        } else if (holder instanceof TopStoriesViewHolder) {
            ((TopStoriesViewHolder) holder).populate(dailyNews.getTopStories());
        }
    }

    @Override
    public int getItemCount() {
        return dailyNews != null ? dailyNews.getStories().size() + 1 : 0;
    }
}
