package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class CarInfoQuery extends Message{ 
    
    @Expose
    private Integer parentid;

    public Integer getParentid(){
        return parentid;
    }

    public void setParentid(Integer parentid){
        this. parentid = parentid;
    }
    
    @Expose
    private Integer userid;

    public Integer getUserid(){
        return userid;
    }

    public void setUserid(Integer userid){
        this. userid = userid;
    }
    
    public CarInfoQuery(){
    }

    public CarInfoQuery(Integer parentid,Integer userid){
        this.parentid=parentid;
        this.userid=userid;
    }
}