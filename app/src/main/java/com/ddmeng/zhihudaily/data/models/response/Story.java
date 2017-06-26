package com.ddmeng.zhihudaily.data.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Story {

    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("multipic")
    @Expose
    private boolean multiPic;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getMultiPic() {
        return multiPic;
    }

    public void setMultiPic(Boolean multiPic) {
        this.multiPic = multiPic;
    }
}