package com.clown.wyxc.x_bean.x_parambean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;
public class DeliveryAddressIns extends Message{ 
    
    @Expose
    private Integer id;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this. id = id;
    }
    
    @Expose
    private String consignee;

    public String getConsignee(){
        return consignee;
    }

    public void setConsignee(String consignee){
        this. consignee = consignee;
    }
    
    @Expose
    private String phone;

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this. phone = phone;
    }
    
    @Expose
    private String address;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this. address = address;
    }
    
    @Expose
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Expose
    private Integer isDefault;

    public Integer getIsDefault(){
        return isDefault;
    }

    public void setIsDefault(Integer isDefault){
        this. isDefault = isDefault;
    }
    
    @Expose
    private String adCode;

    public String getAdCode(){
        return adCode;
    }

    public void setAdCode(String adCode){
        this. adCode = adCode;
    }
    
    public DeliveryAddressIns(){
    }

    public DeliveryAddressIns(Integer id,String consignee,String phone,String address,Integer userId,Integer isDefault,String adCode){
        this.id=id;
        this.consignee=consignee;
        this.phone=phone;
        this.address=address;
        this.userId=userId;
        this.isDefault=isDefault;
        this.adCode=adCode;
    }
}