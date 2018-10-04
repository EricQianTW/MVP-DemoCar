package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by cc on 2016/8/5.
 */
public class MsgGoodsCommentInfo extends Message{

    @Expose
    private String GoodsSpec;

    @Expose
    private int Id;

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

    @Expose
    private int likeNum;

    public MsgGoodsCommentInfo(String goodsSpec, int id, int starNum, MsgUserInfo userinfo, String commentContent, String commentDate, List<MsgCommentImage> commentImageList, int likeNum) {
        GoodsSpec = goodsSpec;
        Id = id;
        this.starNum = starNum;
        this.userinfo = userinfo;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.commentImageList = commentImageList;
        this.likeNum = likeNum;
    }

    public String getGoodsSpec() {
        return GoodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        GoodsSpec = goodsSpec;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
