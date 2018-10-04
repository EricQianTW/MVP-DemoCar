package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.FirmOrderFormResult;
import com.google.gson.annotations.Expose;
public class CreateOrderPayQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private FirmOrderFormResult firmOrderFormResult;

    public FirmOrderFormResult getFirmOrderFormResult(){
        return firmOrderFormResult;
    }

    public void setFirmOrderFormResult(FirmOrderFormResult firmOrderFormResult){
        this. firmOrderFormResult = firmOrderFormResult;
    }
    
    public CreateOrderPayQuery(){
    }

    public CreateOrderPayQuery(Integer userId,FirmOrderFormResult firmOrderFormResult){
        this.userId=userId;
        this.firmOrderFormResult=firmOrderFormResult;
    }
}