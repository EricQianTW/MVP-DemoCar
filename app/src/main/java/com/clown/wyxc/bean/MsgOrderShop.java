package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgOrderShop extends Message {
    // 商店商品列表
    @Expose
    private List<MsgOrderGoodsItem> orderGoodsItemList;
    // 商店可有优惠券列表
    @Expose
    private List<MsgOrderCoupon> shopCouponList;
    // 商店小计（小订单小计）
    @Expose
    private BigDecimal subtotal;
    // 该店邮费（小订单邮费）
    @Expose
    private BigDecimal postage;
    @Expose
    private int Id;
    @Expose
    private int level;
    @Expose
    private String shopName;
    @Expose
    private String shopImage;
    @Expose
    private String phoneNum;

    public List<MsgOrderGoodsItem> getOrderGoodsItemList() {
        return orderGoodsItemList;
    }

    public void setOrderGoodsItemList(List<MsgOrderGoodsItem> orderGoodsItemList) {
        this.orderGoodsItemList = orderGoodsItemList;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<MsgOrderCoupon> getShopCouponList() {
        return shopCouponList;
    }

    public void setShopCouponList(List<MsgOrderCoupon> shopCouponList) {
        this.shopCouponList = shopCouponList;
    }
}
