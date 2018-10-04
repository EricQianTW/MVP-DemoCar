package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.ScreenConditionsResult;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GoodsScreenConditionsQuery extends Message{
    
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
    private Integer goodsTypeId;

    public Integer getGoodsTypeId(){
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId){
        this. goodsTypeId = goodsTypeId;
    }
    
    @Expose
    private String keyWords;

    public String getKeyWords(){
        return keyWords;
    }

    public void setKeyWords(String keyWords){
        this. keyWords = keyWords;
    }
    
    @Expose
    private Integer isPositive;

    public Integer getIsPositive(){
        return isPositive;
    }

    public void setIsPositive(Integer isPositive){
        this. isPositive = isPositive;
    }
    
    @Expose
    private Integer orderCriteria;

    public Integer getOrderCriteria(){
        return orderCriteria;
    }

    public void setOrderCriteria(Integer orderCriteria){
        this. orderCriteria = orderCriteria;
    }
    
    @Expose
    private List<ScreenConditionsResult> ScreenConditionsList;

    public List<ScreenConditionsResult> getScreenConditionsList(){
        return ScreenConditionsList;
    }

    public void setScreenConditionsList(List<ScreenConditionsResult> ScreenConditionsList){
        this. ScreenConditionsList = ScreenConditionsList;
    }
    
    public GoodsScreenConditionsQuery(){
    }

    public GoodsScreenConditionsQuery(Integer userId,Integer pageIndex,Integer goodsTypeId,String keyWords,Integer isPositive,Integer orderCriteria,List<ScreenConditionsResult> ScreenConditionsList){
        this.userId=userId;
        this.pageIndex=pageIndex;
        this.goodsTypeId=goodsTypeId;
        this.keyWords=keyWords;
        this.isPositive=isPositive;
        this.orderCriteria=orderCriteria;
        this.ScreenConditionsList=ScreenConditionsList;
    }
}