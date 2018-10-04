package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/4.
 */
public class MsgReturnZhifu extends Message{
    /**
     * 编号
     */
    @Expose
    private Integer Id;
    /**
     * 字符串
     */
    @Expose
    private String orderString;
    /**
     * 签名
     */
    @Expose
    private String sign;
    /**
     * Type
     */
    @Expose
    private String sign_Type;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_Type() {
        return sign_Type;
    }

    public void setSign_Type(String sign_Type) {
        this.sign_Type = sign_Type;
    }
}
