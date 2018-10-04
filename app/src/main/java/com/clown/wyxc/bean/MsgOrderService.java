package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * Created by JokerEric on 2016/7/1.
 */
public class MsgOrderService extends Message{
    //是否被选中（0未选中，1已选中）
    @Expose
    private int isSelected;

    //商品服务id
    @Expose
    private int Id;

    //服务标题
    @Expose
    public String serviceTitle;

    //服务价格
    @Expose
    private BigDecimal servicePrice;

    //服务内容
    @Expose
    private String serviceContent;

    //服务图片
    @Expose
    private String serviceImage;

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }
}
