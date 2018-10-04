package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgCoupon extends Message {

    // 优惠券标题
    @Expose
    private String title;

    // 优惠券抵扣金额
    @Expose
    private BigDecimal price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
