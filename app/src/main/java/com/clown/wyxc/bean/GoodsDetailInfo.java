package com.clown.wyxc.bean;

import java.util.List;

/**
 * Created by eric_qiantw on 16/5/16.
 */
public class GoodsDetailInfo {

    private String goods_name;
    private String goods_price;
    private String paizi;
    private String xinghao;
    private List<String> goodsBanners;
    private String webUrl;

    public List<String> getGoodsBanners() {
        return goodsBanners;
    }

    public void setGoodsBanners(List<String> goodsBanners) {
        this.goodsBanners = goodsBanners;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getPaizi() {
        return paizi;
    }

    public void setPaizi(String paizi) {
        this.paizi = paizi;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
