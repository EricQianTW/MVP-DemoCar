package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class InModel extends Message{ 
    
    @Expose
    private Integer addressType;

    public Integer getAddressType(){
        return addressType;
    }

    public void setAddressType(Integer addressType){
        this. addressType = addressType;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    public InModel(){
    }

    public InModel(Integer addressType,Integer id){
        this.addressType=addressType;
        this.id=id;
    }
}