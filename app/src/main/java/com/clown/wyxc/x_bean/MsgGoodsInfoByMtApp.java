package com.clown.wyxc.x_bean;

import com.clown.wyxc.bean.MsgGoodsImage;
import com.clown.wyxc.bean.MsgShareDetail;
import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by cc on 2017/5/21.
 */

public class MsgGoodsInfoByMtApp extends Message {

    @Expose
    private MsgShareDetail shareDetail;

    @Expose
    private List<MsgGoodsImage> showGoodsImage;

    @Expose
    private List<MsgGoodsImage> goodsDetailImgs;

    @Expose
    private Integer customerServiceUserId;

    @Expose
    private int collectionId;

    @Expose
    private int collectionCount;

    @Expose
    private String priceRange;

    @Expose
    private int Id;

    @Expose
    private String GoodsName;

    @Expose
    private int ShopId;

    @Expose
    private BigDecimal MinPrice;

    @Expose
    private BigDecimal MaxPrice;

    @Expose
    private String GoodsImage;

    @Expose
    private int SellTotalStock;

    @Expose
    private int TotalStock;

    @Expose
    private String GoodsDetails;

    @Expose
    private BigDecimal Postage;

    @Expose
    private BigDecimal TaxRate;

    @Expose
    private String goodsAddress;

    @Expose
    private String goodsDetailsContent;

    public MsgShareDetail getShareDetail() {
        return shareDetail;
    }

    public void setShareDetail(MsgShareDetail shareDetail) {
        this.shareDetail = shareDetail;
    }

    public List<MsgGoodsImage> getShowGoodsImage() {
        return showGoodsImage;
    }

    public void setShowGoodsImage(List<MsgGoodsImage> showGoodsImage) {
        this.showGoodsImage = showGoodsImage;
    }

    public List<MsgGoodsImage> getGoodsDetailImgs() {
        return goodsDetailImgs;
    }

    public void setGoodsDetailImgs(List<MsgGoodsImage> goodsDetailImgs) {
        this.goodsDetailImgs = goodsDetailImgs;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int shopId) {
        ShopId = shopId;
    }

    public BigDecimal getMinPrice() {
        return MinPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        MinPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        MaxPrice = maxPrice;
    }

    public String getGoodsImage() {
        return GoodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        GoodsImage = goodsImage;
    }

    public int getSellTotalStock() {
        return SellTotalStock;
    }

    public void setSellTotalStock(int sellTotalStock) {
        SellTotalStock = sellTotalStock;
    }

    public String getGoodsDetails() {
        return GoodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        GoodsDetails = goodsDetails;
    }

    public BigDecimal getPostage() {
        return Postage;
    }

    public void setPostage(BigDecimal postage) {
        Postage = postage;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        TaxRate = taxRate;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getGoodsDetailsContent() {
        return goodsDetailsContent;
    }

    public void setGoodsDetailsContent(String goodsDetailsContent) {
        this.goodsDetailsContent = goodsDetailsContent;
    }

    public int getTotalStock() {
        return TotalStock;
    }

    public void setTotalStock(int totalStock) {
        TotalStock = totalStock;
    }

    public Integer getCustomerServiceUserId() {
        return customerServiceUserId;
    }

    public void setCustomerServiceUserId(Integer customerServiceUserId) {
        this.customerServiceUserId = customerServiceUserId;
    }
}
