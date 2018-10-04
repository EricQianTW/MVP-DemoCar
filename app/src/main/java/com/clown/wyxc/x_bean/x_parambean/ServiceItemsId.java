package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class ServiceItemsId extends Message{ 
    
    @Expose
    private Integer serviceItemsId;

    public Integer getServiceItemsId(){
        return serviceItemsId;
    }

    public void setServiceItemsId(Integer serviceItemsId){
        this. serviceItemsId = serviceItemsId;
    }
    
    public ServiceItemsId(){
    }

    public ServiceItemsId(Integer serviceItemsId){
        this.serviceItemsId=serviceItemsId;
    }
}