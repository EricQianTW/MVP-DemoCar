package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class AddEvaluateByOrderIdQuery extends Message{
    
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
    
    @Expose
    private List<GoodsPingLun> goodsPingLunList;

    public List<GoodsPingLun> getGoodsPingLunList(){
        return goodsPingLunList;
    }

    public void setGoodsPingLunList(List<GoodsPingLun> goodsPingLunList){
        this. goodsPingLunList = goodsPingLunList;
    }
    
    @Expose
    private List<ServicePingLun> servicePingLunList;

    public List<ServicePingLun> getServicePingLunList(){
        return servicePingLunList;
    }

    public void setServicePingLunList(List<ServicePingLun> servicePingLunList){
        this. servicePingLunList = servicePingLunList;
    }
    
    public AddEvaluateByOrderIdQuery(){
    }

    public AddEvaluateByOrderIdQuery(Integer userId,Integer orderId,List<GoodsPingLun> goodsPingLunList,List<ServicePingLun> servicePingLunList){
        this.userId=userId;
        this.orderId=orderId;
        this.goodsPingLunList=goodsPingLunList;
        this.servicePingLunList=servicePingLunList;
    }
}