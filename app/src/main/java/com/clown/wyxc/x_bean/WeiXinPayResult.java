package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class WeiXinPayResult extends Message{ 
    
    @Expose
    private String appId;

    public String getAppId(){
        return appId;
    }

    public void setAppId(String appId){
        this. appId = appId;
    }
    
    @Expose
    private Integer Id;

    public Integer getId(){
        return Id;
    }

    public void setId(Integer Id){
        this. Id = Id;
    }
    
    @Expose
    private String nonceStr;

    public String getNonceStr(){
        return nonceStr;
    }

    public void setNonceStr(String nonceStr){
        this. nonceStr = nonceStr;
    }
    
    @Expose
    private String partnerId;

    public String getPartnerId(){
        return partnerId;
    }

    public void setPartnerId(String partnerId){
        this. partnerId = partnerId;
    }
    
    @Expose
    private String prepayId;

    public String getPrepayId(){
        return prepayId;
    }

    public void setPrepayId(String prepayId){
        this. prepayId = prepayId;
    }
    
    @Expose
    private String sign;

    public String getSign(){
        return sign;
    }

    public void setSign(String sign){
        this. sign = sign;
    }
    
    @Expose
    private String signType;

    public String getSignType(){
        return signType;
    }

    public void setSignType(String signType){
        this. signType = signType;
    }
    
    @Expose
    private String timeStamp;

    public String getTimeStamp(){
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp){
        this. timeStamp = timeStamp;
    }
    
    @Expose
    private String wx_package;

    public String getWx_package(){
        return wx_package;
    }

    public void setWx_package(String wx_package){
        this. wx_package = wx_package;
    }
    
    public WeiXinPayResult(){
    }

    public WeiXinPayResult(String appId,Integer Id,String nonceStr,String partnerId,String prepayId,String sign,String signType,String timeStamp,String wx_package){
        this.appId=appId;
        this.Id=Id;
        this.nonceStr=nonceStr;
        this.partnerId=partnerId;
        this.prepayId=prepayId;
        this.sign=sign;
        this.signType=signType;
        this.timeStamp=timeStamp;
        this.wx_package=wx_package;
    }
}