package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class MsgGoodsPackage extends Message {

    //套餐ID
    @Expose
    private int Id;

    //套餐名称
    @Expose
    private String packageName;

    //套餐价格
    @Expose
    private BigDecimal packagePrice;

    //套餐包含的商品list
    @Expose
    private List<MsgSaleInfo> PackageGoodsList;

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

    public List<MsgSaleInfo> getPackageGoodsList() {
        return PackageGoodsList;
    }

    public void setPackageGoodsList(List<MsgSaleInfo> packageGoodsList) {
        PackageGoodsList = packageGoodsList;
    }
}
