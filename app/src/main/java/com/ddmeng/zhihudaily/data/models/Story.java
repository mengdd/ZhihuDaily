package com.ddmeng.zhihudaily.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Story {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("multipic")
    @Expose
    private Boolean multipic;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getMultipic() {
        return multipic;
    }

    public void setMultipic(Boolean multipic) {
        this.multipic = multipic;
    }
}