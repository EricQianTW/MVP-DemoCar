package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class Banner extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this. title = title;
    }
    
    @Expose
    private Integer type;

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this. type = type;
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
    private Integer oId;

    public Integer getOId(){
        return oId;
    }

    public void setOId(Integer oId){
        this. oId = oId;
    }
    
    @Expose
    private String url;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this. url = url;
    }
    
    @Expose
    private String zipCode;

    public String getZipCode(){
        return zipCode;
    }

    public void setZipCode(String zipCode){
        this. zipCode = zipCode;
    }
    
    public Banner(){
    }

    public Banner(Integer id,String pic,String title,Integer type,Integer sort,Integer oId,String url,String zipCode){
        this.id=id;
        this.pic=pic;
        this.title=title;
        this.type=type;
        this.sort=sort;
        this.oId=oId;
        this.url=url;
        this.zipCode=zipCode;
    }
}