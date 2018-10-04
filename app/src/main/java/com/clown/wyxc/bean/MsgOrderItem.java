package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JokerEric on 2016/7/1.
 */
public class MsgOrderItem extends Message {

    //订单明细项目id
    @Expose
    private int Id;

    //邮费
    @Expose
    private BigDecimal postage;

    //可勾选服务列表
    @Expose
    private List<MsgOrderService> optionServiceList;

    //不可勾选服务列表
    @Expose
    private List<MsgService> normalServiceList;

    //优惠券列表
    @Expose
    private List<MsgOrderCoupon> couponList;

    //产品总编号
    @Expose
    private int goodsId;

    //产品价格
    @Expose
    private BigDecimal goodsPrice;

    //产品名称
    @Expose
    private String goodsName;

    //产品图片
    @Expose
    private String imagePath;

    //产品属性
    @Expose
    private String attrValue;

    //产品库存id
    @Expose
    private int goodsStockId;

    //购买数量
    @Expose
    private int goodsNum;

    //允许购买的数量
    @Expose
    private int canbuyNum;

    //原价(旧价格)
    @Expose
    private BigDecimal oldPrice;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public List<MsgOrderService> getOptionServiceList() {
        return optionServiceList;
    }

    public void setOptionServiceList(List<MsgOrderService> optionServiceList) {
        this.optionServiceList = optionServiceList;
    }

    public List<MsgService> getNormalServiceList() {
        return normalServiceList;
    }

    public void setNormalServiceList(List<MsgService> normalServiceList) {
        this.normalServiceList = normalServiceList;
    }

    public List<MsgOrderCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<MsgOrderCoupon> couponList) {
        this.couponList = couponList;
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
