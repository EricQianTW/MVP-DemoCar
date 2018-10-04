package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class OrderDeliveryAddressQuery extends Message{
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String guidgoodsOrderNO;

    public String getGuidgoodsOrderNO(){
        return guidgoodsOrderNO;
    }

    public void setGuidgoodsOrderNO(String guidgoodsOrderNO){
        this. guidgoodsOrderNO = guidgoodsOrderNO;
    }
    
    @Expose
    private InModel inModel;

    public InModel getInModel(){
        return inModel;
    }

    public void setInModel(InModel inModel){
        this. inModel = inModel;
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
    
    public OrderDeliveryAddressQuery(){
    }

    public OrderDeliveryAddressQuery(Integer userId,String guidgoodsOrderNO,InModel inModel,BigDecimal longitude,BigDecimal latitude){
        this.userId=userId;
        this.guidgoodsOrderNO=guidgoodsOrderNO;
        this.inModel=inModel;
        this.longitude=longitude;
        this.latitude=latitude;
    }
}