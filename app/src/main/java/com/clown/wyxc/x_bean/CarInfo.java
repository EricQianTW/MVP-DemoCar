package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class CarInfo extends Message{

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
    private String initial;

    public String getInitial(){
        return initial;
    }

    public void setInitial(String initial){
        this. initial = initial;
    }

    @Expose
    private Integer parentid;

    public Integer getParentid(){
        return parentid;
    }

    public void setParentid(Integer parentid){
        this. parentid = parentid;
    }

    @Expose
    private Integer depth;

    public Integer getDepth(){
        return depth;
    }

    public void setDepth(Integer depth){
        this. depth = depth;
    }

    @Expose
    private String fullName;

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this. fullName = fullName;
    }

    @Expose
    private String logo;

    public String getLogo(){
        return logo;
    }

    public void setLogo(String logo){
        this. logo = logo;
    }

    @Expose
    private String seleState;

    public String getSeleState(){
        return seleState;
    }

    public void setSeleState(String seleState){
        this. seleState = seleState;
    }

    @Expose
    private String price;

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this. price = price;
    }

    @Expose
    private String yearType;

    public String getYearType(){
        return yearType;
    }

    public void setYearType(String yearType){
        this. yearType = yearType;
    }

    @Expose
    private String productionState;

    public String getProductionState(){
        return productionState;
    }

    public void setProductionState(String productionState){
        this. productionState = productionState;
    }

    @Expose
    private String sizeType;

    public String getSizeType(){
        return sizeType;
    }

    public void setSizeType(String sizeType){
        this. sizeType = sizeType;
    }

    public CarInfo(){
    }

    public CarInfo(Integer id,String name,String initial,Integer parentid,Integer depth,String fullName,String logo,String seleState,String price,String yearType,String productionState,String sizeType){
        this.id=id;
        this.name=name;
        this.initial=initial;
        this.parentid=parentid;
        this.depth=depth;
        this.fullName=fullName;
        this.logo=logo;
        this.seleState=seleState;
        this.price=price;
        this.yearType=yearType;
        this.productionState=productionState;
        this.sizeType=sizeType;
    }
}