package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by CZP on 2016/7/2.
 */
public class MsgCommentInfo extends Message {

    @Expose
    private int starNum;

    @Expose
    private MsgUserInfo userinfo;

    @Expose
    private String commentContent;

    @Expose
    private String commentDate;

    @Expose
    private List<MsgCommentImage> commentImageList;

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public MsgUserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(MsgUserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public List<MsgCommentImage> getCommentImageList() {
        return commentImageList;
    }

    public void setCommentImageList(List<MsgCommentImage> commentImageList) {
        this.commentImageList = commentImageList;
    }
}
