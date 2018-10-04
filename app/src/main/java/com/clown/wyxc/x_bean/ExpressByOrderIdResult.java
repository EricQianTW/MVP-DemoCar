package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ExpressByOrderIdResult extends Message{
    
    @Expose
    private Integer expressInfoId;

    public Integer getExpressInfoId(){
        return expressInfoId;
    }

    public void setExpressInfoId(Integer expressInfoId){
        this. expressInfoId = expressInfoId;
    }
    
    @Expose
    private String expressName;

    public String getExpressName(){
        return expressName;
    }

    public void setExpressName(String expressName){
        this. expressName = expressName;
    }
    
    @Expose
    private String expressNO;

    public String getExpressNO(){
        return expressNO;
    }

    public void setExpressNO(String expressNO){
        this. expressNO = expressNO;
    }
    
    @Expose
    private Integer exState;

    public Integer getExState(){
        return exState;
    }

    public void setExState(Integer exState){
        this. exState = exState;
    }
    
    @Expose
    private String exImgPath;

    public String getExImgPath(){
        return exImgPath;
    }

    public void setExImgPath(String exImgPath){
        this. exImgPath = exImgPath;
    }
    
    @Expose
    private List<IogisticsInfo> iogisticsInfoList;

    public List<IogisticsInfo> getIogisticsInfoList(){
        return iogisticsInfoList;
    }

    public void setIogisticsInfoList(List<IogisticsInfo> iogisticsInfoList){
        this. iogisticsInfoList = iogisticsInfoList;
    }
    
    public ExpressByOrderIdResult(){
    }

    public ExpressByOrderIdResult(Integer expressInfoId,String expressName,String expressNO,Integer exState,String exImgPath,List<IogisticsInfo> iogisticsInfoList){
        this.expressInfoId=expressInfoId;
        this.expressName=expressName;
        this.expressNO=expressNO;
        this.exState=exState;
        this.exImgPath=exImgPath;
        this.iogisticsInfoList=iogisticsInfoList;
    }
}