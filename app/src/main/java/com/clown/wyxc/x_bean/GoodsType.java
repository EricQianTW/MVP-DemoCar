package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsType extends Message{ 
    
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
    private Integer pId;

    public Integer getPId(){
        return pId;
    }

    public void setPId(Integer pId){
        this. pId = pId;
    }
    
    @Expose
    private String selelctedImageUrl;

    public String getSelelctedImageUrl(){
        return selelctedImageUrl;
    }

    public void setSelelctedImageUrl(String selelctedImageUrl){
        this. selelctedImageUrl = selelctedImageUrl;
    }
    
    @Expose
    private String imageUrl;

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this. imageUrl = imageUrl;
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
    private Integer isView;

    public Integer getIsView(){
        return isView;
    }

    public void setIsView(Integer isView){
        this. isView = isView;
    }
    
    public GoodsType(){
    }

    public GoodsType(Integer id,String name,Integer pId,String selelctedImageUrl,String imageUrl,Integer sort,Integer isView){
        this.id=id;
        this.name=name;
        this.pId=pId;
        this.selelctedImageUrl=selelctedImageUrl;
        this.imageUrl=imageUrl;
        this.sort=sort;
        this.isView=isView;
    }
}