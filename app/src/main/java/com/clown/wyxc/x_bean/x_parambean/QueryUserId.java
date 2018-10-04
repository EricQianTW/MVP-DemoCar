package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class QueryUserId extends Message{ 
    
    @Expose
    private Integer userId;
    
    public QueryUserId(){
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public QueryUserId(Integer userId) {
        this.userId = userId;
    }
}