package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class OrderFirmOrderAddressResult extends Message{ 
    
    @Expose
    private DeliveryAddress deliveryAddress;

    public DeliveryAddress getDeliveryAddress(){
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress){
        this. deliveryAddress = deliveryAddress;
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
    private Integer addressType;

    public Integer getAddressType(){
        return addressType;
    }

    public void setAddressType(Integer addressType){
        this. addressType = addressType;
    }
    
    public OrderFirmOrderAddressResult(){
    }

    public OrderFirmOrderAddressResult(DeliveryAddress deliveryAddress,Merchant merchant,Integer addressType){
        this.deliveryAddress=deliveryAddress;
        this.merchant=merchant;
        this.addressType=addressType;
    }
}