package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class GoodsService extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String serviceTitle;

    public String getServiceTitle(){
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle){
        this. serviceTitle = serviceTitle;
    }
    
    @Expose
    private BigDecimal servicePrice;

    public BigDecimal getServicePrice(){
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice){
        this. servicePrice = servicePrice;
    }
    
    @Expose
    private String serviceContent;

    public String getServiceContent(){
        return serviceContent;
    }

    public void setServiceContent(String serviceContent){
        this. serviceContent = serviceContent;
    }
    
    @Expose
    private String serviceImage;

    public String getServiceImage(){
        return serviceImage;
    }

    public void setServiceImage(String serviceImage){
        this. serviceImage = serviceImage;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    public GoodsService(){
    }

    public GoodsService(Integer id,String serviceTitle,BigDecimal servicePrice,String serviceContent,String serviceImage,Integer sort){
        this.id=id;
        this.serviceTitle=serviceTitle;
        this.servicePrice=servicePrice;
        this.serviceContent=serviceContent;
        this.serviceImage=serviceImage;
        this.sort=sort;
    }
}