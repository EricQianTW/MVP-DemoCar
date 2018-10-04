package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by JokerEric on 2016/7/4.
 */
public class MsgReturnWeixin extends Message {
    @Expose
    private Integer Id;
    @Expose
    private String appId;
    @Expose
    private String timeStamp;
    @Expose
    private String partnerId;
    @Expose
    private String nonceStr;
    @Expose
    private String wx_package;
    @Expose
    private String prepayId;
    @Expose
    private String signType;
    @Expose
    private String sign;
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
    public String getNonceStr() {
        return nonceStr;
    }
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
    public String getWx_package() {
        return wx_package;
    }
    public void setWx_package(String wx_package) {
        this.wx_package = wx_package;
    }
    public String getPrepayId() {
        return prepayId;
    }
    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
    public String getSignType() {
        return signType;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }

}
