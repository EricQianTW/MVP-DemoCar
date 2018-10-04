package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by CZP on 2016/7/16.
 */
public class MsgAfterSale extends Message {

    @Expose
    private String afterSaleNo;
    @Expose
    private String createTime;
    @Expose
    private String lastEditTime;
    @Expose
    private List<MsgPurchasedItem> saleInfoList;
    @Expose
    private MsgShopInfo shopInfo;

    @Expose
    private int state;

    @Expose
    private int type;

    public String getAfterSaleNo() {
        return afterSaleNo;
    }

    public void setAfterSaleNo(String afterSaleNo) {
        this.afterSaleNo = afterSaleNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }



    public MsgShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(MsgShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MsgPurchasedItem> getSaleInfoList() {
        return saleInfoList;
    }

    public void setSaleInfoList(List<MsgPurchasedItem> saleInfoList) {
        this.saleInfoList = saleInfoList;
    }
}
