package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class FeedBackPicQuery extends Message{ 
    
    @Expose
    private Integer fId;

    public Integer getFId(){
        return fId;
    }

    public void setFId(Integer fId){
        this. fId = fId;
    }
    
    @Expose
    private String buffer;

    public String getBuffer(){
        return buffer;
    }

    public void setBuffer(String buffer){
        this. buffer = buffer;
    }
    
    public FeedBackPicQuery(){
    }

    public FeedBackPicQuery(Integer fId,String buffer){
        this.fId=fId;
        this.buffer=buffer;
    }
}