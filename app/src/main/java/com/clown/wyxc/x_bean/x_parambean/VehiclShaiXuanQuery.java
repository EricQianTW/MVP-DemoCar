package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.VehiclBrand;
import com.clown.wyxc.x_bean.VehiclPriceRangeResult;
import com.google.gson.annotations.Expose;
public class VehiclShaiXuanQuery extends Message{ 
    
    @Expose
    private VehiclBrand vehiclBrand;

    public VehiclBrand getVehiclBrand(){
        return vehiclBrand;
    }

    public void setVehiclBrand(VehiclBrand vehiclBrand){
        this. vehiclBrand = vehiclBrand;
    }
    
    @Expose
    private VehiclPriceRangeResult vehiclPriceRangeResult;

    public VehiclPriceRangeResult getVehiclPriceRangeResult(){
        return vehiclPriceRangeResult;
    }

    public void setVehiclPriceRangeResult(VehiclPriceRangeResult vehiclPriceRangeResult){
        this. vehiclPriceRangeResult = vehiclPriceRangeResult;
    }
    
    @Expose
    private Integer orderCriteria;

    public Integer getOrderCriteria(){
        return orderCriteria;
    }

    public void setOrderCriteria(Integer orderCriteria){
        this. orderCriteria = orderCriteria;
    }
    
    @Expose
    private Integer isPositive;

    public Integer getIsPositive(){
        return isPositive;
    }

    public void setIsPositive(Integer isPositive){
        this. isPositive = isPositive;
    }
    
    @Expose
    private Integer pageIndex;

    public Integer getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex){
        this. pageIndex = pageIndex;
    }
    
    public VehiclShaiXuanQuery(){
    }

    public VehiclShaiXuanQuery(VehiclBrand vehiclBrand,VehiclPriceRangeResult vehiclPriceRangeResult,Integer orderCriteria,Integer isPositive,Integer pageIndex){
        this.vehiclBrand=vehiclBrand;
        this.vehiclPriceRangeResult=vehiclPriceRangeResult;
        this.orderCriteria=orderCriteria;
        this.isPositive=isPositive;
        this.pageIndex=pageIndex;
    }
}