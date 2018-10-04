package com.clown.wyxc.bean;

import com.clown.wyxc.x_base.Message;
import com.google.gson.annotations.Expose;

/**
 * Created by eric_qiantw on 16/6/13.
 */
public class MsgReceiverInfo extends Message {

    //编号
    @Expose
    private int Id;

    //收货人账号
    @Expose
    private int PassportId;

    //收货人名称
    @Expose
    private String ReceiverName;

    //电话
    @Expose
    private String ReceiverPhone;


    //是否默认 0:否 1：是
    @Expose
    private boolean IsDefault;

    //地区-省
    @Expose
    private String Province;

    //地区-市
    @Expose
    private String City;

    //地区-区
    @Expose
    private String District;

    @Expose
    private String Street;

    @Expose
    private String StreetNumber;

    @Expose
    private String IdNum;

    //详细地址
    @Expose
    private String AdCode;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public boolean isDefault() {
        return IsDefault;
    }

    public void setDefault(boolean aDefault) {
        IsDefault = aDefault;
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

    public String getIdNum() {
        return IdNum;
    }

    public void setIdNum(String idNum) {
        IdNum = idNum;
    }

    public String getAdCode() {
        return AdCode;
    }

    public void setAdCode(String adCode) {
        AdCode = adCode;
    }

    public int getPassportId() {
        return PassportId;
    }

    public void setPassportId(int passportId) {
        PassportId = passportId;
    }

    public MsgReceiverInfo(String address, int id, int passportId, String receiverName, String receiverPhone, boolean IsDefault, String province, String city, String district, String street, String streetNumber, String idNum, String adCode) {
        Id = id;
        PassportId = passportId;
        ReceiverName = receiverName;
        ReceiverPhone = receiverPhone;
        this.IsDefault = IsDefault;
        Province = province;
        City = city;
        District = district;
        Street = street;
        StreetNumber = streetNumber;
        IdNum = idNum;
        AdCode = adCode;
    }
}
