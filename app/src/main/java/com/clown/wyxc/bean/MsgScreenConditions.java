package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by JokerEric on 2016/7/4.
 */
public class MsgScreenConditions extends Message{

    //条件id
    @Expose
    private int Id;

    //条件类型
    @Expose
    private int screenType;

    //筛选提交
    @Expose
    private String title;

//筛选值数组
    @Expose
    private List<MsgScreenValue> screenValueList;

    //是否多选
    @Expose
    private int isMultiple;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getScreenType() {
        return screenType;
    }

    public void setScreenType(int screenType) {
        this.screenType = screenType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(int isMultiple) {
        this.isMultiple = isMultiple;
    }

    public List<MsgScreenValue> getScreenValueList() {
        return screenValueList;
    }

    public void setScreenValueList(List<MsgScreenValue> screenValueList) {
        this.screenValueList = screenValueList;
    }
}
