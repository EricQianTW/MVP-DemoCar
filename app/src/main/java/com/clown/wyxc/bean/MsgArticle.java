package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by CZP on 2016/7/27.
 */
public class MsgArticle extends Message {

    @Expose
    private int artId;
    @Expose
    private String artTitle;
    @Expose
    private String artContect;
    @Expose
    private String  createTime;
    @Expose
    private List<MsgArticleImage> artImageList;
    @Expose
    private MsgVideoInfo videoInfo;
    @Expose
    private String artClickUrl;
    @Expose
    private MsgUserInfo userInfo;

    @Expose
    private MsgShareDetail shareDetail;

    public MsgShareDetail getShareDetail() {
        return shareDetail;
    }

    public void setShareDetail(MsgShareDetail shareDetail) {
        this.shareDetail = shareDetail;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    public String getArtContect() {
        return artContect;
    }

    public void setArtContect(String artContect) {
        this.artContect = artContect;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<MsgArticleImage> getArtImageList() {
        return artImageList;
    }

    public void setArtImageList(List<MsgArticleImage> artImageList) {
        this.artImageList = artImageList;
    }

    public MsgVideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(MsgVideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public String getArtClickUrl() {
        return artClickUrl;
    }

    public void setArtClickUrl(String artClickUrl) {
        this.artClickUrl = artClickUrl;
    }

    public MsgUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MsgUserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
