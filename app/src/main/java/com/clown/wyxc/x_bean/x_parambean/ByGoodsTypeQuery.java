package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class ByGoodsTypeQuery extends Message{ 
    
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
    
    @Expose
    private Integer goodsTypeId1;

    public Integer getGoodsTypeId1(){
        return goodsTypeId1;
    }

    public void setGoodsTypeId1(Integer goodsTypeId1){
        this. goodsTypeId1 = goodsTypeId1;
    }
    
    @Expose
    private Integer goodsTypeId2;

    public Integer getGoodsTypeId2(){
        return goodsTypeId2;
    }

    public void setGoodsTypeId2(Integer goodsTypeId2){
        this. goodsTypeId2 = goodsTypeId2;
    }
    
    @Expose
    private Integer goodsTypeId3;

    public Integer getGoodsTypeId3(){
        return goodsTypeId3;
    }

    public void setGoodsTypeId3(Integer goodsTypeId3){
        this. goodsTypeId3 = goodsTypeId3;
    }
    
    public ByGoodsTypeQuery(){
    }

    public ByGoodsTypeQuery(Integer userId,Integer pageIndex,Integer goodsTypeId1,Integer goodsTypeId2,Integer goodsTypeId3){
        this.userId=userId;
        this.pageIndex=pageIndex;
        this.goodsTypeId1=goodsTypeId1;
        this.goodsTypeId2=goodsTypeId2;
        this.goodsTypeId3=goodsTypeId3;
    }
}