package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsAttrAttr extends Message{ 
    
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

    private boolean none;

    public boolean isNone() {
        return none;
    }

    public void setNone(boolean none) {
        this.none = none;
    }

    public GoodsAttrAttr(){
    }

    public GoodsAttrAttr(Integer id,String attrName){
        this.id=id;
        this.attrName=attrName;
    }
}