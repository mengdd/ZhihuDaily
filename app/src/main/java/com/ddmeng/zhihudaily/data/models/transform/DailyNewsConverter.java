package com.ddmeng.zhihudaily.data.models.transform;

import com.ddmeng.zhihudaily.data.models.db.Story;
import com.ddmeng.zhihudaily.data.models.display.DisplayStories;
import com.ddmeng.zhihudaily.data.models.response.DailyNews;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DailyNewsConverter {
    private StoryConverter storyConverter;

    @Inject
    public DailyNewsConverter(StoryConverter storyConverter) {
        this.storyConverter = storyConverter;
    }

    public DisplayStories getNews(DailyNews dailyNews) {
        DisplayStories displayStories = new DisplayStories();
        displayStories.setTopStories(getTopStories(dailyNews));
        displayStories.setListStories(getListStories(dailyNews));
        return displayStories;
    }

    private List<Story> getTopStories(DailyNews dailyNews) {
        List<com.ddmeng.zhihudaily.data.models.response.Story> responseTopStories = dailyNews.getTopStories();
        List<Story> topStories = new ArrayList<>();
        for (com.ddmeng.zhihudaily.data.models.response.Story responseStory : responseTopStories) {
            Story story = storyConverter.convertTopStory(responseStory, dailyNews.getDate());
            topStories.add(story);
        }
        return topStories;
    }

    private List<Story> getListStories(DailyNews dailyNews) {
        List<com.ddmeng.zhihudaily.data.models.response.Story> responseListStories = dailyNews.getStories();
        List<Story> listStories = new ArrayList<>();
        for (com.ddmeng.zhihudaily.data.models.response.Story responseStory : responseListStories) {
            Story story = storyConverter.convertListStory(responseStory, dailyNews.getDate());
            listStories.add(story);
        }
        return listStories;
    }
}
