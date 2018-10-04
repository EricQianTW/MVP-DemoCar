package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;
public class GoodsShare extends Message{ 
    
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
    private String title;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this. title = title;
    }
    
    @Expose
    private Integer shareType;

    public Integer getShareType(){
        return shareType;
    }

    public void setShareType(Integer shareType){
        this. shareType = shareType;
    }
    
    @Expose
    private String contents;

    public String getContents(){
        return contents;
    }

    public void setContents(String contents){
        this. contents = contents;
    }
    
    @Expose
    private String sharePic;

    public String getSharePic(){
        return sharePic;
    }

    public void setSharePic(String sharePic){
        this. sharePic = sharePic;
    }
    
    @Expose
    private String detailPic;

    public String getDetailPic(){
        return detailPic;
    }

    public void setDetailPic(String detailPic){
        this. detailPic = detailPic;
    }
    
    @Expose
    private String shareUrl;

    public String getShareUrl(){
        return shareUrl;
    }

    public void setShareUrl(String shareUrl){
        this. shareUrl = shareUrl;
    }
    
    public GoodsShare(){
    }

    public GoodsShare(Integer id,Integer goodsId,String title,Integer shareType,String contents,String sharePic,String detailPic,String shareUrl){
        this.id=id;
        this.goodsId=goodsId;
        this.title=title;
        this.shareType=shareType;
        this.contents=contents;
        this.sharePic=sharePic;
        this.detailPic=detailPic;
        this.shareUrl=shareUrl;
    }
}