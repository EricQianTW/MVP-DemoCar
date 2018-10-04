package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by JokerEric on 2016/6/21.
 */
public class MsgActivtiySale extends Message {
    @Expose
    private int Id;

    @Expose
    private String Title;

    @Expose
    private int activityType;

    @Expose
    private String startTime;

    @Expose
    private String endTime;

    @Expose
    private String Introduce;

    @Expose
    private String Detail;

    @Expose
    private String appLink;

    @Expose
    private String webLink;

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

    @Expose
    private List<MsgSaleInfo> saleList;

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

    public List<MsgSaleInfo> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<MsgSaleInfo> saleList) {
        this.saleList = saleList;
    }
}
