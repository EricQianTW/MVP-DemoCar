package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.PayPathResult;
import com.google.gson.annotations.Expose;

import java.util.List;

public class OrderPayQuery extends Message{
    
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
    
    @Expose
    private List<PayPathResult> payPathResultList;

    public List<PayPathResult> getPayPathResultList(){
        return payPathResultList;
    }

    public void setPayPathResultList(List<PayPathResult> payPathResultList){
        this. payPathResultList = payPathResultList;
    }
    
    @Expose
    private String payPassword;

    public String getPayPassword(){
        return payPassword;
    }

    public void setPayPassword(String payPassword){
        this. payPassword = payPassword;
    }
    
    public OrderPayQuery(){
    }

    public OrderPayQuery(Integer userId,String payOrderNO,List<PayPathResult> payPathResultList,String payPassword){
        this.userId=userId;
        this.payOrderNO=payOrderNO;
        this.payPathResultList=payPathResultList;
        this.payPassword=payPassword;
    }
}