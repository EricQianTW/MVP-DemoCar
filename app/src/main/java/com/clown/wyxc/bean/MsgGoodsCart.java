package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by eric_qiantw on 16/6/10.
 * 购物车画面商品详细对象
 */
public class MsgGoodsCart extends Message{

    // 带属性的产品编号
    @Expose
    private int goodsStockId;
    // 产品总编号
    @Expose
    private int goodsId;
    // 购物车数量
    @Expose
    private int goodsNum;
    // 带属性的产品编号
    @Expose
    private int userId;
    // 带属性的产品编号
    @Expose
    private BigDecimal goodsPrice;
    // 带属性的产品编号
    @Expose
    private String goodsName;
    // 带属性的产品编号
    @Expose
    private String imagePath;
    // 带属性的产品编号
    @Expose
    private String attrValue;
    // 带属性的产品编号
    @Expose
    private int shopCartId;
    // 商品是否被勾选
    @Expose
    private boolean isChecked;
    @Expose
    private String goodsSpec;

    @Expose
    private int canbuyNum;

    @Expose
    private BigDecimal oldPrice;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(int shopCartId) {
        this.shopCartId = shopCartId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
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
