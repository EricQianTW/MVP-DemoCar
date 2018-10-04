package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JokerEric on 2016/7/1.
 */
public class MsgOrderInfo extends Message {
    //orderinfoId
    @Expose
    private int Id;

    //订单项
    @Expose
    private List<MsgOrderItem> orderItem;

    //店家信息
    @Expose
    private MsgOrderShop orderShop;

    //备注
    @Expose
    private String remark;

    //商店小计（小订单小计）
    @Expose
    private BigDecimal subtotal;

    //该店邮费（小订单邮费）
    @Expose
    private String postage;

    //订单状态 1:待付款,2:待发货,3:待收货,4:待评价
    @Expose
    private int state;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<MsgOrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<MsgOrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public MsgOrderShop getOrderShop() {
        return orderShop;
    }

    public void setOrderShop(MsgOrderShop orderShop) {
        this.orderShop = orderShop;
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

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }
}
