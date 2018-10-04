package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.MaintainItemsResult;
import com.google.gson.annotations.Expose;
public class ChangeGoodsQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private MaintainItemsResult maintainItemsResult;

    public MaintainItemsResult getMaintainItemsResult(){
        return maintainItemsResult;
    }

    public void setMaintainItemsResult(MaintainItemsResult maintainItemsResult){
        this. maintainItemsResult = maintainItemsResult;
    }
    
    @Expose
    private Integer maintainItemGoodsId;

    public Integer getMaintainItemGoodsId(){
        return maintainItemGoodsId;
    }

    public void setMaintainItemGoodsId(Integer maintainItemGoodsId){
        this. maintainItemGoodsId = maintainItemGoodsId;
    }
    
    @Expose
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
    }
    
    public ChangeGoodsQuery(){
    }

    public ChangeGoodsQuery(Integer userId,MaintainItemsResult maintainItemsResult,Integer maintainItemGoodsId,Integer actualGoodsId){
        this.userId=userId;
        this.maintainItemsResult=maintainItemsResult;
        this.maintainItemGoodsId=maintainItemGoodsId;
        this.actualGoodsId=actualGoodsId;
    }
}