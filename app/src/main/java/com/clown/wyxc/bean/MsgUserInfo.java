package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

/**
 * 用户信息
 *
 * @author wqf
 *
 */
@SuppressWarnings("rawtypes")
public class MsgUserInfo extends Message {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 用户编号
     */
    @Expose
    private Integer userId;
    /**
     * 用户账号（手机号码）
     */
    @Expose
    private String accountMobile;
    /**
     * 用户名
     */
    @Expose
    private String nickName;
    /**
     * 用户秘钥信息（以后登录就用此验证）
     */
    @Expose
    private String verify;
    /**
     * 头像
     */
    @Expose
    private String headImg;
    /**
     * 账户余额（小数点后两位）
     */
    @Expose
    private BigDecimal moneyAccount;
    /**
     * 奖金余额（小数点后两位）
     */
    @Expose
    private BigDecimal moneyReward;
    /**
     * 购物币（小数点后两位）
     */
    @Expose
    private BigDecimal moneyCoin;
    /**
     * 我的订单数
     */
    @Expose
    private Integer orderCnt;
    /**
     * 下属会员数
     */
    @Expose
    private Integer staffCnt;
    /**
     * 性别（0女 1男）
     */
    @Expose
    private Integer sex;
    /**
     * 默认收货地址
     */
    @Expose
    private String addressInfo;
    /**
     * 等级
     */
    @Expose
    private Integer level;
    /**
     * 是否进行过认证
     */
    @Expose
    private Integer VefiryUsed;
    /**
     *  分享url
     */
    private String shareUrl;

    private String qrCodeStr;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public BigDecimal getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(BigDecimal moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public BigDecimal getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(BigDecimal moneyReward) {
        this.moneyReward = moneyReward;
    }

    public BigDecimal getMoneyCoin() {
        return moneyCoin;
    }

    public void setMoneyCoin(BigDecimal moneyCoin) {
        this.moneyCoin = moneyCoin;
    }

    public Integer getOrderCnt() {
        return orderCnt;
    }

    public void setOrderCnt(Integer orderCnt) {
        this.orderCnt = orderCnt;
    }

    public Integer getStaffCnt() {
        return staffCnt;
    }

    public void setStaffCnt(Integer staffCnt) {
        this.staffCnt = staffCnt;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getVefiryUsed() {
        return VefiryUsed;
    }

    public void setVefiryUsed(Integer vefiryUsed) {
        VefiryUsed = vefiryUsed;
    }

    public String getQrCodeStr() {
        return qrCodeStr;
    }

    public void setQrCodeStr(String qrCodeStr) {
        this.qrCodeStr = qrCodeStr;
    }
}
