package com.ddmeng.zhihudaily.data.models.db;

import com.ddmeng.zhihudaily.data.local.ZhihuDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = ZhihuDatabase.class)
public class Story extends BaseModel {
    @PrimaryKey
    private String id;
    @Column
    private String date;
    @Column
    private String title;
    @Column
    private String listImage;
    @Column
    private int type;
    @Column
    private boolean multiPic;
    @ForeignKey(stubbedRelationship = true)
    private StoryDetail storyDetail;
    @Column
    private boolean isTopStory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMultiPic() {
        return multiPic;
    }

    public void setMultiPic(boolean multiPic) {
        this.multiPic = multiPic;
    }

    public StoryDetail getStoryDetail() {
        return storyDetail;
    }

    public void setStoryDetail(StoryDetail storyDetail) {
        this.storyDetail = storyDetail;
    }

    public boolean isTopStory() {
        return isTopStory;
    }

    public void setTopStory(boolean topStory) {
        isTopStory = topStory;
    }
}
