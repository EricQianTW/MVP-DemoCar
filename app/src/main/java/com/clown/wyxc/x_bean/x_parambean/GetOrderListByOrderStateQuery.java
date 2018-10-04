package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GetOrderListByOrderStateQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer pageIndex;

    public Integer getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex){
        this. pageIndex = pageIndex;
    }
    
    @Expose
    private Integer orderType;

    public Integer getOrderType(){
        return orderType;
    }

    public void setOrderType(Integer orderType){
        this. orderType = orderType;
    }
    
    @Expose
    private Integer orderState;

    public Integer getOrderState(){
        return orderState;
    }

    public void setOrderState(Integer orderState){
        this. orderState = orderState;
    }

    @Expose
    private String titleName;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public GetOrderListByOrderStateQuery(){
    }

    public GetOrderListByOrderStateQuery(Integer userId,Integer pageIndex,Integer orderType,Integer orderState){
        this.userId=userId;
        this.pageIndex=pageIndex;
        this.orderType=orderType;
        this.orderState=orderState;
    }

    public GetOrderListByOrderStateQuery(Integer userId, Integer pageIndex, Integer orderType, Integer orderState, String titleName) {
        this.userId = userId;
        this.pageIndex = pageIndex;
        this.orderType = orderType;
        this.orderState = orderState;
        this.titleName = titleName;
    }
}