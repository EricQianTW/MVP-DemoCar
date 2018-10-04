package com.clown.wyxc.enums;

/**
 * Created by JokerEric on 2016/6/24.
 */
public enum  LoginRes {
    // 利用构造函数传参
    REGISTER(1), LOGIN (2);

    // 定义私有变量
    private int nCode ;

    // 构造函数，枚举类型只能为私有
    private LoginRes( int _nCode) {
        this . nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf ( this . nCode );
    }
}
