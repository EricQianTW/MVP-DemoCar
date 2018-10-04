package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by cc on 2016/8/3.
 */
public class MsgOrderInfoDetail extends Message{

    @Expose
    private MsgOrderDetailState orderStateClass;

    //
    @Expose
    private List<MsgOrderItemDetail> orderItemDetailList;

    //
    @Expose
    private MsgShopInfo shopInfo;

    //
    @Expose
    private MsgIogisticsInfo iogisticsInfo;


    @Expose
    private MsgAddressInfo addressInfo;

//
    @Expose
    private List<MsgAdsInpage> orderButtonList;

    @Expose
    private int isPackage;//0不是套餐 1是

    //
    @Expose
    private int Id;

    //
    @Expose
    private String remark;

    //
    @Expose
    private BigDecimal subtotal;

    //
    @Expose
    private String postage;

    //
    @Expose
    private int state;

    //
    @Expose
    private String orderdate;


    @Expose
    private String orderNo;


    public MsgOrderDetailState getOrderStateClass() {
        return orderStateClass;
    }

    public void setOrderStateClass(MsgOrderDetailState orderStateClass) {
        this.orderStateClass = orderStateClass;
    }

    public List<MsgOrderItemDetail> getOrderItemDetailList() {
        return orderItemDetailList;
    }

    public void setOrderItemDetailList(List<MsgOrderItemDetail> orderItemDetailList) {
        this.orderItemDetailList = orderItemDetailList;
    }

    public MsgShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(MsgShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public MsgIogisticsInfo getIogisticsInfo() {
        return iogisticsInfo;
    }

    public void setIogisticsInfo(MsgIogisticsInfo iogisticsInfo) {
        this.iogisticsInfo = iogisticsInfo;
    }

    public List<MsgAdsInpage> getOrderButtonList() {
        return orderButtonList;
    }

    public void setOrderButtonList(List<MsgAdsInpage> orderButtonList) {
        this.orderButtonList = orderButtonList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public MsgAddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(MsgAddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(int isPackage) {
        this.isPackage = isPackage;
    }
}
