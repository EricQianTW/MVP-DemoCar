package com.clown.wyxc.bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

public class MsgAddressInfo extends Message {

    //编号
    @Expose
    private int Id;

    //收货人账号
    @Expose
    private String PassportId;

    //收货人名称
    @Expose
    private String ReceiverName;

    //电话
    @Expose
    private String ReceiverPhone;

    //地区-省
    @Expose
    private String Province;

    //地区-市
    @Expose
    private String City;

    //地区-区
    @Expose
    private String District;

    //详细地址
    @Expose
    private String Street;

    //详细地址
    @Expose
    private String StreetNumber;

    //详细地址
    @Expose
    private String AdCode;

    //是否默认 0:否 1：是
    @Expose
    private boolean IsDefault;

    @Expose
    private String IdNum;

    public MsgAddressInfo(){

    }

    public MsgAddressInfo(int id, String passportId, String receiverName
            , String receiverPhone, String province, String city
            , String district, String street
            , String streetNumber, String adCode, boolean isDefault,String idNum) {
        Id = id;
        PassportId = passportId;
        ReceiverName = receiverName;
        ReceiverPhone = receiverPhone;
        Province = province;
        City = city;
        District = district;
        Street = street;
        StreetNumber = streetNumber;
        AdCode = adCode;
        IsDefault = isDefault;
        IdNum = idNum;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPassportId() {
        return PassportId;
    }

    public void setPassportId(String passportId) {
        PassportId = passportId;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getReceiverPhone() {
        return ReceiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        ReceiverPhone = receiverPhone;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreetNumber() {
        return StreetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }

    public String getAdCode() {
        return AdCode;
    }

    public void setAdCode(String adCode) {
        AdCode = adCode;
    }

    public String getIdNum() {
        return IdNum;
    }

    public void setIdNum(String idNum) {
        IdNum = idNum;
    }

    public boolean isDefault() {
        return IsDefault;
    }

    public void setDefault(boolean aDefault) {
        IsDefault = aDefault;
    }
}
