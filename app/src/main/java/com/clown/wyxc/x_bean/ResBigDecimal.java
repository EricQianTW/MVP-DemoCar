package com.clown.wyxc.x_bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 接口返回消息
 *
 * @author dzh
 */

public class ResBigDecimal implements Serializable {
    @Expose
    private Integer customCode;
    @Expose
    private String info;
    @Expose
    private Integer statusCode;
    @Expose
    private BigDecimal body;

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

    public BigDecimal getBody() {
        return body;
    }

    public void setBody(BigDecimal body) {
        body = body;
    }
}
