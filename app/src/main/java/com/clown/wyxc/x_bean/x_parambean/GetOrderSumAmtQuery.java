package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GetOrderSumAmtQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String payOrderNO;

    public String getPayOrderNO(){
        return payOrderNO;
    }

    public void setPayOrderNO(String payOrderNO){
        this. payOrderNO = payOrderNO;
    }
    
    public GetOrderSumAmtQuery(){
    }

    public GetOrderSumAmtQuery(Integer userId,String payOrderNO){
        this.userId=userId;
        this.payOrderNO=payOrderNO;
    }
}