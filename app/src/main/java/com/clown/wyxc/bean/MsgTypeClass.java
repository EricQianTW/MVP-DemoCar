package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by zhoulang on 2016/8/18.
 */
public class MsgTypeClass extends Message{

    @Expose
    private List<MsgTypeClass_2> children;

    @Expose
    private String clickUrl;

    @Expose
    private String imageUrl;

    @Expose
    private String selelctedImageUrl;

    @Expose
    private int typeId;

    @Expose
    private String typeName;

    public List<MsgTypeClass_2> getChildren() {
        return children;
    }

    public void setChildren(List<MsgTypeClass_2> children) {
        this.children = children;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
