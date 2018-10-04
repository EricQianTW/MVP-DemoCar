package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_qiantw on 16/6/10.
 */
public class MsgShoppingCart extends Message {

    // 产品所带属性的编号
    @Expose
    private int goodsStockId;
    // 购买数量
    @Expose
    private int goodsNum;

    public MsgShoppingCart(int goodsStockId, int goodsNum) {
        this.goodsStockId = goodsStockId;
        this.goodsNum = goodsNum;
    }

    public int getGoodsStockId() {
        return goodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        this.goodsStockId = goodsStockId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
}
