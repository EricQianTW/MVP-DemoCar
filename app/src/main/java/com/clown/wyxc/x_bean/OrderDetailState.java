package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class OrderDetailState extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer flag;

    public Integer getFlag(){
        return flag;
    }

    public void setFlag(Integer flag){
        this. flag = flag;
    }
    
    @Expose
    private String stateTitle;

    public String getStateTitle(){
        return stateTitle;
    }

    public void setStateTitle(String stateTitle){
        this. stateTitle = stateTitle;
    }
    
    @Expose
    private String stateRemark;

    public String getStateRemark(){
        return stateRemark;
    }

    public void setStateRemark(String stateRemark){
        this. stateRemark = stateRemark;
    }
    
    @Expose
    private String imagePath;

    public String getImagePath(){
        return imagePath;
    }

    public void setImagePath(String imagePath){
        this. imagePath = imagePath;
    }
    
    public OrderDetailState(){
    }

    public OrderDetailState(Integer id,Integer flag,String stateTitle,String stateRemark,String imagePath){
        this.id=id;
        this.flag=flag;
        this.stateTitle=stateTitle;
        this.stateRemark=stateRemark;
        this.imagePath=imagePath;
    }
}