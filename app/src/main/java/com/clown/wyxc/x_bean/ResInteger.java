package com.clown.wyxc.x_bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 接口返回消息
 *
 * @author dzh
 */

public class ResInteger implements Serializable {
    @Expose
    private Integer customCode;
    @Expose
    private String info;
    @Expose
    private Integer statusCode;
    @Expose
    private Integer body;

    public Integer getCustomCode() {
        return customCode;
    }

    public void setCustomCode(Integer customCode) {
        this.customCode = customCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }
}
