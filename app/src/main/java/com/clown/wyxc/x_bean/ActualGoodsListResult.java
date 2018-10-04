package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.HashMap;

public class ActualGoodsListResult extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    
    @Expose
    private Integer canBuyNum;

    public Integer getCanBuyNum(){
        return canBuyNum;
    }

    public void setCanBuyNum(Integer canBuyNum){
        this. canBuyNum = canBuyNum;
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
    private Integer stock;

    public Integer getStock(){
        return stock;
    }

    public void setStock(Integer stock){
        this. stock = stock;
    }
    
    @Expose
    private Integer pastage;

    public Integer getPastage(){
        return pastage;
    }

    public void setPastage(Integer pastage){
        this. pastage = pastage;
    }
    
    @Expose
    private BigDecimal oldPrice;

    public BigDecimal getOldPrice(){
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice){
        this. oldPrice = oldPrice;
    }
    
    @Expose
    private HashMap<String, Integer> goodsAttrDictionary;

    public HashMap<String, Integer> getGoodsAttrDictionary() {
        return goodsAttrDictionary;
    }

    public void setGoodsAttrDictionary(HashMap<String, Integer> goodsAttrDictionary) {
        this.goodsAttrDictionary = goodsAttrDictionary;
    }

    public ActualGoodsListResult(){
    }

    public ActualGoodsListResult(Integer id,String pic,BigDecimal price,Integer canBuyNum,Integer sellStock,Integer stock,Integer pastage,BigDecimal oldPrice,HashMap<String, Integer> goodsAttrDictionary){
        this.id=id;
        this.pic=pic;
        this.price=price;
        this.canBuyNum=canBuyNum;
        this.sellStock=sellStock;
        this.stock=stock;
        this.pastage=pastage;
        this.oldPrice=oldPrice;
        this.goodsAttrDictionary=goodsAttrDictionary;
    }
}