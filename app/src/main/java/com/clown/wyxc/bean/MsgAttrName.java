package com.clown.wyxc.bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by eric_qiantw on 16/6/7.
 */
public class MsgAttrName extends Message {


    /**
     * Id : 1
     * attrName : 颜色
     * attrValueList : [{"Id":1,"attrValue":"黑色","attrName":null,"attrNameId":1},{"Id":2,"attrValue":"银色","attrName":null,"attrNameId":1},{"Id":3,"attrValue":"金色","attrName":null,"attrNameId":1},{"Id":4,"attrValue":"白色","attrName":null,"attrNameId":1}]
     */
    @Expose
    private String Name;


    /**
     * Id : 1
     * attrValue : 黑色
     * attrName : null
     * attrNameId : 1
     */
    @Expose
    private List<String> Values;

    @Expose
    private List<AttrValueList> attrValueList;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getValues() {
        return Values;
    }

    public void setValues(List<String> values) {
        Values = values;
    }

    public List<AttrValueList> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<AttrValueList> attrValueList) {
        this.attrValueList = attrValueList;
    }
}
