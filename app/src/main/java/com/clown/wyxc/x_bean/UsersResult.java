package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

public class UsersResult extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    public UsersResult(){
    }

    public UsersResult(Integer id,String phone){
        this.id=id;
        this.phone=phone;
    }
}