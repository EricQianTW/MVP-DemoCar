package com.clown.wyxc.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 接口返回消息
 *
 * @author dzh
 */

public class ResBigDecimal implements Serializable {
    private String Token;
    private String CustomCode;
    private String CustomMessage;
    private String StackTrace;
    // 1:成功;0:失败
    private int State;
    private BigDecimal Body;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getCustomCode() {
        return CustomCode;
    }

    public void setCustomCode(String customCode) {
        CustomCode = customCode;
    }

    public String getCustomMessage() {
        return CustomMessage;
    }

    public void setCustomMessage(String customMessage) {
        CustomMessage = customMessage;
    }

    public String getStackTrace() {
        return StackTrace;
    }

    public void setStackTrace(String stackTrace) {
        StackTrace = stackTrace;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public BigDecimal getBody() {
        return Body;
    }

    public void setBody(BigDecimal body) {
        Body = body;
    }
}
