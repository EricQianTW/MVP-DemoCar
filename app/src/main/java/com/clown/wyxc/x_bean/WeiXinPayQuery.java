package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class WeiXinPayQuery extends Message{ 
    
    @Expose
    private String body;

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this. body = body;
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
    private String total_fee;

    public String getTotal_fee(){
        return total_fee;
    }

    public void setTotal_fee(String total_fee){
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
    private String spbill_create_ip;

    public String getSpbill_create_ip(){
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip){
        this. spbill_create_ip = spbill_create_ip;
    }
    
    @Expose
    private String notifyurl_type;

    public String getNotifyurl_type(){
        return notifyurl_type;
    }

    public void setNotifyurl_type(String notifyurl_type){
        this. notifyurl_type = notifyurl_type;
    }
    
    public WeiXinPayQuery(){
    }

    public WeiXinPayQuery(String body,String out_trade_no,String total_fee,Integer userId,String spbill_create_ip,String notifyurl_type){
        this.body=body;
        this.out_trade_no=out_trade_no;
        this.total_fee=total_fee;
        this.userId=userId;
        this.spbill_create_ip=spbill_create_ip;
        this.notifyurl_type=notifyurl_type;
    }
}