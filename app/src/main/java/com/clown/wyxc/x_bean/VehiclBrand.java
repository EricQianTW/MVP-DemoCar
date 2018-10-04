package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class VehiclBrand extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer carInfoId;

    public Integer getCarInfoId(){
        return carInfoId;
    }

    public void setCarInfoId(Integer carInfoId){
        this. carInfoId = carInfoId;
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
    private String logo;

    public String getLogo(){
        return logo;
    }

    public void setLogo(String logo){
        this. logo = logo;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    @Expose
    private Integer isView;

    public Integer getIsView(){
        return isView;
    }

    public void setIsView(Integer isView){
        this. isView = isView;
    }
    
    public VehiclBrand(){
    }

    public VehiclBrand(Integer id,Integer carInfoId,String name,String logo,Integer sort,Integer isView){
        this.id=id;
        this.carInfoId=carInfoId;
        this.name=name;
        this.logo=logo;
        this.sort=sort;
        this.isView=isView;
    }
}