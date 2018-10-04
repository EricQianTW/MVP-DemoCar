package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class LoginQuery extends Message{ 
    
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
    
    public LoginQuery(){
    }

    public LoginQuery(String phone,String password){
        this.phone=phone;
        this.password=password;
    }
}