package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class OrderItem extends Message{
    
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
    private String goodsName;

    public String getGoodsName(){
        return goodsName;
    }

    public void setGoodsName(String goodsName){
        this. goodsName = goodsName;
    }
    
    @Expose
    private Integer actualGoodsId;

    public Integer getActualGoodsId(){
        return actualGoodsId;
    }

    public void setActualGoodsId(Integer actualGoodsId){
        this. actualGoodsId = actualGoodsId;
    }
    
    @Expose
    private BigDecimal goodsPrice;

    public BigDecimal getGoodsPrice(){
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice){
        this. goodsPrice = goodsPrice;
    }
    
    @Expose
    private BigDecimal oldPrice;

    public BigDecimal getOldPrice(){
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice){
        this. oldPrice = oldPrice;
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
    private String attrValue;

    public String getAttrValue(){
        return attrValue;
    }

    public void setAttrValue(String attrValue){
        this. attrValue = attrValue;
    }
    
    @Expose
    private Integer goodsNum;

    public Integer getGoodsNum(){
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum){
        this. goodsNum = goodsNum;
    }

    @Expose
    private List<String> imgGuidList;

    public List<String> getImgGuidList(){
        return imgGuidList;
    }

    public void setImgGuidList(List<String> imgGuidList){
        this. imgGuidList = imgGuidList;
    }

    @Expose
    private Integer point;

    public Integer getPoint(){
        return point;
    }

    public void setPoint(Integer point){
        this. point = point;
    }

    @Expose
    private String commentContent;

    public String getCommentContent(){
        return commentContent;
    }

    public void setCommentContent(String commentContent){
        this. commentContent = commentContent;
    }
    
    public OrderItem(){
    }

    public OrderItem(Integer id,Integer goodsId,String goodsName,Integer actualGoodsId,BigDecimal goodsPrice,BigDecimal oldPrice,String pic,String attrValue,Integer goodsNum){
        this.id=id;
        this.goodsId=goodsId;
        this.goodsName=goodsName;
        this.actualGoodsId=actualGoodsId;
        this.goodsPrice=goodsPrice;
        this.oldPrice=oldPrice;
        this.pic=pic;
        this.attrValue=attrValue;
        this.goodsNum=goodsNum;
    }
}