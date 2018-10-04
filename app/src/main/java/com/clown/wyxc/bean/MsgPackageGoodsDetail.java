package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by CZP on 2016/7/4.
 */
public class MsgPackageGoodsDetail extends Message{

    @Expose
    private List<MsgAttrName> attrList;

    @Expose
    private MsgPurchasedItem goodsInfo;

    @Expose
    private List<MsgGoodsStock> goodsStockList;


    public MsgPurchasedItem getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(MsgPurchasedItem goodsInfo) {
        this.goodsInfo = goodsInfo;
    }


    public List<MsgGoodsStock> getGoodsStockList() {
        return goodsStockList;
    }

    public void setGoodsStockList(List<MsgGoodsStock> goodsStockList) {
        this.goodsStockList = goodsStockList;
    }

    public List<MsgAttrName> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<MsgAttrName> attrList) {
        this.attrList = attrList;
    }
}
