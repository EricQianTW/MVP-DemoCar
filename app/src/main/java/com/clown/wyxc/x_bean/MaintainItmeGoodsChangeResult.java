package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class MaintainItmeGoodsChangeResult extends Message{
    
    @Expose
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
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
    private String pic;

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this. pic = pic;
    }
    
    @Expose
    private BigDecimal price;

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this. price = price;
    }
    
    public MaintainItmeGoodsChangeResult(){
    }

    public MaintainItmeGoodsChangeResult(Integer actualGoodsId,String name,String pic,BigDecimal price){
        this.actualGoodsId=actualGoodsId;
        this.name=name;
        this.pic=pic;
        this.price=price;
    }
}