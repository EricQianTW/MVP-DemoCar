package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class MyCollectionMerchantResult extends Message{
    
    @Expose
    private String merchantName;

    public String getMerchantName(){
        return merchantName;
    }

    public void setMerchantName(String merchantName){
        this. merchantName = merchantName;
    }
    
    @Expose
    private String merchantTypeName;

    public String getMerchantTypeName(){
        return merchantTypeName;
    }

    public void setMerchantTypeName(String merchantTypeName){
        this. merchantTypeName = merchantTypeName;
    }
    
    @Expose
    private String address;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this. address = address;
    }
    
    @Expose
    private BigDecimal point;

    public BigDecimal getPoint(){
        return point;
    }

    public void setPoint(BigDecimal point){
        this. point = point;
    }
    
    public MyCollectionMerchantResult(){
    }

    public MyCollectionMerchantResult(String merchantName,String merchantTypeName,String address,BigDecimal point){
        this.merchantName=merchantName;
        this.merchantTypeName=merchantTypeName;
        this.address=address;
        this.point=point;
    }
}