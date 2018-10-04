package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class DeleteShoppingCartQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private List<Integer> shoppingCartIds;

    public List<Integer> getShoppingCartIds(){
        return shoppingCartIds;
    }

    public void setShoppingCartIds(List<Integer> shoppingCartIds){
        this. shoppingCartIds = shoppingCartIds;
    }
    
    public DeleteShoppingCartQuery(){
    }

    public DeleteShoppingCartQuery(Integer userId,List<Integer> shoppingCartIds){
        this.shoppingCartIds=shoppingCartIds;
        this.userId=userId;
    }
}