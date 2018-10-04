package com.clown.wyxc.bean;

import java.util.List;

/**
 * Created by cc on 2016/8/7.
 */
public class CommentGroup {

    private int id;
    private String imagepath;
    private String content;
    private int starnum = 5;
    private List<MsgImage> imagelist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStarnum() {
        return starnum;
    }

    public void setStarnum(int starnum) {
        this.starnum = starnum;
    }

    public List<MsgImage> getImagelist() {
        return imagelist;
    }

    public void setImagelist(List<MsgImage> imagelist) {
        this.imagelist = imagelist;
    }
}
