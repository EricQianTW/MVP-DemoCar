package com.clown.wyxc.bean;

/**
 * Created by CZP on 2016/7/17.
 */
public enum OrderState{
    CHEXIAO(-99,"撤销"),
    BOHUI(-1,"卖家驳回"),
    SHENQING(1,"买家申请"),
    QUERENSHENQING(2,"卖家确认"),
    JISONG(3,"买家寄送"),
    JIANCHA(4,"卖家收到检查或维修"),
    TUIQIANJIHUI(5,"卖家寄回或退钱"),
    SUCCESS(6,"买家确认完成");

    private int index;
    private String message;

    private OrderState(int i,String message){
        index = i;
        this.message = message;
    }

    public String getText(){
        return message;
    }

    public int getIndex(){
        return index;
    }

    public static String getText(int i){
        String text = null;
        switch (i){
            case -99:
                text = CHEXIAO.getText();
                break;
            case -1:
                text = BOHUI.getText();
                break;
            case 1:
                text = SHENQING.getText();
                break;
            case 2:
                text = QUERENSHENQING.getText();
                break;
            case 3:
                text = JISONG.getText();
                break;
            case 4:
                text = JIANCHA.getText();
                break;
            case 5:
                text = TUIQIANJIHUI.getText();
                break;
            case 6:
                text = SUCCESS.getText();
                break;
            default:
                break;

        }
        return text;
    }
}
