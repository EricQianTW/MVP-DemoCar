package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class UpdatePayPasswordQuery extends Message{ 
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    @Expose
    private String password;

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this. password = password;
    }
    
    @Expose
    private String code;

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this. code = code;
    }
    
    public UpdatePayPasswordQuery(){
    }

    public UpdatePayPasswordQuery(String phone,String password,String code){
        this.phone=phone;
        this.password=password;
        this.code=code;
    }
}