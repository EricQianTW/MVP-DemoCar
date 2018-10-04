package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class GoodsCart extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private Integer goodsId;

    public Integer getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(Integer goodsId){
        this. goodsId = goodsId;
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
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
    }
    
    @Expose
    private BigDecimal actualGoodsPrice;

    public BigDecimal getActualGoodsPrice(){
        return actualGoodsPrice;
    }

    public void setActualGoodsPrice(BigDecimal actualGoodsPrice){
        this. actualGoodsPrice = actualGoodsPrice;
    }
    
    @Expose
    private String actualGoodsPic;

    public String getActualGoodsPic(){
        return actualGoodsPic;
    }

    public void setActualGoodsPic(String actualGoodsPic){
        this. actualGoodsPic = actualGoodsPic;
    }
    
    @Expose
    private String attrValue;

    public String getAttrValue(){
        return attrValue;
    }

    public void setAttrValue(String attrValue){
        this. attrValue = attrValue;
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
    private Integer canbuyNum;

    public Integer getCanbuyNum(){
        return canbuyNum;
    }

    public void setCanbuyNum(Integer canbuyNum){
        this. canbuyNum = canbuyNum;
    }
    
    @Expose
    private BigDecimal oldPrice;

    public BigDecimal getOldPrice(){
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice){
        this. oldPrice = oldPrice;
    }

    // 商品是否被勾选
    @Expose
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public GoodsCart(){
    }

    public GoodsCart(Integer id,Integer userId,Integer goodsId,String goodsName,Integer actualGoodsId,BigDecimal actualGoodsPrice,String actualGoodsPic,String attrValue,Integer goodsNum,Integer canbuyNum,BigDecimal oldPrice){
        this.id=id;
        this.userId=userId;
        this.goodsId=goodsId;
        this.goodsName=goodsName;
        this.actualGoodsId=actualGoodsId;
        this.actualGoodsPrice=actualGoodsPrice;
        this.actualGoodsPic=actualGoodsPic;
        this.attrValue=attrValue;
        this.goodsNum=goodsNum;
        this.canbuyNum=canbuyNum;
        this.oldPrice=oldPrice;
    }
}