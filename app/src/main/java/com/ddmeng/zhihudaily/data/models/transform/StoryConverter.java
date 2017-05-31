package com.ddmeng.zhihudaily.data.models.transform;

import com.ddmeng.zhihudaily.data.models.db.Story;

import javax.inject.Inject;

public class StoryConverter {

    @Inject
    public StoryConverter() {
    }

    public Story convertTopStory(com.ddmeng.zhihudaily.data.models.response.Story responseStory, String date) {
        Story story = convert(responseStory, date);
        story.setListImage(responseStory.getImage());
        return story;
    }

    public Story convertListStory(com.ddmeng.zhihudaily.data.models.response.Story responseStory, String date) {
        Story story = convert(responseStory, date);
        story.setListImage(responseStory.getImages().get(0));
        return story;
    }

    private Story convert(com.ddmeng.zhihudaily.data.models.response.Story responseStory, String date) {
        Story story = new Story();
        story.setId(responseStory.getId());
        story.setDate(date);
        story.setTitle(responseStory.getTitle());
        story.setType(responseStory.getType());
        story.setMultiPic(responseStory.getMultiPic());
        return story;
    }
}
