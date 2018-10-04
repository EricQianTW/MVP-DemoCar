package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.util.List;

public class ShoppingCartResult extends Message{
    
    @Expose
    private Integer goodsShopId;

    public Integer getGoodsShopId(){
        return goodsShopId;
    }

    public void setGoodsShopId(Integer goodsShopId){
        this. goodsShopId = goodsShopId;
    }
    
    @Expose
    private String goodsShopName;

    public String getGoodsShopName(){
        return goodsShopName;
    }

    public void setGoodsShopName(String goodsShopName){
        this. goodsShopName = goodsShopName;
    }
    
    @Expose
    private List<GoodsCart> goodsCartList;

    public List<GoodsCart> getGoodsCartList(){
        return goodsCartList;
    }

    public void setGoodsCartList(List<GoodsCart> goodsCartList){
        this. goodsCartList = goodsCartList;
    }

    // 商品是否被勾选
    @Expose
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ShoppingCartResult(){
    }

    public ShoppingCartResult(Integer goodsShopId,String goodsShopName,List<GoodsCart> goodsCartList){
        this.goodsShopId=goodsShopId;
        this.goodsShopName=goodsShopName;
        this.goodsCartList=goodsCartList;
    }
}