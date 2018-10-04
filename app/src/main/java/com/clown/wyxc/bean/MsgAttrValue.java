package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_qiantw on 16/6/9.
 */
public class MsgAttrValue extends Message {
    @Expose
    private int Id;
    @Expose
    private String attrValue;
    @Expose
    private MsgAttrName attrName;
    @Expose
    private int attrNameId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public MsgAttrName getAttrName() {
        return attrName;
    }

    public void setAttrName(MsgAttrName attrName) {
        this.attrName = attrName;
    }

    public int getAttrNameId() {
        return attrNameId;
    }

    public void setAttrNameId(int attrNameId) {
        this.attrNameId = attrNameId;
    }
}
