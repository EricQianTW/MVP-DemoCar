package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class GoodsResult extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String goodsShopName;

    public String getGoodsShopName(){
        return goodsShopName;
    }

    public void setGoodsShopName(String goodsShopName){
        this. goodsShopName = goodsShopName;
    }
    
    @Expose
    private String goodsAddress;

    public String getGoodsAddress(){
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress){
        this. goodsAddress = goodsAddress;
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
    private BigDecimal minPrice;

    public BigDecimal getMinPrice(){
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice){
        this. minPrice = minPrice;
    }
    
    @Expose
    private String firstPic;

    public String getFirstPic(){
        return firstPic;
    }

    public void setFirstPic(String firstPic){
        this. firstPic = firstPic;
    }
    
    @Expose
    private Integer sellStock;

    public Integer getSellStock(){
        return sellStock;
    }

    public void setSellStock(Integer sellStock){
        this. sellStock = sellStock;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    public GoodsResult(){
    }

    public GoodsResult(Integer id,String goodsShopName,String goodsAddress,String name,BigDecimal minPrice,String firstPic,Integer sellStock,Integer sort){
        this.id=id;
        this.goodsShopName=goodsShopName;
        this.goodsAddress=goodsAddress;
        this.name=name;
        this.minPrice=minPrice;
        this.firstPic=firstPic;
        this.sellStock=sellStock;
        this.sort=sort;
    }
}