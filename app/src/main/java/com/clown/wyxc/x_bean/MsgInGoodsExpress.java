package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_clown on 2017/5/30.
 */

public class MsgInGoodsExpress extends Message {

    @Expose
    private int Id;

    @Expose
    private String title;

    @Expose
    private MsgInGoodsExpressDtl ingoods;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MsgInGoodsExpressDtl getIngoods() {
        return ingoods;
    }

    public void setIngoods(MsgInGoodsExpressDtl ingoods) {
        this.ingoods = ingoods;
    }
}
