package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;

/**
 * Created by cc on 2016/8/11.
 */
public class NotificationMsg extends Message{

    private String img_path;

    private String title;

    private String content;

    private String time;

    public NotificationMsg(String img_path, String title, String content, String time) {
        this.img_path = img_path;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
