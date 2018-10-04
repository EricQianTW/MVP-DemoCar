package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class MerchantQueryId extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String time;

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this. time = time;
    }
    
    @Expose
    private Integer userid;

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this. userid = userid;
    }
    
    public MerchantQueryId(){
    }

    public MerchantQueryId(Integer id,String time,Integer userid){
        this.id=id;
        this.time=time;
        this.userid=userid;
    }
}