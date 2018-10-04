package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsIdQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer goodsId;

    public Integer getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(Integer goodsId){
        this. goodsId = goodsId;
    }
    
    @Expose
    private String adCode;

    public String getAdCode(){
        return adCode;
    }

    public void setAdCode(String adCode){
        this. adCode = adCode;
    }
    
    public GoodsIdQuery(){
    }

    public GoodsIdQuery(Integer userId, Integer goodsId, String adCode){
        this.userId=userId;
        this.goodsId=goodsId;
        this.adCode=adCode;
    }
}