package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class FirmOrderFormResult extends Message{
    
    @Expose
    private List<OrderInfo> orderList;

    public List<OrderInfo> getOrderList(){
        return orderList;
    }

    public void setOrderList(List<OrderInfo> orderList){
        this. orderList = orderList;
    }
    
    @Expose
    private OrderServiceInfo orderServiceInfo;

    public OrderServiceInfo getOrderServiceInfo(){
        return orderServiceInfo;
    }

    public void setOrderServiceInfo(OrderServiceInfo orderServiceInfo){
        this. orderServiceInfo = orderServiceInfo;
    }
    
    @Expose
    private OrderFirmOrderAddressResult orderFirmOrderAddressResult;

    public OrderFirmOrderAddressResult getOrderFirmOrderAddressResult(){
        return orderFirmOrderAddressResult;
    }

    public void setOrderFirmOrderAddressResult(OrderFirmOrderAddressResult orderFirmOrderAddressResult){
        this. orderFirmOrderAddressResult = orderFirmOrderAddressResult;
    }
    
    @Expose
    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice){
        this. totalPrice = totalPrice;
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
    private Integer orderType;

    public Integer getOrderType(){
        return orderType;
    }

    public void setOrderType(Integer orderType){
        this. orderType = orderType;
    }
    
    public FirmOrderFormResult(){
    }

    public FirmOrderFormResult(List<OrderInfo> orderList,OrderServiceInfo orderServiceInfo,OrderFirmOrderAddressResult orderFirmOrderAddressResult,BigDecimal totalPrice,String guidgoodsOrderNO,Integer orderType){
        this.orderList=orderList;
        this.orderServiceInfo=orderServiceInfo;
        this.orderFirmOrderAddressResult=orderFirmOrderAddressResult;
        this.totalPrice=totalPrice;
        this.guidgoodsOrderNO=guidgoodsOrderNO;
        this.orderType=orderType;
    }
}