package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ScreenConditionsResult extends Message{
    
    @Expose
    private List<ScreenValue> screenValueList;

    public List<ScreenValue> getScreenValueList(){
        return screenValueList;
    }

    public void setScreenValueList(List<ScreenValue> screenValueList){
        this. screenValueList = screenValueList;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private Integer isMultiple;

    public Integer getIsMultiple(){
        return isMultiple;
    }

    public void setIsMultiple(Integer isMultiple){
        this. isMultiple = isMultiple;
    }
    
    public ScreenConditionsResult(){
    }

    public ScreenConditionsResult(List<ScreenValue> screenValueList,Integer id,String name,Integer isMultiple){
        this.screenValueList=screenValueList;
        this.id=id;
        this.name=name;
        this.isMultiple=isMultiple;
    }
}