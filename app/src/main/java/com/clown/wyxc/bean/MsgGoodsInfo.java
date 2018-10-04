package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class MsgGoodsInfo extends Message {

    //供应商编号
    @Expose
    private int shopId;

    //当前产品状态
    @Expose
    private int goodState;

    //添加产品时间
    @Expose
    private String createTime;

    //一级分类
    @Expose
    private int type1;

    //二级分类
    @Expose
    private int type2;

    //三级分类
    @Expose
    private int type3;

    //商品详情
    @Expose
    private String goodsDetails;

    //分享详情
    @Expose
    private MsgShareDetail shareDetail;

//显示图片详情列表
    @Expose
    private List<MsgGoodsImage> showGoodsImage;

    //供应商详情
    @Expose
    private MsgShopInfo shopInfo;

    //邮费
    @Expose
    private String postage;

    //价格区间
    @Expose
    private String priceRange;

    @Expose
    private String goodsAddress;

    @Expose
    private String goodsDetailsContent;

    //商品id
    @Expose
    private int Id;

    //商品名称
    @Expose
    private String goodsName;

    //价格
    @Expose
    private BigDecimal goodsPrice;

    //首图
    @Expose
    private String goodsImage;

    //销售数量
    @Expose
    public int sellStock;

    //品牌
    @Expose
    private String brand;

    //总库存
    @Expose
    private int totalStock;

    @Expose
    private int goodsStockId;

    @Expose
    private BigDecimal oldPrice;

    @Expose
    private int collectionId;

    public int getGoodsStockId() {
        return goodsStockId;
    }

    public void setGoodsStockId(int goodsStockId) {
        this.goodsStockId = goodsStockId;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGoodState() {
        return goodState;
    }

    public void setGoodState(int goodState) {
        this.goodState = goodState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public int getType3() {
        return type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

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

    public MsgShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(MsgShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public String getPostage() {
        return postage;
    }

    public void setPostage(String postage) {
        this.postage = postage;
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
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public int getSellStock() {
        return sellStock;
    }

    public void setSellStock(int sellStock) {
        this.sellStock = sellStock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
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
}
