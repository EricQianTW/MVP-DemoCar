package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class OrderDeliverPayQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private List<Integer> orderIdList;

    public List<Integer> getOrderIdList(){
        return orderIdList;
    }

    public void setOrderIdList(List<Integer> orderIdList){
        this. orderIdList = orderIdList;
    }
    
    public OrderDeliverPayQuery(){
    }

    public OrderDeliverPayQuery(Integer userId,List<Integer> orderIdList){
        this.userId=userId;
        this.orderIdList=orderIdList;
    }
}