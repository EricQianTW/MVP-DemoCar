package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_qiantw on 16/6/9.
 */
public class MsgGoodsStockAttr extends Message {

    @Expose
    private int id;
    @Expose
    private int goodsStockId;
    @Expose
    private MsgGoodsStock goodsStock;
    @Expose
    private MsgAttrName attrName;
    @Expose
    private MsgAttrValue attrValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsStockId() {
        return goodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        this.goodsStockId = goodsStockId;
    }

    public MsgGoodsStock getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(MsgGoodsStock goodsStock) {
        this.goodsStock = goodsStock;
    }

    public MsgAttrName getAttrName() {
        return attrName;
    }

    public void setAttrName(MsgAttrName attrName) {
        this.attrName = attrName;
    }

    public MsgAttrValue getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(MsgAttrValue attrValue) {
        this.attrValue = attrValue;
    }
}
