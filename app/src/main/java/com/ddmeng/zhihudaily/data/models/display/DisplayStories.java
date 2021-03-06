package com.ddmeng.zhihudaily.data.models.display;

import com.ddmeng.zhihudaily.data.models.db.Story;

import java.util.List;

public class DisplayStories {
    private String date;
    private List<Story> topStories;
    private List<Story> listStories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<Story> topStories) {
        this.topStories = topStories;
    }

    public List<Story> getListStories() {
        return listStories;
    }

    public void setListStories(List<Story> listStories) {
        this.listStories = listStories;
    }

    @Override
    public String toString() {
        int topStoriesCount = topStories != null ? topStories.size() : 0;
        int listStoriesCount = listStories != null ? listStories.size() : 0;
        return super.toString() + "date: " + date + ", top stories count = " + topStoriesCount +
                ", list stories count = " + listStoriesCount;
    }
}
