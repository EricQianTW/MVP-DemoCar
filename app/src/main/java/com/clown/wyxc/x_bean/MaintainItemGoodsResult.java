package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class MaintainItemGoodsResult extends Message{
    
    @Expose
    private Integer maintainItemGoodsId;

    public Integer getMaintainItemGoodsId(){
        return maintainItemGoodsId;
    }

    public void setMaintainItemGoodsId(Integer maintainItemGoodsId){
        this. maintainItemGoodsId = maintainItemGoodsId;
    }
    
    @Expose
    private Integer goodsId;

    public Integer getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(Integer goodsId){
        this. goodsId = goodsId;
    }
    
    @Expose
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
    }
    
    @Expose
    private String goodsPic;

    public String getGoodsPic(){
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic){
        this. goodsPic = goodsPic;
    }
    
    @Expose
    private String goodsName;

    public String getGoodsName(){
        return goodsName;
    }

    public void setGoodsName(String goodsName){
        this. goodsName = goodsName;
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
    private Integer goodsNum;

    public Integer getGoodsNum(){
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum){
        this. goodsNum = goodsNum;
    }

    @Expose
    private boolean isEditer;

    public boolean isEditer() {
        return isEditer;
    }

    public void setEditer(boolean editer) {
        isEditer = editer;
    }

    public MaintainItemGoodsResult(){
    }

    public MaintainItemGoodsResult(Integer maintainItemGoodsId, Integer goodsId, Integer actualGoodsId, String goodsPic, String goodsName, BigDecimal price, Integer canBuyNum, Integer goodsNum){
        this.maintainItemGoodsId=maintainItemGoodsId;
        this.goodsId=goodsId;
        this.actualGoodsId=actualGoodsId;
        this.goodsPic=goodsPic;
        this.goodsName=goodsName;
        this.price=price;
        this.canBuyNum=canBuyNum;
        this.goodsNum=goodsNum;
    }
}