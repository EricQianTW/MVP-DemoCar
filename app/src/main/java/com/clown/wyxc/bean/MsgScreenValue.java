package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/4.
 */
public class MsgScreenValue extends Message{
    //是否被选中 0:未选中 1:已被选中
    @Expose
    private int isSelected;

    //筛选标题
    @Expose
    private String screenTitle;

    //筛选值
    @Expose
    private String screenValue;

    public MsgScreenValue(int isSelected, String screenTitle, String screenValue) {
        this.isSelected = isSelected;
        this.screenTitle = screenTitle;
        this.screenValue = screenValue;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getScreenTitle() {
        return screenTitle;
    }

    public void setScreenTitle(String screenTitle) {
        this.screenTitle = screenTitle;
    }

    public String getScreenValue() {
        return screenValue;
    }

    public void setScreenValue(String screenValue) {
        this.screenValue = screenValue;
    }
}
