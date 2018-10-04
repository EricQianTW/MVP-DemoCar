package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2016/8/5.
 */
public class ceshi extends Message{
    @Expose
    private int amoant;
    @Expose
    private String text;

    public int getAmoant() {
        return amoant;
    }

    public void setAmoant(int amoant) {
        this.amoant = amoant;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
