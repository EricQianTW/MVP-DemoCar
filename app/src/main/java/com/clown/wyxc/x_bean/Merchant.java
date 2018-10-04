package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class Merchant extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private BigDecimal longitude;

    public BigDecimal getLongitude(){
        return longitude;
    }

    public void setLongitude(BigDecimal longitude){
        this. longitude = longitude;
    }
    
    @Expose
    private BigDecimal latitude;

    public BigDecimal getLatitude(){
        return latitude;
    }

    public void setLatitude(BigDecimal latitude){
        this. latitude = latitude;
    }
    
    @Expose
    private String type;

    public String getType(){
        return type;
    }

    public void setType(String type){
        this. type = type;
    }
    
    @Expose
    private BigDecimal point;

    public BigDecimal getPoint(){
        return point;
    }

    public void setPoint(BigDecimal point){
        this. point = point;
    }
    
    @Expose
    private Integer dealCount;

    public Integer getDealCount(){
        return dealCount;
    }

    public void setDealCount(Integer dealCount){
        this. dealCount = dealCount;
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
    private String zipCode;

    public String getZipCode(){
        return zipCode;
    }

    public void setZipCode(String zipCode){
        this. zipCode = zipCode;
    }
    
    @Expose
    private Integer state;

    public Integer getState(){
        return state;
    }

    public void setState(Integer state){
        this. state = state;
    }
    
    @Expose
    private String businessBeginTime;

    public String getBusinessBeginTime(){
        return businessBeginTime;
    }

    public void setBusinessBeginTime(String businessBeginTime){
        this. businessBeginTime = businessBeginTime;
    }
    
    @Expose
    private String businessEndTime;

    public String getBusinessEndTime(){
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime){
        this. businessEndTime = businessEndTime;
    }
    
    @Expose
    private Integer businessState;

    public Integer getBusinessState(){
        return businessState;
    }

    public void setBusinessState(Integer businessState){
        this. businessState = businessState;
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
    private List<Banner> merchantPics;

    public List<Banner> getMerchantPics(){
        return merchantPics;
    }

    public void setMerchantPics(List<Banner> merchantPics){
        this. merchantPics = merchantPics;
    }
    
    public Merchant(){
    }

    public Merchant(Integer id,String name,String adCode,String address,String tel,BigDecimal longitude,BigDecimal latitude,String type
            ,BigDecimal point,Integer dealCount,Integer sort,String zipCode,Integer state,String businessBeginTime,String businessEndTime
            ,Integer businessState,String provName,String cityName,String regionName){
        this.id=id;
        this.name=name;
        this.adCode=adCode;
        this.address=address;
        this.tel=tel;
        this.longitude=longitude;
        this.latitude=latitude;
        this.type=type;
        this.point=point;
        this.dealCount=dealCount;
        this.sort=sort;
        this.zipCode=zipCode;
        this.state=state;
        this.businessBeginTime=businessBeginTime;
        this.businessEndTime=businessEndTime;
        this.businessState=businessState;
        this.provName=provName;
        this.cityName=cityName;
        this.regionName=regionName;
    }
}