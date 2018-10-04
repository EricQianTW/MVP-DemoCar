package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by CZP on 2016/7/17.
 */
public class MsgAfterSaleDetail extends Message {

    @Expose
    private List<MsgAfterSaleLog> afterSaleLog;

    @Expose
    private String afterSaleNo;

    @Expose
    private String creatTime;

    @Expose
    private String lastEditTime;

    @Expose
    private MsgPurchasedItem saleInfo;

    @Expose
    private MsgShopInfo shopInfo;

    @Expose
    private int state;

    @Expose
    private int type;

    public List<MsgAfterSaleLog> getAfterSaleLog() {
        return afterSaleLog;
    }

    public void setAfterSaleLog(List<MsgAfterSaleLog> afterSaleLog) {
        this.afterSaleLog = afterSaleLog;
    }

    public String getAfterSaleNo() {
        return afterSaleNo;
    }

    public void setAfterSaleNo(String afterSaleNo) {
        this.afterSaleNo = afterSaleNo;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(String lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public MsgPurchasedItem getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(MsgPurchasedItem saleInfo) {
        this.saleInfo = saleInfo;
    }

    public MsgShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(MsgShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
