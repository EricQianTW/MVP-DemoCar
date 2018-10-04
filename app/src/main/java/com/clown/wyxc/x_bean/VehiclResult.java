package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class VehiclResult extends Message{
    
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
    private List<VehiclPic> bannerPicList;

    public List<VehiclPic> getBannerPicList(){
        return bannerPicList;
    }

    public void setBannerPicList(List<VehiclPic> bannerPicList){
        this. bannerPicList = bannerPicList;
    }
    
    @Expose
    private List<VehiclPic> detailPicList;

    public List<VehiclPic> getDetailPicList(){
        return detailPicList;
    }

    public void setDetailPicList(List<VehiclPic> detailPicList){
        this. detailPicList = detailPicList;
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
    private GoodsShare goodsShare;

    public GoodsShare getGoodsShare(){
        return goodsShare;
    }

    public void setGoodsShare(GoodsShare goodsShare){
        this. goodsShare = goodsShare;
    }
    
    public VehiclResult(){
    }

    public VehiclResult(Integer vehiclId,String name,List<VehiclPic> bannerPicList,List<VehiclPic> detailPicList,String startPrice,GoodsShare goodsShare){
        this.vehiclId=vehiclId;
        this.name=name;
        this.bannerPicList=bannerPicList;
        this.detailPicList=detailPicList;
        this.startPrice=startPrice;
        this.goodsShare=goodsShare;
    }
}