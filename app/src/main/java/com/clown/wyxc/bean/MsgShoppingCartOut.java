package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgShoppingCartOut extends Message {

    // 店铺编号
    @Expose
    private int shopId;

    // 店铺名称
    @Expose
    private String shopName;

    // 店铺名称
    @Expose
    private int shopStatus = 1;

    // 购物车列表
    @Expose
    private List<MsgGoodsCart> showGoodsCart;

    // 商品是否被勾选
    @Expose
    private boolean isChecked;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<MsgGoodsCart> getShowGoodsCart() {
        return showGoodsCart;
    }

    public void setShowGoodsCart(List<MsgGoodsCart> showGoodsCart) {
        this.showGoodsCart = showGoodsCart;
    }

    public int getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(int shopStatus) {
        this.shopStatus = shopStatus;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
