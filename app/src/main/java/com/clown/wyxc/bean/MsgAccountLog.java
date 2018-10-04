package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/7/5.
 */
public class MsgAccountLog extends Message {
    @Expose
    private int Id;
    @Expose
    private int AccType;
    @Expose
    private BigDecimal Amount;
    @Expose
    private String Memo;
    @Expose
    private String strTime;
    @Expose
    private String Stat;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAccType() {
        return AccType;
    }

    public void setAccType(int accType) {
        AccType = accType;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStat() {
        return Stat;
    }

    public void setStat(String stat) {
        Stat = stat;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}
