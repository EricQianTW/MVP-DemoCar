package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MyCarsDefaultResult extends Message{ 
    
    @Expose
    private String ppName;

    public String getPpName(){
        return ppName;
    }

    public void setPpName(String ppName){
        this. ppName = ppName;
    }
    
    @Expose
    private String carCXName;

    public String getCarCXName(){
        return carCXName;
    }

    public void setCarCXName(String carCXName){
        this. carCXName = carCXName;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer ppId;

    public Integer getPpId(){
        return ppId;
    }

    public void setPpId(Integer ppId){
        this. ppId = ppId;
    }
    
    @Expose
    private Integer cId;

    public Integer getCId(){
        return cId;
    }

    public void setCId(Integer cId){
        this. cId = cId;
    }
    
    @Expose
    private Integer cxId;

    public Integer getCxId(){
        return cxId;
    }

    public void setCxId(Integer cxId){
        this. cxId = cxId;
    }
    
    @Expose
    private Integer carId;

    public Integer getCarId(){
        return carId;
    }

    public void setCarId(Integer carId){
        this. carId = carId;
    }
    
    @Expose
    private String yearType;

    public String getYearType(){
        return yearType;
    }

    public void setYearType(String yearType){
        this. yearType = yearType;
    }
    
    @Expose
    private Integer km;

    public Integer getKm(){
        return km;
    }

    public void setKm(Integer km){
        this. km = km;
    }
    
    @Expose
    private String carNo;

    public String getCarNo(){
        return carNo;
    }

    public void setCarNo(String carNo){
        this. carNo = carNo;
    }
    
    @Expose
    private String carYear;

    public String getCarYear(){
        return carYear;
    }

    public void setCarYear(String carYear){
        this. carYear = carYear;
    }
    
    @Expose
    private String engineNo;

    public String getEngineNo(){
        return engineNo;
    }

    public void setEngineNo(String engineNo){
        this. engineNo = engineNo;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer isDefault;

    public Integer getIsDefault(){
        return isDefault;
    }

    public void setIsDefault(Integer isDefault){
        this. isDefault = isDefault;
    }
    
    public MyCarsDefaultResult(){
    }

    public MyCarsDefaultResult(String ppName,String carCXName,Integer id,Integer ppId,Integer cId,Integer cxId,Integer carId,String yearType,Integer km,String carNo,String carYear,String engineNo,Integer userId,Integer isDefault){
        this.ppName=ppName;
        this.carCXName=carCXName;
        this.id=id;
        this.ppId=ppId;
        this.cId=cId;
        this.cxId=cxId;
        this.carId=carId;
        this.yearType=yearType;
        this.km=km;
        this.carNo=carNo;
        this.carYear=carYear;
        this.engineNo=engineNo;
        this.userId=userId;
        this.isDefault=isDefault;
    }
}