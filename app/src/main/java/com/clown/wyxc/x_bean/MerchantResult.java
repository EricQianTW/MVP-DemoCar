package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class MerchantResult extends Message{
    
    @Expose
    private BigDecimal distance;

    public BigDecimal getDistance(){
        return distance;
    }

    public void setDistance(BigDecimal distance){
        this. distance = distance;
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
    private String typeName;

    public String getTypeName(){
        return typeName;
    }

    public void setTypeName(String typeName){
        this. typeName = typeName;
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
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
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
    private String businessBeginDate;

    public String getBusinessBeginDate(){
        return businessBeginDate;
    }

    public void setBusinessBeginDate(String businessBeginDate){
        this. businessBeginDate = businessBeginDate;
    }
    
    @Expose
    private String businessEndDate;

    public String getBusinessEndDate(){
        return businessEndDate;
    }

    public void setBusinessEndDate(String businessEndDate){
        this. businessEndDate = businessEndDate;
    }
    
    @Expose
    private String businessStateName;

    public String getBusinessStateName(){
        return businessStateName;
    }

    public void setBusinessStateName(String businessStateName){
        this. businessStateName = businessStateName;
    }
    
    @Expose
    private Integer isCollection;

    public Integer getIsCollection(){
        return isCollection;
    }

    public void setIsCollection(Integer isCollection){
        this. isCollection = isCollection;
    }
    
    @Expose
    private List<Banner> merchantPics;

    public List<Banner> getMerchantPics(){
        return merchantPics;
    }

    public void setMerchantPics(List<Banner> merchantPics){
        this. merchantPics = merchantPics;
    }
    
    public MerchantResult(){
    }

    public MerchantResult(BigDecimal distance,String adCode,String provName,String cityName,String regionName,String typeName,Integer id,String name,String address,String tel,BigDecimal longitude,BigDecimal latitude,BigDecimal point,Integer dealCount,String businessBeginDate,String businessEndDate,String businessStateName,Integer isCollection,List<Banner> merchantPics){
        this.distance=distance;
        this.adCode=adCode;
        this.provName=provName;
        this.cityName=cityName;
        this.regionName=regionName;
        this.typeName=typeName;
        this.id=id;
        this.name=name;
        this.address=address;
        this.tel=tel;
        this.longitude=longitude;
        this.latitude=latitude;
        this.point=point;
        this.dealCount=dealCount;
        this.businessBeginDate=businessBeginDate;
        this.businessEndDate=businessEndDate;
        this.businessStateName=businessStateName;
        this.isCollection=isCollection;
        this.merchantPics=merchantPics;
    }
}