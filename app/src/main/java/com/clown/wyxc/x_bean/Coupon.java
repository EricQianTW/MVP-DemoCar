package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class Coupon extends Message{ 
    
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
    private String beginDateTime;

    public String getBeginDateTime(){
        return beginDateTime;
    }

    public void setBeginDateTime(String beginDateTime){
        this. beginDateTime = beginDateTime;
    }
    
    @Expose
    private String endDateTime;

    public String getEndDateTime(){
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime){
        this. endDateTime = endDateTime;
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
    private Integer state;

    public Integer getState(){
        return state;
    }

    public void setState(Integer state){
        this. state = state;
    }
    
    @Expose
    private String condition;

    public String getCondition(){
        return condition;
    }

    public void setCondition(String condition){
        this. condition = condition;
    }
    
    @Expose
    private Integer discountPrice;

    public Integer getDiscountPrice(){
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice){
        this. discountPrice = discountPrice;
    }
    
    @Expose
    private String contents;

    public String getContents(){
        return contents;
    }

    public void setContents(String contents){
        this. contents = contents;
    }
    
    @Expose
    private Integer type;

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this. type = type;
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
    private String clickUrl;

    public String getClickUrl(){
        return clickUrl;
    }

    public void setClickUrl(String clickUrl){
        this. clickUrl = clickUrl;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    public Coupon(){
    }

    public Coupon(Integer id,String name,String beginDateTime,String endDateTime,String pic,Integer state,String condition,Integer discountPrice,String contents,Integer type,String zipCode,String clickUrl,Integer sort){
        this.id=id;
        this.name=name;
        this.beginDateTime=beginDateTime;
        this.endDateTime=endDateTime;
        this.pic=pic;
        this.state=state;
        this.condition=condition;
        this.discountPrice=discountPrice;
        this.contents=contents;
        this.type=type;
        this.zipCode=zipCode;
        this.clickUrl=clickUrl;
        this.sort=sort;
    }
}