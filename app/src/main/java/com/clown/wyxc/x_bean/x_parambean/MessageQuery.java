package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MessageQuery extends Message{ 
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    @Expose
    private Integer pageIndex;

    public Integer getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex){
        this. pageIndex = pageIndex;
    }
    
    public MessageQuery(){
    }

    public MessageQuery(Integer userId,Integer pageIndex){
        this.userId=userId;
        this.pageIndex=pageIndex;
    }
}