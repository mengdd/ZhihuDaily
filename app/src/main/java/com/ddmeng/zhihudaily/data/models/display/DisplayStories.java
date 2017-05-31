package com.ddmeng.zhihudaily.data.models.display;

import com.ddmeng.zhihudaily.data.models.db.Story;

import java.util.List;

public class DisplayStories {
    private List<Story> topStories;
    private List<Story> listStories;

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
}
