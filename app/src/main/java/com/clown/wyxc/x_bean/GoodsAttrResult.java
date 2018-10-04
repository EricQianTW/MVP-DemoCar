package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GoodsAttrResult extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String attrName;

    public String getAttrName(){
        return attrName;
    }

    public void setAttrName(String attrName){
        this. attrName = attrName;
    }
    
    @Expose
    private List<GoodsAttrAttr> attrList;

    public List<GoodsAttrAttr> getAttrList(){
        return attrList;
    }

    public void setAttrList(List<GoodsAttrAttr> attrList){
        this. attrList = attrList;
    }
    
    public GoodsAttrResult(){
    }

    public GoodsAttrResult(Integer id,String attrName,List<GoodsAttrAttr> attrList){
        this.id=id;
        this.attrName=attrName;
        this.attrList=attrList;
    }
}