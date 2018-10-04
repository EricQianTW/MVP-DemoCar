package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by CZP on 2016/7/4.
 */
public class MsgPackageDetail extends Message {

    @Expose
    private List<MsgPackageGoodsDetail> MsgPackageGoodsList;

    @Expose
    private int Id;

    @Expose
    private String packageName;

    @Expose
    private BigDecimal packagePrice;

    public List<MsgPackageGoodsDetail> getMsgPackageGoodsList() {
        return MsgPackageGoodsList;
    }

    public void setMsgPackageGoodsList(List<MsgPackageGoodsDetail> msgPackageGoodsList) {
        MsgPackageGoodsList = msgPackageGoodsList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public BigDecimal getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(BigDecimal packagePrice) {
        this.packagePrice = packagePrice;
    }
}
