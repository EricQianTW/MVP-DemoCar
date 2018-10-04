package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemService extends Message{
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
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
    private Integer buyNum;

    public Integer getBuyNum(){
        return buyNum;
    }

    public void setBuyNum(Integer buyNum){
        this. buyNum = buyNum;
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
    
    public OrderItemService(){
    }

    public OrderItemService(Integer id,BigDecimal price,String name,String pic,String simpleDetail,Integer buyNum){
        this.id=id;
        this.price=price;
        this.name=name;
        this.pic=pic;
        this.simpleDetail=simpleDetail;
        this.buyNum=buyNum;
    }
}