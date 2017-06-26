package com.ddmeng.zhihudaily.data.models.db;

import com.ddmeng.zhihudaily.data.local.ZhihuDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = ZhihuDatabase.class)
public class StoryDetail extends BaseModel {

    @PrimaryKey
    private String id;
    @Column
    private String body;
    @Column
    private String imageSource;
    @Column
    private String title;
    @Column
    private String detailImage;
    @Column
    private String shareUrl;
    @Column
    private int type;
    @Column
    private String css;

    @Column
    private String themeName;
    @Column
    private String editorName;
    @Column
    private int themeId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
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

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }
}
