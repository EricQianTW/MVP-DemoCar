package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class ServiceItemsQuery extends Message{ 
    
    @Expose
    private Integer mId;

    public Integer getMId(){
        return mId;
    }

    public void setMId(Integer mId){
        this. mId = mId;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    public ServiceItemsQuery(){
    }

    public ServiceItemsQuery(Integer mId,Integer userId){
        this.mId=mId;
        this.userId=userId;
    }
}