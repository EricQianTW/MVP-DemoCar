package com.clown.wyxc.x_base;

import java.io.Serializable;

public class Message<T> implements Serializable {
    private int customCode;
    private String info;
    private int statusCode;
    private T body;

    public int getCustomCode() {
        return customCode;
    }

    public void setCustomCode(int customCode) {
        this.customCode = customCode;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}