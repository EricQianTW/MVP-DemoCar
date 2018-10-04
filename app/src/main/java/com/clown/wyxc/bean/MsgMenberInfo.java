package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by JokerEric on 2016/7/4.
 */
public class MsgMenberInfo extends Message{
    //个人中心上部菜单
    @Expose
    private List<MsgAdsInpage> userMenuList;
    //积分
    @Expose
    private BigDecimal integral;

    //信用
    @Expose
    private BigDecimal credit;

//等级
    @Expose
    private int level;

//编号
    @Expose
    private int Id;

//用户编号
    @Expose
    private int userId;

    //用户账号（手机号码）
    @Expose
    private String accountMobile;

    //用户名
    @Expose
    private String nickName;

    //用户秘钥信息（以后登录就用此验证）
    @Expose
    private String verify;

    //头像
    @Expose
    private String headImg;

    //性别（0女 1男）
    @Expose
    private String sex;

    public List<MsgAdsInpage> getUserMenuList() {
        return userMenuList;
    }

    public void setUserMenuList(List<MsgAdsInpage> userMenuList) {
        this.userMenuList = userMenuList;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
