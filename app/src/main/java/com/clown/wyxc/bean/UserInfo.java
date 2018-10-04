package com.clown.wyxc.bean;

import com.google.gson.annotations.Expose;
import com.clown.wyxc.base.Message;

/**
 * Created by eric_qiantw on 16/4/21.
 */
public class UserInfo extends Message {

    @Expose
    private String userId;

    @Expose
    private String passwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
