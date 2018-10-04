package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by JokerEric on 2016/7/3.
 */
public class MsgOrderPay extends Message{
    //
    @Expose
    private String GuidOrderNo;

//
    @Expose
    private int Id;

//
    @Expose
    private BigDecimal PayAmt;

    @Expose
    private BigDecimal DispayPayAmt;

//
    @Expose
    private int PayStat;

//
    @Expose
    private int PayTyp;

//
    @Expose
    private int SplitDur;

    public String getGuidOrderNo() {
        return GuidOrderNo;
    }

    public void setGuidOrderNo(String guidOrderNo) {
        GuidOrderNo = guidOrderNo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public BigDecimal getPayAmt() {
        return PayAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        PayAmt = payAmt;
    }

    public int getPayStat() {
        return PayStat;
    }

    public void setPayStat(int payStat) {
        PayStat = payStat;
    }

    public int getPayTyp() {
        return PayTyp;
    }

    public void setPayTyp(int payTyp) {
        PayTyp = payTyp;
    }

    public int getSplitDur() {
        return SplitDur;
    }

    public void setSplitDur(int splitDur) {
        SplitDur = splitDur;
    }

    public BigDecimal getDispayPayAmt() {
        return DispayPayAmt;
    }

    public void setDispayPayAmt(BigDecimal dispayPayAmt) {
        DispayPayAmt = dispayPayAmt;
    }
}
