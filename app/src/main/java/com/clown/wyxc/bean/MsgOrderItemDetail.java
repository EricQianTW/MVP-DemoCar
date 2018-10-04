package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by cc on 2016/8/3.
 */
public class MsgOrderItemDetail extends Message {

    @Expose
    private List<MsgAdsInpage> orderItemDetailButton ;

    //
    @Expose
    private int Id;

    //
    @Expose
    private int goodsId;

    //
    @Expose
    private BigDecimal goodsPrice;

    //
    @Expose
    private String goodsName;

    //
    @Expose
    private String imagePath;

    //
    @Expose
    private String attrValue;

    //
    @Expose
    private int goodsStockId;

    //
    @Expose
    private int goodsNum;

    //
    @Expose
    private int canbuyNum;

    //
    @Expose
    private BigDecimal oldPrice;

    public List<MsgAdsInpage> getOrderItemDetailButton() {
        return orderItemDetailButton;
    }

    public void setOrderItemDetailButton(List<MsgAdsInpage> orderItemDetailButton) {
        this.orderItemDetailButton = orderItemDetailButton;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public int getGoodsStockId() {
        return goodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        this.goodsStockId = goodsStockId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getCanbuyNum() {
        return canbuyNum;
    }

    public void setCanbuyNum(int canbuyNum) {
        this.canbuyNum = canbuyNum;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }
}
