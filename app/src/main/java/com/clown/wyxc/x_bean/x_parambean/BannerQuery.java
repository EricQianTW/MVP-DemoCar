package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

public class BannerQuery extends Message{
    
    @Expose
    private Integer type;

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this. type = type;
    }
    
    @Expose
    private String zipcode;

    public String getZipcode(){
        return zipcode;
    }

    public void setZipcode(String zipcode){
        this. zipcode = zipcode;
    }
    
    @Expose
    private Integer userid;

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this. userid = userid;
    }
    
    public BannerQuery(){
    }

    public BannerQuery(Integer type,String zipcode,Integer userid){
        this.type=type;
        this.zipcode=zipcode;
        this.userid=userid;
    }

    public BannerQuery(Integer type,String zipcode){
        this.type=type;
        this.zipcode=zipcode;
    }
}