package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class OrderMerchantQuery extends Message{
    
    @Expose
    private String guidgoodsOrderNO;

    public String getGuidgoodsOrderNO(){
        return guidgoodsOrderNO;
    }

    public void setGuidgoodsOrderNO(String guidgoodsOrderNO){
        this. guidgoodsOrderNO = guidgoodsOrderNO;
    }
    
    @Expose
    private BigDecimal longitude;

    public BigDecimal getLongitude(){
        return longitude;
    }

    public void setLongitude(BigDecimal longitude){
        this. longitude = longitude;
    }
    
    @Expose
    private BigDecimal latitude;

    public BigDecimal getLatitude(){
        return latitude;
    }

    public void setLatitude(BigDecimal latitude){
        this. latitude = latitude;
    }
    
    public OrderMerchantQuery(){
    }

    public OrderMerchantQuery(String guidgoodsOrderNO,BigDecimal longitude,BigDecimal latitude){
        this.guidgoodsOrderNO=guidgoodsOrderNO;
        this.longitude=longitude;
        this.latitude=latitude;
    }
}