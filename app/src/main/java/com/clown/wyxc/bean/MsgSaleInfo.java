package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by JokerEric on 2016/6/21.
 */
public class MsgSaleInfo extends Message {

    @Expose
    private int Id;
    @Expose
    private String goodsName;
    @Expose
    private BigDecimal goodsPrice;
    @Expose
    private String goodsImage;
    @Expose
    private int sellStock;
    @Expose
    private String brand;
    @Expose
    private int totalStock;
    //库存id
    @Expose
    private int goodsStockId;

    //原价
    @Expose
    private BigDecimal oldPrice;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getSellStock() {
        return sellStock;
    }

    public void setSellStock(int sellStock) {
        this.sellStock = sellStock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getGoodsStockId() {
        return goodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        this.goodsStockId = goodsStockId;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }
}
