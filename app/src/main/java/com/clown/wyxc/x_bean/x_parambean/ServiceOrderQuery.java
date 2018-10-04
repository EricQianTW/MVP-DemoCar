package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ServiceOrderQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer merchantId;

    public Integer getMerchantId(){
        return merchantId;
    }

    public void setMerchantId(Integer merchantId){
        this. merchantId = merchantId;
    }
    
    @Expose
    private List<ServiceItemsId> serviceItemsList;

    public List<ServiceItemsId> getServiceItemsList(){
        return serviceItemsList;
    }

    public void setServiceItemsList(List<ServiceItemsId> serviceItemsList){
        this. serviceItemsList = serviceItemsList;
    }
    
    public ServiceOrderQuery(){
    }

    public ServiceOrderQuery(Integer userId,Integer merchantId,List<ServiceItemsId> serviceItemsList){
        this.userId=userId;
        this.merchantId=merchantId;
        this.serviceItemsList=serviceItemsList;
    }
}