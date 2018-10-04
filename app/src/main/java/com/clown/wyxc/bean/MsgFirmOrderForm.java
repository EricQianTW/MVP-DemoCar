package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgFirmOrderForm extends Message {

    // 订单地址
    @Expose
    private MsgAddressInfo orderReceiverInfo;
    // 订单店信息
    @Expose
    private List<MsgOrderInfo> orderinfo;
    // 商城优惠券
    @Expose
    private List<MsgOrderCoupon> mallCoupon;
    // 订单金额
    @Expose
    private BigDecimal orderPrice;

    public MsgAddressInfo getOrderReceiverInfo() {
        return orderReceiverInfo;
    }

    public void setOrderReceiverInfo(MsgAddressInfo orderReceiverInfo) {
        this.orderReceiverInfo = orderReceiverInfo;
    }

    public List<MsgOrderInfo> getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(List<MsgOrderInfo> orderinfo) {
        this.orderinfo = orderinfo;
    }

    public List<MsgOrderCoupon> getMallCoupon() {
        return mallCoupon;
    }

    public void setMallCoupon(List<MsgOrderCoupon> mallCoupon) {
        this.mallCoupon = mallCoupon;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
