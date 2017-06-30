package com.ddmeng.zhihudaily.newslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;
import com.ddmeng.zhihudaily.utils.LogUtils;
import com.ddmeng.zhihudaily.widget.ItemDecoration.HeaderAdapter;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        HeaderAdapter<RecyclerView.ViewHolder> {
    public interface Callback {
        void onStoryClicked(String id);
    }

    public static final String TAG = "NewsListAdapter";

    public static final int VIEW_TYPE_STORY = 0;
    public static final int VIEW_TYPE_TOP_NEWS = 1;

    private DisplayStories displayStories;
    private List<String> headers = new ArrayList<>();
    private ImageLoader imageLoader;
    private Callback callback;

    public NewsListAdapter(ImageLoader imageLoader, Callback callback) {
        this.imageLoader = imageLoader;
        this.callback = callback;
    }

    public void setDisplayStories(DisplayStories displayStories) {
        this.displayStories = displayStories;
    }

    public void appendDisplayStories(DisplayStories displayStories) {
        this.displayStories.getListStories().addAll(displayStories.getListStories());
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
                return new TopStoriesViewHolder(view, imageLoader, callback);
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
        LogUtils.v(TAG, "onBindViewHolder: " + position);
        if (holder instanceof StoryViewHolder) {
            Story story = displayStories.getListStories().get(position - 1);
            ((StoryViewHolder) holder).populate(story);
        } else if (holder instanceof TopStoriesViewHolder) {
            ((TopStoriesViewHolder) holder).populate(displayStories.getTopStories());
        }
    }

    @Override
    public int getItemCount() {
        return displayStories != null ? displayStories.getListStories().size() + 1 : 0;
    }

    public String getCurrentTitle(int position) {
        return getItemHeader(position);
    }

    @Override
    public long getHeaderId(int position) {
        String header = getItemHeader(position);
        if (header != null) {
            return generateHeaderId(header);
        } else {
            return -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_header_view_holder, parent, false);
        return new DateHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        String header = getItemHeader(position);
        if (header != null) {
            ((DateHeaderViewHolder) holder).populate(header);
        }
    }

    private String getItemHeader(int position) {
        if (position > 0) {
            Story story = displayStories.getListStories().get(position - 1);
            return story.getDate();
        } else {
            return null;
        }
    }

    private int generateHeaderId(String header) {
        if (!headers.contains(header)) {
            headers.add(header);
            return headers.size() - 1;
        }
        return headers.indexOf(header);
    }
}
