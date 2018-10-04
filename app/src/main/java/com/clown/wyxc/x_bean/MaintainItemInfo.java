package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class MaintainItemInfo extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String maintainItemName;

    public String getMaintainItemName(){
        return maintainItemName;
    }

    public void setMaintainItemName(String maintainItemName){
        this. maintainItemName = maintainItemName;
    }
    
    @Expose
    private BigDecimal maintainItemPrice;

    public BigDecimal getMaintainItemPrice(){
        return maintainItemPrice;
    }

    public void setMaintainItemPrice(BigDecimal maintainItemPrice){
        this. maintainItemPrice = maintainItemPrice;
    }
    
    @Expose
    private String pic;

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this. pic = pic;
    }
    
    @Expose
    private String simpleDetail;

    public String getSimpleDetail(){
        return simpleDetail;
    }

    public void setSimpleDetail(String simpleDetail){
        this. simpleDetail = simpleDetail;
    }
    
    @Expose
    private Integer buyNum;

    public Integer getBuyNum(){
        return buyNum;
    }

    public void setBuyNum(Integer buyNum){
        this. buyNum = buyNum;
    }
    
    public MaintainItemInfo(){
    }

    public MaintainItemInfo(Integer id,String maintainItemName,BigDecimal maintainItemPrice,String pic,String simpleDetail,Integer buyNum){
        this.id=id;
        this.maintainItemName=maintainItemName;
        this.maintainItemPrice=maintainItemPrice;
        this.pic=pic;
        this.simpleDetail=simpleDetail;
        this.buyNum=buyNum;
    }
}