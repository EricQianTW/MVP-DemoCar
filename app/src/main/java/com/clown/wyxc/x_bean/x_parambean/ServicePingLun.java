package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ServicePingLun extends Message{
    
    @Expose
    private Integer serviceId;

    public Integer getServiceId(){
        return serviceId;
    }

    public void setServiceId(Integer serviceId){
        this. serviceId = serviceId;
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
    
    public ServicePingLun(){
    }

    public ServicePingLun(Integer serviceId,List<String> imgGuidList,Integer point,String commentContent){
        this.serviceId=serviceId;
        this.imgGuidList=imgGuidList;
        this.point=point;
        this.commentContent=commentContent;
    }
}