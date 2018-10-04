package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class CarInfoYearQuery extends Message{ 
    
    @Expose
    private String yearType;

    public String getYearType(){
        return yearType;
    }

    public void setYearType(String yearType){
        this. yearType = yearType;
    }
    
    @Expose
    private Integer parentid;

    public Integer getParentid(){
        return parentid;
    }

    public void setParentid(Integer parentid){
        this. parentid = parentid;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    public CarInfoYearQuery(){
    }

    public CarInfoYearQuery(String yearType,Integer parentid,Integer userId){
        this.yearType=yearType;
        this.parentid=parentid;
        this.userId=userId;
    }
}