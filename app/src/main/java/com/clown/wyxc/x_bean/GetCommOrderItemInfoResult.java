package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GetCommOrderItemInfoResult extends Message{
    
    @Expose
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList){
        this. orderItemList = orderItemList;
    }
    
    @Expose
    private List<OrderItemService> orderItemServiceList;

    public List<OrderItemService> getOrderItemServiceList(){
        return orderItemServiceList;
    }

    public void setOrderItemServiceList(List<OrderItemService> orderItemServiceList){
        this. orderItemServiceList = orderItemServiceList;
    }
    
    public GetCommOrderItemInfoResult(){
    }

    public GetCommOrderItemInfoResult(List<OrderItem> orderItemList,List<OrderItemService> orderItemServiceList){
        this.orderItemList=orderItemList;
        this.orderItemServiceList=orderItemServiceList;
    }
}