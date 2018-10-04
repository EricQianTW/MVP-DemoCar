package com.clown.wyxc.bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by zhoulang on 2016/8/18.
 */
public class MsgTypeClass_2 extends Message {
    @Expose
    private String className;

    @Expose
    private int id;

    @Expose
    private int fieldId;

    @Expose
    private String imageUrl;

    @Expose
    private String selelctedImageUrl;

    @Expose
    private String clickUrl;

    public MsgTypeClass_2(String className) {
        this.className = className;
    }

    public MsgTypeClass_2(String className,String imageUrl) {
        this.className = className;
        this.imageUrl = imageUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSelelctedImageUrl() {
        return selelctedImageUrl;
    }

    public void setSelelctedImageUrl(String selelctedImageUrl) {
        this.selelctedImageUrl = selelctedImageUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }
}
