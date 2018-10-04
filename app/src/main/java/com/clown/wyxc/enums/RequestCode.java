package com.clown.wyxc.enums;

/**
 * Created by JokerEric on 2016/7/1.
 */
public enum RequestCode {
    // 利用构造函数传参
    JUMP(10001);

    // 定义私有变量
    private int nCode ;

    // 构造函数，枚举类型只能为私有
    private RequestCode( int _nCode) {
        this . nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf ( this . nCode );
    }
}
