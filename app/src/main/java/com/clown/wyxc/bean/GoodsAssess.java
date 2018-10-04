package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_qiantw on 16/6/4.
 */
public class GoodsAssess extends Message {
    @Expose
    private String userImgUrl;
    @Expose
    private String userName;
    @Expose
    private String content;
    @Expose
    private String assessDate;

    public String getUserImgUrl() {
        return userImgUrl;
    }

    public void setUserImgUrl(String userImgUrl) {
        this.userImgUrl = userImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(String assessDate) {
        this.assessDate = assessDate;
    }
}
