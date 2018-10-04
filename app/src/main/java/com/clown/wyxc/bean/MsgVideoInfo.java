package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by CZP on 2016/7/27.
 */
public class MsgVideoInfo extends Message {
    @Expose
    private String videoLink;
    @Expose
    private String videoImageLink;

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoImageLink() {
        return videoImageLink;
    }

    public void setVideoImageLink(String videoImageLink) {
        this.videoImageLink = videoImageLink;
    }
}
