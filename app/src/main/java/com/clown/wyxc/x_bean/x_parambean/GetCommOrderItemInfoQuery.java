package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GetCommOrderItemInfoQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer orderId;

    public Integer getOrderId(){
        return orderId;
    }

    public void setOrderId(Integer orderId){
        this. orderId = orderId;
    }
    
    public GetCommOrderItemInfoQuery(){
    }

    public GetCommOrderItemInfoQuery(Integer userId,Integer orderId){
        this.userId=userId;
        this.orderId=orderId;
    }
}