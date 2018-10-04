package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by cc on 2016/8/9.
 */
public class MsgRecommend extends Message {

    //推荐id
    @Expose
    private int Id;

    //推荐标题
    @Expose
    private String RecommendTitle;

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

    //活动类型 1:限时活动，2首页推荐活动
    @Expose
    private int Type;

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

    public String getRecommendTitle() {
        return RecommendTitle;
    }

    public void setRecommendTitle(String recommendTitle) {
        RecommendTitle = recommendTitle;
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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
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
