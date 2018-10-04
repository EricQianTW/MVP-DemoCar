package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MyCarsResult extends Message{

    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private String carNo;

    public String getCarNo(){
        return carNo;
    }

    public void setCarNo(String carNo){
        this. carNo = carNo;
    }

    @Expose
    private String ppName;

    public String getPpName(){
        return ppName;
    }

    public void setPpName(String ppName){
        this. ppName = ppName;
    }

    @Expose
    private String logo;

    public String getLogo(){
        return logo;
    }

    public void setLogo(String logo){
        this. logo = logo;
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
    private Integer isDefault;

    public Integer getIsDefault(){
        return isDefault;
    }

    public void setIsDefault(Integer isDefault){
        this. isDefault = isDefault;
    }

    public MyCarsResult(){
    }

    public MyCarsResult(Integer id,Integer carId,String carNo,String ppName,String logo,String carCXName,Integer isDefault){
        this.id=id;
        this.carId=carId;
        this.carNo=carNo;
        this.ppName=ppName;
        this.logo=logo;
        this.carCXName=carCXName;
        this.isDefault=isDefault;
    }
}