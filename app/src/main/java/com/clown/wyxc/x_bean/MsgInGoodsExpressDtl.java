package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by eric_clown on 2017/5/30.
 */

public class MsgInGoodsExpressDtl extends Message {

    @Expose
    private int Id;

    @Expose
    private MsgInGoodsTax goodstax;

    @Expose
    private List<MsgInGoodsExpressDesc> liGoodsExpressDesc;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public MsgInGoodsTax getGoodstax() {
        return goodstax;
    }

    public void setGoodstax(MsgInGoodsTax goodstax) {
        this.goodstax = goodstax;
    }

    public List<MsgInGoodsExpressDesc> getLiGoodsExpressDesc() {
        return liGoodsExpressDesc;
    }

    public void setLiGoodsExpressDesc(List<MsgInGoodsExpressDesc> liGoodsExpressDesc) {
        this.liGoodsExpressDesc = liGoodsExpressDesc;
    }
}
