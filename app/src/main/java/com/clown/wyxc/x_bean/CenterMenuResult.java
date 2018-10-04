package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class CenterMenuResult extends Message{
    
    @Expose
    private List<CenterMenu> list;

    public List<CenterMenu> getList(){
        return list;
    }

    public void setList(List<CenterMenu> list){
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
    private Integer pId;

    public Integer getPId(){
        return pId;
    }

    public void setPId(Integer pId){
        this. pId = pId;
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
    private Integer isView;

    public Integer getIsView(){
        return isView;
    }

    public void setIsView(Integer isView){
        this. isView = isView;
    }
    
    @Expose
    private Integer sort;

    public Integer getSort(){
        return sort;
    }

    public void setSort(Integer sort){
        this. sort = sort;
    }
    
    public CenterMenuResult(){
    }

    public CenterMenuResult(List<CenterMenu> list,Integer id,String name,String pic,Integer pId,String url,Integer isView,Integer sort){
        this.list=list;
        this.id=id;
        this.name=name;
        this.pic=pic;
        this.pId=pId;
        this.url=url;
        this.isView=isView;
        this.sort=sort;
    }
}