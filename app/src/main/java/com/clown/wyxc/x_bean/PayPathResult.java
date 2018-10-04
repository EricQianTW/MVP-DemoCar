package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PayPathResult extends Message{
    
    @Expose
    private String payOrderNO;

    public String getPayOrderNO(){
        return payOrderNO;
    }

    public void setPayOrderNO(String payOrderNO){
        this. payOrderNO = payOrderNO;
    }
    
    @Expose
    private BigDecimal dispayPayAmt;

    public BigDecimal getDispayPayAmt(){
        return dispayPayAmt;
    }

    public void setDispayPayAmt(BigDecimal dispayPayAmt){
        this. dispayPayAmt = dispayPayAmt;
    }
    
    @Expose
    private BigDecimal payAmt;

    public BigDecimal getPayAmt(){
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt){
        this. payAmt = payAmt;
    }
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private String memo;

    public String getMemo(){
        return memo;
    }

    public void setMemo(String memo){
        this. memo = memo;
    }
    
    @Expose
    private String imgPath;

    public String getImgPath(){
        return imgPath;
    }

    public void setImgPath(String imgPath){
        this. imgPath = imgPath;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }

    @Expose
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public PayPathResult(){
    }

    public PayPathResult(String payOrderNO,BigDecimal dispayPayAmt,BigDecimal payAmt,Integer id,String name,String memo,String imgPath,Integer sort){
        this.payOrderNO=payOrderNO;
        this.dispayPayAmt=dispayPayAmt;
        this.payAmt=payAmt;
        this.id=id;
        this.name=name;
        this.memo=memo;
        this.imgPath=imgPath;
        this.sort=sort;
    }
}