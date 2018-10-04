package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class MerchantEvaluateQuery extends Message{ 
    
    @Expose
    private Integer pageIndex;

    public Integer getPageIndex(){
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex){
        this. pageIndex = pageIndex;
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
    private Integer mId;

    public Integer getMId(){
        return mId;
    }

    public void setMId(Integer mId){
        this. mId = mId;
    }
    
    public MerchantEvaluateQuery(){
    }

    public MerchantEvaluateQuery(Integer pageIndex,Integer userId,Integer mId){
        this.pageIndex=pageIndex;
        this.userId=userId;
        this.mId=mId;
    }
}