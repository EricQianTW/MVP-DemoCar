package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class OrderShoppingCartQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private List<OrderShoppingCartId> shoppingCartList;

    public List<OrderShoppingCartId> getShoppingCartList(){
        return shoppingCartList;
    }

    public void setShoppingCartList(List<OrderShoppingCartId> shoppingCartList){
        this. shoppingCartList = shoppingCartList;
    }
    
    public OrderShoppingCartQuery(){
    }

    public OrderShoppingCartQuery(Integer userId,List<OrderShoppingCartId> shoppingCartList){
        this.userId=userId;
        this.shoppingCartList=shoppingCartList;
    }
}