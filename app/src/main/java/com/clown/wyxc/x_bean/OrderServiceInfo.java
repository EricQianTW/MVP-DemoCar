package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceInfo extends Message{
    
    @Expose
    private List<MaintainItemInfo> maintainItemInfoList;

    public List<MaintainItemInfo> getMaintainItemInfoList(){
        return maintainItemInfoList;
    }

    public void setMaintainItemInfoList(List<MaintainItemInfo> maintainItemInfoList){
        this. maintainItemInfoList = maintainItemInfoList;
    }
    
    @Expose
    private Merchant merchant;

    public Merchant getMerchant(){
        return merchant;
    }

    public void setMerchant(Merchant merchant){
        this. merchant = merchant;
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
    
    public OrderServiceInfo(){
    }

    public OrderServiceInfo(List<MaintainItemInfo> maintainItemInfoList,Merchant merchant,Integer id,String customerMessage,BigDecimal subtotal){
        this.maintainItemInfoList=maintainItemInfoList;
        this.merchant=merchant;
        this.id=id;
        this.customerMessage=customerMessage;
        this.subtotal=subtotal;
    }
}