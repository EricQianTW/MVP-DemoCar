package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class ScreenConditionsQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String keyWords;

    public String getKeyWords(){
        return keyWords;
    }

    public void setKeyWords(String keyWords){
        this. keyWords = keyWords;
    }
    
    @Expose
    private Integer goodsTypeId;

    public Integer getGoodsTypeId(){
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId){
        this. goodsTypeId = goodsTypeId;
    }
    
    public ScreenConditionsQuery(){
    }

    public ScreenConditionsQuery(Integer userId,String keyWords,Integer goodsTypeId){
        this.userId=userId;
        this.keyWords=keyWords;
        this.goodsTypeId=goodsTypeId;
    }
}