package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class OrderShoppingCartId extends Message{ 
    
    @Expose
    private Integer shoppingCartId;

    public Integer getShoppingCartId(){
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId){
        this. shoppingCartId = shoppingCartId;
    }
    
    @Expose
    private Integer goodsNum;

    public Integer getGoodsNum(){
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum){
        this. goodsNum = goodsNum;
    }
    
    public OrderShoppingCartId(){
    }

    public OrderShoppingCartId(Integer shoppingCartId,Integer goodsNum){
        this.shoppingCartId=shoppingCartId;
        this.goodsNum=goodsNum;
    }
}