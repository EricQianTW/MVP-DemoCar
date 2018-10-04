package com.clown.wyxc.enums;

/**
 * Created by JokerEric on 2016/7/3.
 */
public enum OrderPayType {
    // 利用构造函数传参
    XIANJIN(1),XINYONGFENQI (2),JIFENG(3),XIANJINDIYONG (4),DINGDANYOUHUI(5),ZHIFUBAO (6),WEIXIN(7),HUODAOFUKUAN (11);

    // 定义私有变量
    private int nCode ;

    // 构造函数，枚举类型只能为私有
    private OrderPayType( int _nCode) {
        this . nCode = _nCode;
    }

    @Override
    public String toString() {
        return String.valueOf ( this . nCode );
    }

    public int getnCode(){
        return this.nCode;
    }
}
