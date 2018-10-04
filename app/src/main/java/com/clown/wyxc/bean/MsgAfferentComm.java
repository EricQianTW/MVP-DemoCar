package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by cc on 2016/8/7.
 */
public class MsgAfferentComm extends Message {

    @Expose
    private int orderItemId;

    @Expose
    private List<Integer> ImageIdList;

    @Expose
    private int Id;

    @Expose
    private int starNum;

    @Expose
    private String commentContent;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public List<Integer> getImageIdList() {
        return ImageIdList;
    }

    public void setImageIdList(List<Integer> imageIdList) {
        ImageIdList = imageIdList;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
