package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by eric_qiantw on 16/5/28.
 */
public class CommunityInfo extends Message {
    @Expose
    private String HeaderUrl;
    @Expose
    private String userName;
    @Expose
    private String comment;
    @Expose
    private List<IconInfo> gridView;
    @Expose
    private List<IconInfo> commnetList;

    public String getHeaderUrl() {
        return HeaderUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        HeaderUrl = headerUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<IconInfo> getGridView() {
        return gridView;
    }

    public void setGridView(List<IconInfo> gridView) {
        this.gridView = gridView;
    }

    public List<IconInfo> getCommnetList() {
        return commnetList;
    }

    public void setCommnetList(List<IconInfo> commnetList) {
        this.commnetList = commnetList;
    }
}
