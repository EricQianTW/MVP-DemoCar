package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfo extends Message{
    
    @Expose
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList){
        this. orderItemList = orderItemList;
    }
    
    @Expose
    private GoodsShop goodsShop;

    public GoodsShop getGoodsShop(){
        return goodsShop;
    }

    public void setGoodsShop(GoodsShop goodsShop){
        this. goodsShop = goodsShop;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String customerMessage;

    public String getCustomerMessage(){
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage){
        this. customerMessage = customerMessage;
    }
    
    @Expose
    private BigDecimal subtotal;

    public BigDecimal getSubtotal(){
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal){
        this. subtotal = subtotal;
    }
    
    @Expose
    private String postage;

    public String getPostage(){
        return postage;
    }

    public void setPostage(String postage){
        this. postage = postage;
    }
    
    public OrderInfo(){
    }

    public OrderInfo(List<OrderItem> orderItemList,GoodsShop goodsShop,Integer id,String customerMessage,BigDecimal subtotal,String postage){
        this.orderItemList=orderItemList;
        this.goodsShop=goodsShop;
        this.id=id;
        this.customerMessage=customerMessage;
        this.subtotal=subtotal;
        this.postage=postage;
    }
}