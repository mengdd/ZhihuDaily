package com.ddmeng.zhihudaily.data.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyNews {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stories")
    @Expose
    private List<Story> stories = null;
    @SerializedName("top_stories")
    @Expose
    private List<Story> topStories = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<Story> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<Story> topStories) {
        this.topStories = topStories;
    }
}
