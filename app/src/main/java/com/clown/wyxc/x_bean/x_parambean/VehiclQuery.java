package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class VehiclQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer vehiclId;

    public Integer getVehiclId(){
        return vehiclId;
    }

    public void setVehiclId(Integer vehiclId){
        this. vehiclId = vehiclId;
    }
    
    public VehiclQuery(){
    }

    public VehiclQuery(Integer userId,Integer vehiclId){
        this.userId=userId;
        this.vehiclId=vehiclId;
    }
}