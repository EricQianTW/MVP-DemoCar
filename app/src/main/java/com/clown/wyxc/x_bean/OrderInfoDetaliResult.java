package com.clown.wyxc.x_bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class OrderInfoDetaliResult extends Message{
    
    @Expose
    private OrderDetailState orderDetailState;

    public OrderDetailState getOrderDetailState(){
        return orderDetailState;
    }

    public void setOrderDetailState(OrderDetailState orderDetailState){
        this. orderDetailState = orderDetailState;
    }
    
    @Expose
    private List<OrderItem> orderItemList;

    public List<OrderItem> getOrderItemList(){
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList){
        this. orderItemList = orderItemList;
    }
    
    @Expose
    private List<OrderItemService> orderItemServiceList;

    public List<OrderItemService> getOrderItemServiceList(){
        return orderItemServiceList;
    }

    public void setOrderItemServiceList(List<OrderItemService> orderItemServiceList){
        this. orderItemServiceList = orderItemServiceList;
    }
    
    @Expose
    private IogisticsInfo iogisticsInfo;

    public IogisticsInfo getIogisticsInfo(){
        return iogisticsInfo;
    }

    public void setIogisticsInfo(IogisticsInfo iogisticsInfo){
        this. iogisticsInfo = iogisticsInfo;
    }
    
    @Expose
    private OrderFirmOrderAddressResult orderFirmOrderAddressResult;

    public OrderFirmOrderAddressResult getOrderFirmOrderAddressResult(){
        return orderFirmOrderAddressResult;
    }

    public void setOrderFirmOrderAddressResult(OrderFirmOrderAddressResult orderFirmOrderAddressResult){
        this. orderFirmOrderAddressResult = orderFirmOrderAddressResult;
    }
    
    @Expose
    private GoodsShop goodsShop;

    public GoodsShop getGoodsShop(){
        return goodsShop;
    }

    public void setGoodsShop(GoodsShop goodsShop){
        this. goodsShop = goodsShop;
    }
    
    @Expose
    private Integer orderId;

    public Integer getOrderId(){
        return orderId;
    }

    public void setOrderId(Integer orderId){
        this. orderId = orderId;
    }
    
    @Expose
    private String customerMessage;

    public String getCustomerMessage(){
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage){
        this. customerMessage = customerMessage;
    }
    
    @Expose
    private BigDecimal subtotal;

    public BigDecimal getSubtotal(){
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal){
        this. subtotal = subtotal;
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
    private Integer orderType;

    public Integer getOrderType(){
        return orderType;
    }

    public void setOrderType(Integer orderType){
        this. orderType = orderType;
    }
    
    @Expose
    private String orderdate;

    public String getOrderdate(){
        return orderdate;
    }

    public void setOrderdate(String orderdate){
        this. orderdate = orderdate;
    }
    
    @Expose
    private String goodsOrderNO;

    public String getGoodsOrderNO(){
        return goodsOrderNO;
    }

    public void setGoodsOrderNO(String goodsOrderNO){
        this. goodsOrderNO = goodsOrderNO;
    }
    
    @Expose
    private String erweima;

    public String getErweima(){
        return erweima;
    }

    public void setErweima(String erweima){
        this. erweima = erweima;
    }

    @Expose
    private Integer sendMode;

    public Integer getSendMode() {
        return sendMode;
    }

    public void setSendMode(Integer sendMode) {
        this.sendMode = sendMode;
    }

    public OrderInfoDetaliResult(){
    }

    public OrderInfoDetaliResult(OrderDetailState orderDetailState, List<OrderItem> orderItemList, List<OrderItemService> orderItemServiceList, IogisticsInfo iogisticsInfo, OrderFirmOrderAddressResult orderFirmOrderAddressResult, GoodsShop goodsShop, Integer orderId, String customerMessage, BigDecimal subtotal, String postage, Integer orderType, String orderdate, String goodsOrderNO, String erweima){
        this.orderDetailState=orderDetailState;
        this.orderItemList=orderItemList;
        this.orderItemServiceList=orderItemServiceList;
        this.iogisticsInfo=iogisticsInfo;
        this.orderFirmOrderAddressResult=orderFirmOrderAddressResult;
        this.goodsShop=goodsShop;
        this.orderId=orderId;
        this.customerMessage=customerMessage;
        this.subtotal=subtotal;
        this.postage=postage;
        this.orderType=orderType;
        this.orderdate=orderdate;
        this.goodsOrderNO=goodsOrderNO;
        this.erweima=erweima;
    }

    public OrderInfoDetaliResult(OrderDetailState orderDetailState, List<OrderItem> orderItemList, List<OrderItemService> orderItemServiceList, IogisticsInfo iogisticsInfo, OrderFirmOrderAddressResult orderFirmOrderAddressResult, GoodsShop goodsShop, Integer orderId, String customerMessage, BigDecimal subtotal, String postage, Integer orderType, String orderdate, String goodsOrderNO, String erweima, Integer sendMode) {
        this.orderDetailState = orderDetailState;
        this.orderItemList = orderItemList;
        this.orderItemServiceList = orderItemServiceList;
        this.iogisticsInfo = iogisticsInfo;
        this.orderFirmOrderAddressResult = orderFirmOrderAddressResult;
        this.goodsShop = goodsShop;
        this.orderId = orderId;
        this.customerMessage = customerMessage;
        this.subtotal = subtotal;
        this.postage = postage;
        this.orderType = orderType;
        this.orderdate = orderdate;
        this.goodsOrderNO = goodsOrderNO;
        this.erweima = erweima;
        this.sendMode = sendMode;
    }
}