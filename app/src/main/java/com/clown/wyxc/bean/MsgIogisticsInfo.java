package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by cc on 2016/8/3.
 */
public class MsgIogisticsInfo extends Message {
    @Expose
    private String time;

    //
    @Expose
    private String info;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
