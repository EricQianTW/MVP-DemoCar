package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by cc on 2016/8/11.
 */
public class MsgAfterSaleItem extends Message {
    @Expose
    private String afterSaleNo;

    @Expose
    private int GoodsStockId;

    @Expose
    private int OrderItemId;

    public String getAfterSaleNo() {
        return afterSaleNo;
    }

    public void setAfterSaleNo(String afterSaleNo) {
        this.afterSaleNo = afterSaleNo;
    }

    public int getGoodsStockId() {
        return GoodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        GoodsStockId = goodsStockId;
    }

    public int getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        OrderItemId = orderItemId;
    }
}
