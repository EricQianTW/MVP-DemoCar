package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric on 17-4-8.
 */

public class SellerInfo extends Message {
    @Expose
    private String imgUrl;
    @Expose
    private String sellerName;
    @Expose
    private String sellerCity;
    @Expose
    private String sellerAddress;
    @Expose
    private String distance;
    @Expose
    private String pingfen;
    @Expose
    private String leixing;

    public SellerInfo(String imgUrl, String sellerName, String sellerCity, String sellerAddress, String distance, String pingfen, String leixing) {
        this.imgUrl = imgUrl;
        this.sellerName = sellerName;
        this.sellerCity = sellerCity;
        this.sellerAddress = sellerAddress;
        this.distance = distance;
        this.pingfen = pingfen;
        this.leixing = leixing;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPingfen() {
        return pingfen;
    }

    public void setPingfen(String pingfen) {
        this.pingfen = pingfen;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }
}
