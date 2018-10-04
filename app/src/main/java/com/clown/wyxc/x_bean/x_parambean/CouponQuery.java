package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class CouponQuery extends Message{ 
    
    @Expose
    private Integer type;

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this. type = type;
    }
    
    @Expose
    private String zipCode;

    public String getZipCode(){
        return zipCode;
    }

    public void setZipCode(String zipCode){
        this. zipCode = zipCode;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    public CouponQuery(){
    }

    public CouponQuery(Integer type,String zipCode,Integer userId){
        this.type=type;
        this.zipCode=zipCode;
        this.userId=userId;
    }
}