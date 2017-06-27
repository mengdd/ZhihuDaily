package com.ddmeng.zhihudaily.newslist;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ddmeng.zhihudaily.R;
import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.imageloader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopStoriesPagerAdapter extends PagerAdapter {

    private List<Story> topStories;
    private ImageLoader imageLoader;
    private NewsListAdapter.Callback callback;

    public TopStoriesPagerAdapter(ImageLoader imageLoader, NewsListAdapter.Callback callback) {
        this.imageLoader = imageLoader;
        this.callback = callback;
    }

    public void setTopStories(List<Story> topStories) {
        this.topStories = topStories;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View view = layoutInflater.inflate(R.layout.top_story_viewpager_item, container, false);
        ItemViewHolder holder = new ItemViewHolder(view, callback);
        holder.populate(topStories.get(position), imageLoader);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return topStories != null ? topStories.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public static class ItemViewHolder {
        @BindView(R.id.pager_item_image)
        ImageView topImageView;
        @BindView(R.id.pager_item_title)
        TextView topTitleView;

        private NewsListAdapter.Callback callback;
        private Story story;

        public ItemViewHolder(View view, NewsListAdapter.Callback callback) {
            ButterKnife.bind(this, view);
            this.callback = callback;
        }

        public void populate(Story story, ImageLoader imageLoader) {
            this.story = story;
            imageLoader.load(story.getListImage(), topImageView);
            topTitleView.setText(story.getTitle());
        }

        @OnClick(R.id.top_story_container)
        void onTopStoryClicked() {
            callback.onStoryClicked(story.getId());
        }
    }
}
