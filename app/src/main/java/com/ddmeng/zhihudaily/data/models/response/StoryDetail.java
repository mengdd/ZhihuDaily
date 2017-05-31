package com.ddmeng.zhihudaily.data.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoryDetail {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("image_source")
    @Expose
    private String imageSource;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("js")
    @Expose
    private List<Object> js = null;
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("css")
    @Expose
    private List<String> css = null;
    @SerializedName("theme_name")
    @Expose
    private String themeName;
    @SerializedName("editor_name")
    @Expose
    private String editorName;
    @SerializedName("theme_id")
    @Expose
    private Integer themeId;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<Object> getJs() {
        return js;
    }

    public void setJs(List<Object> js) {
        this.js = js;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
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

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
}
