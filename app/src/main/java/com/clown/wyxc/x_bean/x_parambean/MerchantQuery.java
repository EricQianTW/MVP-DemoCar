package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class MerchantQuery extends Message{

    @Expose
    private Integer searchType;

    public Integer getSearchType(){
        return searchType;
    }

    public void setSearchType(Integer searchType){
        this. searchType = searchType;
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
    private Integer serviceItemsId;

    public Integer getServiceItemsId(){
        return serviceItemsId;
    }

    public void setServiceItemsId(Integer serviceItemsId){
        this. serviceItemsId = serviceItemsId;
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
    private Integer pageIndex;

    public Integer getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex){
        this. pageIndex = pageIndex;
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
    private String keyWord;

    public String getKeyWord(){
        return keyWord;
    }

    public void setKeyWord(String keyWord){
        this. keyWord = keyWord;
    }

    public MerchantQuery(){
    }

    public MerchantQuery(Integer searchType, BigDecimal longitude, BigDecimal latitude, Integer serviceItemsId, String zipCode, Integer pageIndex, Integer userId, String keyWord){
        this.searchType=searchType;
        this.longitude=longitude;
        this.latitude=latitude;
        this.serviceItemsId=serviceItemsId;
        this.zipCode=zipCode;
        this.pageIndex=pageIndex;
        this.userId=userId;
        this.keyWord=keyWord;
    }

    public MerchantQuery(Integer searchtype,BigDecimal longitude,BigDecimal latitude,Integer serviceitemsid,String zipcode,Integer pageindex,String keyword){
        this.searchType=searchtype;
        this.longitude=longitude;
        this.latitude=latitude;
        this.serviceItemsId=serviceitemsid;
        this.zipCode=zipcode;
        this.pageIndex=pageindex;
        this.keyWord=keyword;
    }

    public MerchantQuery(BigDecimal longitude,BigDecimal latitude,String zipcode,Integer pageindex,Integer userid){
        this.longitude=longitude;
        this.latitude=latitude;
        this.zipCode=zipcode;
        this.pageIndex=pageindex;
        this.userId=userid;
    }

    public MerchantQuery(BigDecimal longitude,BigDecimal latitude,String zipcode,Integer pageindex){
        this.longitude=longitude;
        this.latitude=latitude;
        this.zipCode=zipcode;
        this.pageIndex=pageindex;
    }

    public MerchantQuery(Integer searchtype,String zipcode,Integer pageindex,Integer userid){
        this.searchType=searchtype;
        this.zipCode=zipcode;
        this.pageIndex=pageindex;
        this.userId=userid;
    }

    public MerchantQuery(Integer searchtype,String zipcode,Integer pageindex){
        this.searchType=searchtype;
        this.zipCode=zipcode;
        this.pageIndex=pageindex;
    }
}