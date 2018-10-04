package com.clown.wyxc.bean;

import com.clown.wyxc.base.Message;

/**
 * Created by eric_shenn on 2017/4/26.
 */

public class Test extends Message {

    private String UserName;
    private String UserPwd;
    private usertest1 usertest1;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    public com.clown.wyxc.bean.usertest1 getUsertest1() {
        return usertest1;
    }

    public void setUsertest1(com.clown.wyxc.bean.usertest1 usertest1) {
        this.usertest1 = usertest1;
    }
}
