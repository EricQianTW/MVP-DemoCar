package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MaintainItemGoodsChangeQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer maintainItemId;

    public Integer getMaintainItemId(){
        return maintainItemId;
    }

    public void setMaintainItemId(Integer maintainItemId){
        this. maintainItemId = maintainItemId;
    }
    
    @Expose
    private Integer maintainItemGoodsId;

    public Integer getMaintainItemGoodsId(){
        return maintainItemGoodsId;
    }

    public void setMaintainItemGoodsId(Integer maintainItemGoodsId){
        this. maintainItemGoodsId = maintainItemGoodsId;
    }
    
    public MaintainItemGoodsChangeQuery(){
    }

    public MaintainItemGoodsChangeQuery(Integer userId,Integer maintainItemId,Integer maintainItemGoodsId){
        this.userId=userId;
        this.maintainItemId=maintainItemId;
        this.maintainItemGoodsId=maintainItemGoodsId;
    }
}