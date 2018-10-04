package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class LoginCodeQuery extends Message{ 
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    @Expose
    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this. code = code;
    }
    
    public LoginCodeQuery(){
    }

    public LoginCodeQuery(String phone,String code){
        this.phone=phone;
        this.code=code;
    }
}