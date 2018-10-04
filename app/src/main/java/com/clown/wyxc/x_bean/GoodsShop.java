package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsShop extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String shopName;

    public String getShopName(){
        return shopName;
    }

    public void setShopName(String shopName){
        this. shopName = shopName;
    }
    
    @Expose
    private String provName;

    public String getProvName(){
        return provName;
    }

    public void setProvName(String provName){
        this. provName = provName;
    }
    
    @Expose
    private String cityName;

    public String getCityName(){
        return cityName;
    }

    public void setCityName(String cityName){
        this. cityName = cityName;
    }
    
    @Expose
    private String regionName;

    public String getRegionName(){
        return regionName;
    }

    public void setRegionName(String regionName){
        this. regionName = regionName;
    }
    
    @Expose
    private String adCode;

    public String getAdCode(){
        return adCode;
    }

    public void setAdCode(String adCode){
        this. adCode = adCode;
    }
    
    @Expose
    private String address;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this. address = address;
    }
    
    @Expose
    private String tel;

    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this. tel = tel;
    }
    
    @Expose
    private String headPic;

    public String getHeadPic(){
        return headPic;
    }

    public void setHeadPic(String headPic){
        this. headPic = headPic;
    }
    
    public GoodsShop(){
    }

    public GoodsShop(Integer id,String shopName,String provName,String cityName,String regionName,String adCode,String address,String tel,String headPic){
        this.id=id;
        this.shopName=shopName;
        this.provName=provName;
        this.cityName=cityName;
        this.regionName=regionName;
        this.adCode=adCode;
        this.address=address;
        this.tel=tel;
        this.headPic=headPic;
    }
}