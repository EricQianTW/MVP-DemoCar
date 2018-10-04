package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class CarInfoCResult extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
    }
    
    @Expose
    private List<CarInfoCX> cxList;

    public List<CarInfoCX> getCxList() {
        return cxList;
    }

    public void setCxList(List<CarInfoCX> cxList) {
        this.cxList = cxList;
    }

    public CarInfoCResult(){
    }

    public CarInfoCResult(Integer id,String name,List<CarInfoCX> cxList){
        this.id=id;
        this.name=name;
        this.cxList=cxList;
    }
}