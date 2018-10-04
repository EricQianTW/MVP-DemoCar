package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class QRcodeQuery extends Message{ 
    
    @Expose
    private String erweima;

    public String getErweima(){
        return erweima;
    }

    public void setErweima(String erweima){
        this. erweima = erweima;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    public QRcodeQuery(){
    }

    public QRcodeQuery(String erweima,Integer userId){
        this.erweima=erweima;
        this.userId=userId;
    }
}