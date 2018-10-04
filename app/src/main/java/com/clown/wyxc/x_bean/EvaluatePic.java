package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class EvaluatePic extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer eId;

    public Integer getEId(){
        return eId;
    }

    public void setEId(Integer eId){
        this. eId = eId;
    }
    
    @Expose
    private String pic;

    public String getPic(){
        return pic;
    }

    public void setPic(String pic){
        this. pic = pic;
    }
    
    @Expose
    private String bigPic;

    public String getBigPic(){
        return bigPic;
    }

    public void setBigPic(String bigPic){
        this. bigPic = bigPic;
    }
    
    public EvaluatePic(){
    }

    public EvaluatePic(Integer id,Integer eId,String pic,String bigPic){
        this.id=id;
        this.eId=eId;
        this.pic=pic;
        this.bigPic=bigPic;
    }
}