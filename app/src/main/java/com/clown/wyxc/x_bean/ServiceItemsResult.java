package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class ServiceItemsResult extends Message{
    
    @Expose
    private List<ServiceItems> list;

    public List<ServiceItems> getList(){
        return list;
    }

    public void setList(List<ServiceItems> list){
        this. list = list;
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
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this. name = name;
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
    private String simpleDetail;

    public String getSimpleDetail(){
        return simpleDetail;
    }

    public void setSimpleDetail(String simpleDetail){
        this. simpleDetail = simpleDetail;
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
    private Integer parentId;

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this. parentId = parentId;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    @Expose
    private BigDecimal price;

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this. price = price;
    }
    
    @Expose
    private Integer canPay;

    public Integer getCanPay(){
        return canPay;
    }

    public void setCanPay(Integer canPay){
        this. canPay = canPay;
    }
    
    public ServiceItemsResult(){
    }

    public ServiceItemsResult(List<ServiceItems> list,Integer id,String name,String pic,String simpleDetail,String detail,Integer parentId,Integer sort,BigDecimal price,Integer canPay){
        this.list=list;
        this.id=id;
        this.name=name;
        this.pic=pic;
        this.simpleDetail=simpleDetail;
        this.detail=detail;
        this.parentId=parentId;
        this.sort=sort;
        this.price=price;
        this.canPay=canPay;
    }
}