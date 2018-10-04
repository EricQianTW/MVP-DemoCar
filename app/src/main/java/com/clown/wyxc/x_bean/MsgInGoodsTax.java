package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by eric_clown on 2017/5/30.
 */

public class MsgInGoodsTax extends Message {
    @Expose
    private int Id;
    @Expose
    private BigDecimal TaxAmt;
    @Expose
    private MsgInGoodsTaxDesc ListTaxDesc;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BigDecimal getTaxAmt() {
        return TaxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        TaxAmt = taxAmt;
    }

    public MsgInGoodsTaxDesc getListTaxDesc() {
        return ListTaxDesc;
    }

    public void setListTaxDesc(MsgInGoodsTaxDesc listTaxDesc) {
        ListTaxDesc = listTaxDesc;
    }
}
