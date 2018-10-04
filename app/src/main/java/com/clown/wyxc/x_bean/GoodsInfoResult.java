package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class GoodsInfoResult extends Message{
    
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
    private String firstPic;

    public String getFirstPic(){
        return firstPic;
    }

    public void setFirstPic(String firstPic){
        this. firstPic = firstPic;
    }
    
    @Expose
    private Integer sellStock;

    public Integer getSellStock(){
        return sellStock;
    }

    public void setSellStock(Integer sellStock){
        this. sellStock = sellStock;
    }
    
    @Expose
    private Integer totalStock;

    public Integer getTotalStock(){
        return totalStock;
    }

    public void setTotalStock(Integer totalStock){
        this. totalStock = totalStock;
    }
    
    @Expose
    private Integer isCollection;

    public Integer getIsCollection(){
        return isCollection;
    }

    public void setIsCollection(Integer isCollection){
        this. isCollection = isCollection;
    }
    
    @Expose
    private Integer collectionCount;

    public Integer getCollectionCount(){
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount){
        this. collectionCount = collectionCount;
    }
    
    @Expose
    private String postage;

    public String getPostage(){
        return postage;
    }

    public void setPostage(String postage){
        this. postage = postage;
    }
    
    @Expose
    private String priceRange;

    public String getPriceRange(){
        return priceRange;
    }

    public void setPriceRange(String priceRange){
        this. priceRange = priceRange;
    }
    
    @Expose
    private String goodsAddress;

    public String getGoodsAddress(){
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress){
        this. goodsAddress = goodsAddress;
    }
    
    @Expose
    private String goodsDetailsContent;

    public String getGoodsDetailsContent(){
        return goodsDetailsContent;
    }

    public void setGoodsDetailsContent(String goodsDetailsContent){
        this. goodsDetailsContent = goodsDetailsContent;
    }
    
    @Expose
    private GoodsShare goodsShare;

    public GoodsShare getGoodsShare(){
        return goodsShare;
    }

    public void setGoodsShare(GoodsShare goodsShare){
        this. goodsShare = goodsShare;
    }
    
    @Expose
    private List<GoodsPic> goodsBanner;

    public List<GoodsPic> getGoodsBanner(){
        return goodsBanner;
    }

    public void setGoodsBanner(List<GoodsPic> goodsBanner){
        this. goodsBanner = goodsBanner;
    }
    
    @Expose
    private List<GoodsPic> goodsDetailPic;

    public List<GoodsPic> getGoodsDetailPic(){
        return goodsDetailPic;
    }

    public void setGoodsDetailPic(List<GoodsPic> goodsDetailPic){
        this. goodsDetailPic = goodsDetailPic;
    }
    
    public GoodsInfoResult(){
    }

    public GoodsInfoResult(Integer id,String name,String firstPic,Integer sellStock,Integer totalStock,Integer isCollection,Integer collectionCount,String postage,String priceRange,String goodsAddress,String goodsDetailsContent,GoodsShare goodsShare,List<GoodsPic> goodsBanner,List<GoodsPic> goodsDetailPic){
        this.id=id;
        this.name=name;
        this.firstPic=firstPic;
        this.sellStock=sellStock;
        this.totalStock=totalStock;
        this.isCollection=isCollection;
        this.collectionCount=collectionCount;
        this.postage=postage;
        this.priceRange=priceRange;
        this.goodsAddress=goodsAddress;
        this.goodsDetailsContent=goodsDetailsContent;
        this.goodsShare=goodsShare;
        this.goodsBanner=goodsBanner;
        this.goodsDetailPic=goodsDetailPic;
    }
}