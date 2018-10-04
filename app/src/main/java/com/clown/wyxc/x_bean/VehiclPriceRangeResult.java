package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class VehiclPriceRangeResult extends Message{ 
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private String value;

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this. value = value;
    }
    
    public VehiclPriceRangeResult(){
    }

    public VehiclPriceRangeResult(String name,String value){
        this.name=name;
        this.value=value;
    }
}