package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class QueryId extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer userid;

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this. userid = userid;
    }
    
    public QueryId(){
    }

    public QueryId(Integer id,Integer userid){
        this.id=id;
        this.userid=userid;
    }
}