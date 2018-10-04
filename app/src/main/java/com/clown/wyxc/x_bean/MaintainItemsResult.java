package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class MaintainItemsResult extends Message{
    
    @Expose
    private String detailUrl;

    public String getDetailUrl(){
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl){
        this. detailUrl = detailUrl;
    }
    
    @Expose
    private Integer isCheck;

    public Integer getIsCheck(){
        return isCheck;
    }

    public void setIsCheck(Integer isCheck){
        this. isCheck = isCheck;
    }
    
    @Expose
    private List<MaintainItemGoodsResult> list;

    public List<MaintainItemGoodsResult> getList(){
        return list;
    }

    public void setList(List<MaintainItemGoodsResult> list){
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
    private Integer parentId;

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this. parentId = parentId;
    }
    
    @Expose
    private Integer serviceItemsId;

    public Integer getServiceItemsId(){
        return serviceItemsId;
    }

    public void setServiceItemsId(Integer serviceItemsId){
        this. serviceItemsId = serviceItemsId;
    }
    
    @Expose
    private Integer maintainKM;

    public Integer getMaintainKM(){
        return maintainKM;
    }

    public void setMaintainKM(Integer maintainKM){
        this. maintainKM = maintainKM;
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
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }

    @Expose
    private boolean isEditer = false;

    public boolean isEditer() {
        return isEditer;
    }

    public void setEditer(boolean editer) {
        isEditer = editer;
    }
    
    public MaintainItemsResult(){
    }

    public MaintainItemsResult(String detailUrl,Integer isCheck,List<MaintainItemGoodsResult> list,Integer id,String name,String pic,Integer parentId,Integer serviceItemsId,Integer maintainKM,String simpleDetail,String detail,Integer sort){
        this.detailUrl=detailUrl;
        this.isCheck=isCheck;
        this.list=list;
        this.id=id;
        this.name=name;
        this.pic=pic;
        this.parentId=parentId;
        this.serviceItemsId=serviceItemsId;
        this.maintainKM=maintainKM;
        this.simpleDetail=simpleDetail;
        this.detail=detail;
        this.sort=sort;
    }
}