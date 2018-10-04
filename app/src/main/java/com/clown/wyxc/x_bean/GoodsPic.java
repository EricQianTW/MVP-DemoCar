package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsPic extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private Integer goodsId;

    public Integer getGoodsId(){
        return goodsId;
    }

    public void setGoodsId(Integer goodsId){
        this. goodsId = goodsId;
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
    
    public GoodsPic(){
    }

    public GoodsPic(Integer id,Integer goodsId,Integer type,String pic,String bigPic){
        this.id=id;
        this.goodsId=goodsId;
        this.type=type;
        this.pic=pic;
        this.bigPic=bigPic;
    }
}