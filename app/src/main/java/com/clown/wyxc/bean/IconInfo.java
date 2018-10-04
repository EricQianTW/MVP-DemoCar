package com.clown.wyxc.bean;

import com.google.gson.annotations.Expose;
import com.clown.wyxc.base.Message;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class IconInfo extends Message {

    @Expose
    private String picUrl;

    @Expose
    private String title;

    @Expose
    private Integer resId;

    public IconInfo(int resId, String title, String picUrl) {
        this.resId = resId;
        this.title = title;
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }
}
