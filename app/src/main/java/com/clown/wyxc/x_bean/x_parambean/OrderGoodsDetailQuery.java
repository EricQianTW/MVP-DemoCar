package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class OrderGoodsDetailQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
    }
    
    @Expose
    private Integer goodsNum;

    public Integer getGoodsNum(){
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum){
        this. goodsNum = goodsNum;
    }
    
    public OrderGoodsDetailQuery(){
    }

    public OrderGoodsDetailQuery(Integer userId,Integer actualGoodsId,Integer goodsNum){
        this.userId=userId;
        this.actualGoodsId=actualGoodsId;
        this.goodsNum=goodsNum;
    }
}