package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by JokerEric on 2016/7/3.
 */
public class MsgOrderPay extends Message {

    @Expose
    private String orderGuid;

    @Expose
    private int payPathId;

    @Expose
    private String payPathName;

    @Expose
    private BigDecimal dispayPayAmt;

    @Expose
    private BigDecimal dispayPayMyAmt;

    @Expose
    private String memo;

    @Expose
    private String IconImage;

    public String getOrderGuid() {
        return orderGuid;
    }

    public void setOrderGuid(String orderGuid) {
        this.orderGuid = orderGuid;
    }

    public int getPayPathId() {
        return payPathId;
    }

    public void setPayPathId(int payPathId) {
        this.payPathId = payPathId;
    }

    public String getPayPathName() {
        return payPathName;
    }

    public void setPayPathName(String payPathName) {
        this.payPathName = payPathName;
    }

    public BigDecimal getDispayPayAmt() {
        return dispayPayAmt;
    }

    public void setDispayPayAmt(BigDecimal dispayPayAmt) {
        this.dispayPayAmt = dispayPayAmt;
    }

    public BigDecimal getDispayPayMyAmt() {
        return dispayPayMyAmt;
    }

    public void setDispayPayMyAmt(BigDecimal dispayPayMyAmt) {
        this.dispayPayMyAmt = dispayPayMyAmt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getIconImage() {
        return IconImage;
    }

    public void setIconImage(String iconImage) {
        IconImage = iconImage;
    }
}
