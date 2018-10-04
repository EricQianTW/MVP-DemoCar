package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by eric_shenn on 2017/3/7.
 */

public class MsgFullCut extends Message {

    // 订单价格
    @Expose
    private BigDecimal OrderPrice;

    // 全价
    @Expose
    private BigDecimal FullPrice;

    // 备注
    @Expose
    private String RemarksColumn;

    public BigDecimal getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        OrderPrice = orderPrice;
    }

    public BigDecimal getFullPrice() {
        return FullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        FullPrice = fullPrice;
    }

    public String getRemarksColumn() {
        return RemarksColumn;
    }

    public void setRemarksColumn(String remarksColumn) {
        RemarksColumn = remarksColumn;
    }
}
