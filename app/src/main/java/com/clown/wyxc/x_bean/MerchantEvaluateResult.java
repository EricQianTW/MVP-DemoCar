package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class MerchantEvaluateResult extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    @Expose
    private Integer point;

    public Integer getPoint(){
        return point;
    }

    public void setPoint(Integer point){
        this. point = point;
    }
    
    @Expose
    private String insDateTime;

    public String getInsDateTime(){
        return insDateTime;
    }

    public void setInsDateTime(String insDateTime){
        this. insDateTime = insDateTime;
    }
    
    @Expose
    private String detail;

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this. detail = detail;
    }
    
    @Expose
    private List<EvaluatePic> evaluatePicList;

    public List<EvaluatePic> getEvaluatePicList(){
        return evaluatePicList;
    }

    public void setEvaluatePicList(List<EvaluatePic> evaluatePicList){
        this. evaluatePicList = evaluatePicList;
    }
    
    @Expose
    private String userHeadPic;

    public String getUserHeadPic(){
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic){
        this. userHeadPic = userHeadPic;
    }
    
    public MerchantEvaluateResult(){
    }

    public MerchantEvaluateResult(Integer id,String phone,Integer point,String insDateTime,String detail,List<EvaluatePic> evaluatePicList,String userHeadPic){
        this.id=id;
        this.phone=phone;
        this.point=point;
        this.insDateTime=insDateTime;
        this.detail=detail;
        this.evaluatePicList=evaluatePicList;
        this.userHeadPic=userHeadPic;
    }
}