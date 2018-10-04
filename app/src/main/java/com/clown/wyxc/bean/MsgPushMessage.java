package com.clown.wyxc.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by CZP on 2016/7/19.
 */
public class MsgPushMessage {

    @Expose
    private int Id;
    @Expose
    private String Content;

    @Expose
    private String Title;

    @Expose
    private String SendTime;

    @Expose
    private int Typ;

    @Expose
    private String Url;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSendTime() {
        return SendTime;
    }

    public void setSendTime(String sendTime) {
        SendTime = sendTime;
    }

    public int getTyp() {
        return Typ;
    }

    public void setTyp(int typ) {
        Typ = typ;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
