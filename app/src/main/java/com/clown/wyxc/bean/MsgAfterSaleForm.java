package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by cc on 2016/7/28.
 */
public class MsgAfterSaleForm extends Message {

    @Expose
    private int applyNum;

    @Expose
    private List<MsgService> afterSaleServiceList;

    @Expose
    private List<MsgAfterSaleItem> afterSaleItemList;

    public List<MsgAfterSaleItem> getAfterSaleItemList() {
        return afterSaleItemList;
    }

    public void setAfterSaleItemList(List<MsgAfterSaleItem> afterSaleItemList) {
        this.afterSaleItemList = afterSaleItemList;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public List<MsgService> getAfterSaleServiceList() {
        return afterSaleServiceList;
    }

    public void setAfterSaleServiceList(List<MsgService> afterSaleServiceList) {
        this.afterSaleServiceList = afterSaleServiceList;
    }
}
