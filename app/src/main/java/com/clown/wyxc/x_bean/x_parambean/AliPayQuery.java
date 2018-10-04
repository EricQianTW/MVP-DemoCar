package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class AliPayQuery extends Message{
    
    @Expose
    private String subject;

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this. subject = subject;
    }
    
    @Expose
    private String out_trade_no;

    public String getOut_trade_no(){
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no){
        this. out_trade_no = out_trade_no;
    }
    
    @Expose
    private String body;

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this. body = body;
    }
    
    @Expose
    private BigDecimal total_fee;

    public BigDecimal getTotal_fee(){
        return total_fee;
    }

    public void setTotal_fee(BigDecimal total_fee){
        this. total_fee = total_fee;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private String notifyurl_type;

    public String getNotifyurl_type(){
        return notifyurl_type;
    }

    public void setNotifyurl_type(String notifyurl_type){
        this. notifyurl_type = notifyurl_type;
    }
    
    public AliPayQuery(){
    }

    public AliPayQuery(String subject,String out_trade_no,String body,BigDecimal total_fee,Integer userId,String notifyurl_type){
        this.subject=subject;
        this.out_trade_no=out_trade_no;
        this.body=body;
        this.total_fee=total_fee;
        this.userId=userId;
        this.notifyurl_type=notifyurl_type;
    }
}