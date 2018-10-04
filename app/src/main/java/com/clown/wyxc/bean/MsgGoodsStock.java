package com.clown.wyxc.bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by eric_qiantw on 16/6/9.
 */
public class MsgGoodsStock extends Message {

    /**
     * detailImage : 20151226093318083.jpg
     * detailPrice : 22.0
     * goodsStockAttr : [{"attrName":{"attrName":"颜色","attrValueList":null,"Id":1},"attrValue":{"attrName":null,"attrNameId":1,"attrValue":"黑色","Id":1},"goodsStock":null,"goodsStockId":3,"id":0},{"attrName":{"attrName":"内存","attrValueList":null,"Id":2},"attrValue":{"attrName":null,"attrNameId":2,"attrValue":"32GB","Id":5},"goodsStock":null,"goodsStockId":3,"id":0},{"attrName":{"attrName":"版本类型","attrValueList":null,"Id":3},"attrValue":{"attrName":null,"attrNameId":3,"attrValue":"港澳台","Id":8},"goodsStock":null,"goodsStockId":3,"id":0},{"attrName":{"attrName":"套餐类型","attrValueList":null,"Id":4},"attrValue":{"attrName":null,"attrNameId":4,"attrValue":"官方标配","Id":12},"goodsStock":null,"goodsStockId":3,"id":0}]
     * Id : 3
     * sellStock : 2
     * stock : 12
     */
    @Expose
    private String detailImage;
    @Expose
    private double detailPrice;
    @Expose
    private int Id;
    @Expose
    private int sellStock;

    @Expose
    private BigDecimal oldPrice;

    @Expose
    private MsgActivity activity;

    @Expose
    private String postaeg;

    @Expose
    private int canBuyNum;

    /**
     * attrName : {"attrName":"颜色","attrValueList":null,"Id":1}
     * attrValue : {"attrName":null,"attrNameId":1,"attrValue":"黑色","Id":1}
     * goodsStock : null
     * goodsStockId : 3
     * id : 0
     */
    @Expose
    private HashMap<String, Integer> goodsStockAttr;

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }

    public double getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(double detailPrice) {
        this.detailPrice = detailPrice;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getSellStock() {
        return sellStock;
    }

    public void setSellStock(int sellStock) {
        this.sellStock = sellStock;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public MsgActivity getActivity() {
        return activity;
    }

    public void setActivity(MsgActivity activity) {
        this.activity = activity;
    }

    public String getPostaeg() {
        return postaeg;
    }

    public void setPostaeg(String postaeg) {
        this.postaeg = postaeg;
    }

    public int getCanBuyNum() {
        return canBuyNum;
    }

    public void setCanBuyNum(int canBuyNum) {
        this.canBuyNum = canBuyNum;
    }

    public HashMap<String, Integer> getGoodsStockAttr() {
        return goodsStockAttr;
    }

    public void setGoodsStockAttr(HashMap<String, Integer> goodsStockAttr) {
        this.goodsStockAttr = goodsStockAttr;
    }
}
