package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.MaintainItemsResult;
import com.google.gson.annotations.Expose;

import java.util.List;

public class OrderMaintainQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer myCarsId;

    public Integer getMyCarsId(){
        return myCarsId;
    }

    public void setMyCarsId(Integer myCarsId){
        this. myCarsId = myCarsId;
    }
    
    @Expose
    private List<MaintainItemsResult> maintainItemsResultList;

    public List<MaintainItemsResult> getMaintainItemsResultList(){
        return maintainItemsResultList;
    }

    public void setMaintainItemsResultList(List<MaintainItemsResult> maintainItemsResultList){
        this. maintainItemsResultList = maintainItemsResultList;
    }
    
    public OrderMaintainQuery(){
    }

    public OrderMaintainQuery(Integer userId,Integer myCarsId,List<MaintainItemsResult> maintainItemsResultList){
        this.userId=userId;
        this.myCarsId=myCarsId;
        this.maintainItemsResultList=maintainItemsResultList;
    }
}