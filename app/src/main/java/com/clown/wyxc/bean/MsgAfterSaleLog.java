package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by CZP on 2016/7/17.
 */
public class MsgAfterSaleLog extends Message {

    @Expose
    private String title;

    @Expose
    private String Explain;

    @Expose
    private String CreatTime;

    @Expose
    private String AfterSaleNo;

    @Expose
    private int type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(String creatTime) {
        CreatTime = creatTime;
    }

    public String getAfterSaleNo() {
        return AfterSaleNo;
    }

    public void setAfterSaleNo(String afterSaleNo) {
        AfterSaleNo = afterSaleNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
