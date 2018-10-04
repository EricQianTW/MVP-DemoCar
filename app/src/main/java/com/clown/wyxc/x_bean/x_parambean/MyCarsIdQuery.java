package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MyCarsIdQuery extends Message{ 
    
    @Expose
    private Integer myCarsId;

    public Integer getMyCarsId(){
        return myCarsId;
    }

    public void setMyCarsId(Integer myCarsId){
        this. myCarsId = myCarsId;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this. userId = userId;
    }
    
    public MyCarsIdQuery(){
    }

    public MyCarsIdQuery(Integer myCarsId,Integer userId){
        this.myCarsId=myCarsId;
        this.userId=userId;
    }
}