package com.ddmeng.zhihudaily.data.models.transform;

import com.ddmeng.zhihudaily.data.models.db.StoryDetail;

import javax.inject.Inject;

public class StoryDetailConverter {
    @Inject
    public StoryDetailConverter() {
    }

    public StoryDetail convertStoryDetail(com.ddmeng.zhihudaily.data.models.response.StoryDetail responseStoryDetail) {
        StoryDetail storyDetail = new StoryDetail();
        storyDetail.setId(responseStoryDetail.getId());
        storyDetail.setBody(responseStoryDetail.getBody());
        storyDetail.setImageSource(responseStoryDetail.getImageSource());
        storyDetail.setTitle(responseStoryDetail.getTitle());
        storyDetail.setDetailImage(responseStoryDetail.getImage());
        storyDetail.setShareUrl(responseStoryDetail.getShareUrl());
        storyDetail.setType(responseStoryDetail.getType());
        storyDetail.setCss(responseStoryDetail.getCss().get(0));
        storyDetail.setThemeName(responseStoryDetail.getThemeName());
        storyDetail.setEditorName(responseStoryDetail.getEditorName());
        if (responseStoryDetail.getThemeId() != null) {
            storyDetail.setThemeId(responseStoryDetail.getThemeId());
        }
        return storyDetail;
    }
}
