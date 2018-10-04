package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class ScreenValue extends Message{ 
    
    @Expose
    private String screenName;

    public String getScreenName(){
        return screenName;
    }

    public void setScreenName(String screenName){
        this. screenName = screenName;
    }
    
    @Expose
    private Integer screenValue;

    public Integer getScreenValue(){
        return screenValue;
    }

    public void setScreenValue(Integer screenValue){
        this. screenValue = screenValue;
    }
    
    @Expose
    private Integer isSelected;

    public Integer getIsSelected(){
        return isSelected;
    }

    public void setIsSelected(Integer isSelected){
        this. isSelected = isSelected;
    }
    
    public ScreenValue(){
    }

    public ScreenValue(String screenName,Integer screenValue,Integer isSelected){
        this.screenName=screenName;
        this.screenValue=screenValue;
        this.isSelected=isSelected;
    }
}