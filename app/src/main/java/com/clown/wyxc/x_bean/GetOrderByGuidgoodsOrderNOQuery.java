package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GetOrderByGuidgoodsOrderNOQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String guidgoodsOrderNO;

    public String getGuidgoodsOrderNO(){
        return guidgoodsOrderNO;
    }

    public void setGuidgoodsOrderNO(String guidgoodsOrderNO){
        this. guidgoodsOrderNO = guidgoodsOrderNO;
    }
    
    @Expose
    private OrderFirmOrderAddressResult orderFirmOrderAddressResult;

    public OrderFirmOrderAddressResult getOrderFirmOrderAddressResult(){
        return orderFirmOrderAddressResult;
    }

    public void setOrderFirmOrderAddressResult(OrderFirmOrderAddressResult orderFirmOrderAddressResult){
        this. orderFirmOrderAddressResult = orderFirmOrderAddressResult;
    }
    
    public GetOrderByGuidgoodsOrderNOQuery(){
    }

    public GetOrderByGuidgoodsOrderNOQuery(Integer userId,String guidgoodsOrderNO,OrderFirmOrderAddressResult orderFirmOrderAddressResult){
        this.userId=userId;
        this.guidgoodsOrderNO=guidgoodsOrderNO;
        this.orderFirmOrderAddressResult=orderFirmOrderAddressResult;
    }
}