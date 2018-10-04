package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class VehiclListResult extends Message{ 
    
    @Expose
    private Integer vehiclId;

    public Integer getVehiclId(){
        return vehiclId;
    }

    public void setVehiclId(Integer vehiclId){
        this. vehiclId = vehiclId;
    }
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private String firstPic;

    public String getFirstPic(){
        return firstPic;
    }

    public void setFirstPic(String firstPic){
        this. firstPic = firstPic;
    }
    
    @Expose
    private String startPrice;

    public String getStartPrice(){
        return startPrice;
    }

    public void setStartPrice(String startPrice){
        this. startPrice = startPrice;
    }

    @Expose
    private String endPrice;

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    @Expose
    private String erweima;

    public String getErweima(){
        return erweima;
    }

    public void setErweima(String erweima){
        this. erweima = erweima;
    }
    
    public VehiclListResult(){
    }

    public VehiclListResult(Integer vehiclId,String name,String firstPic,String startPrice,String erweima){
        this.vehiclId=vehiclId;
        this.name=name;
        this.firstPic=firstPic;
        this.startPrice=startPrice;
        this.erweima=erweima;
    }
}