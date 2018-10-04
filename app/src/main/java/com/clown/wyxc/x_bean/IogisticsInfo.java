package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class IogisticsInfo extends Message{ 
    
    @Expose
    private String time;

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this. time = time;
    }
    
    @Expose
    private String wuliuInfo;

    public String getWuliuInfo(){
        return wuliuInfo;
    }

    public void setWuliuInfo(String wuliuInfo){
        this. wuliuInfo = wuliuInfo;
    }
    
    public IogisticsInfo(){
    }

    public IogisticsInfo(String time,String wuliuInfo){
        this.time=time;
        this.wuliuInfo=wuliuInfo;
    }
}