package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgOrderGoodsItem extends Message {
    // 邮费
    @Expose
    private BigDecimal postage;
    // 服务列表
    @Expose
    private List<MsgService> serviceList;
    // 个人编号
    @Expose
    private int userId;
    // 产品总编号
    @Expose
    private int goodsId;
    // 产品价格
    @Expose
    private BigDecimal goodsPrice;
    // 产品名称
    @Expose
    private String goodsName;
    // 产品图片
    @Expose
    private String imageName;
    // 产品属性
    @Expose
    private String attrValue;
    // 购物车编号
    @Expose
    private int shopCardId;
    // 产品所带属性的编号
    @Expose
    private int goodsStockId;
    // 购买数量
    @Expose
    private int goodsNum;

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public List<MsgService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<MsgService> serviceList) {
        this.serviceList = serviceList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public int getShopCardId() {
        return shopCardId;
    }

    public void setShopCardId(int shopCardId) {
        this.shopCardId = shopCardId;
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
}
