package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;

/**
 * Created by eric_qiantw on 16/6/7.
 */
public class AttrValueList extends Message {
    private int Id;
    private String attrValue;
    private String attrName;
    private int attrNameId;
    private boolean none;
    private boolean defaulty;
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

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public int getAttrNameId() {
        return attrNameId;
    }

    public void setAttrNameId(int attrNameId) {
        this.attrNameId = attrNameId;
    }

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    public boolean isDefaulty() {
        return defaulty;
    }

    public void setDefaulty(boolean defaulty) {
        this.defaulty = defaulty;
    }
}
