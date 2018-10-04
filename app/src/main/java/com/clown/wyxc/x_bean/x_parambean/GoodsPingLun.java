package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GoodsPingLun extends Message{
    
    @Expose
    private Integer goodsId;

    public Integer getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(Integer goodsId){
        this. goodsId = goodsId;
    }
    
    @Expose
    private List<String> imgGuidList;

    public List<String> getImgGuidList(){
        return imgGuidList;
    }

    public void setImgGuidList(List<String> imgGuidList){
        this. imgGuidList = imgGuidList;
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
    
    public GoodsPingLun(){
    }

    public GoodsPingLun(Integer goodsId,List<String> imgGuidList,Integer point,String commentContent){
        this.goodsId=goodsId;
        this.imgGuidList=imgGuidList;
        this.point=point;
        this.commentContent=commentContent;
    }
}