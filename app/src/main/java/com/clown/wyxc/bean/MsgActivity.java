package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/9.
 */
public class MsgActivity extends Message {

    //活动id
    @Expose
    private int Id;

    //活动标题
    @Expose
    private String Title;

    //活动类型 1:限时活动，2首页推荐活动
    @Expose
    private int activityType;

    //开始时间
    @Expose
    private String startTime;

    //结束时间
    @Expose
    private String endTime;

    //活动介绍
    @Expose
    private String Introduce;

    //活动详情
    @Expose
    private String Detail;

    //APP活动跳转链接
    @Expose
    private String appLink;

    //网页活动链接
    @Expose
    private String webLink;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
}
