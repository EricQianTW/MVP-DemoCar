package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GetEvaluateByGoodsIdResult extends Message{
    
    @Expose
    private String goodsAttr;

    public String getGoodsAttr(){
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr){
        this. goodsAttr = goodsAttr;
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
    private String userHeadPic;

    public String getUserHeadPic(){
        return userHeadPic;
    }

    public void setUserHeadPic(String userHeadPic){
        this. userHeadPic = userHeadPic;
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
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private String commentContent;

    public String getCommentContent(){
        return commentContent;
    }

    public void setCommentContent(String commentContent){
        this. commentContent = commentContent;
    }
    
    @Expose
    private List<EvaluatePic> evaluatePicList;

    public List<EvaluatePic> getEvaluatePicList(){
        return evaluatePicList;
    }

    public void setEvaluatePicList(List<EvaluatePic> evaluatePicList){
        this. evaluatePicList = evaluatePicList;
    }
    
    public GetEvaluateByGoodsIdResult(){
    }

    public GetEvaluateByGoodsIdResult(String goodsAttr,String phone,String userHeadPic,String insDateTime,Integer id,Integer point,String commentContent,List<EvaluatePic> evaluatePicList){
        this.goodsAttr=goodsAttr;
        this.phone=phone;
        this.userHeadPic=userHeadPic;
        this.insDateTime=insDateTime;
        this.id=id;
        this.point=point;
        this.commentContent=commentContent;
        this.evaluatePicList=evaluatePicList;
    }
}