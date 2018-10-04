package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by pnt_t on 2017/4/1.
 */

public class ActivityInfo extends Message {

    @Expose
    private String imgUrl;
    @Expose
    private String activityTime;
    @Expose
    private String activityContent;

    public ActivityInfo(String imgUrl, String activityTime, String activityContent) {
        this.imgUrl = imgUrl;
        this.activityTime = activityTime;
        this.activityContent = activityContent;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }
}
