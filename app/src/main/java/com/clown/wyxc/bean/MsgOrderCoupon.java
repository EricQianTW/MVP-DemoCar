package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by JokerEric on 2016/7/1.
 */
public class MsgOrderCoupon extends Message{

    //是否被选中 0:未被选中，1已被选中
    @Expose
    private int isSelected;

    //
    @Expose
    private int Id;

    //优惠券标题
    @Expose
    private String title;

    //优惠券抵扣金额
    @Expose
    private BigDecimal price;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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
