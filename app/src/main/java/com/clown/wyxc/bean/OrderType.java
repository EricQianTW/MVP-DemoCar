package com.clown.wyxc.bean;

/**
 * Created by CZP on 2016/7/17.
 */
public enum OrderType {
    REPAIR("维修",4),
    ONLYMONEY("仅退款",1),
    MONEYGOODS("退货退款",2),
    CHANGEGOODS("换货",3);

    private int index;
    private String text;

    private OrderType(String name,int i){
        text = name;
        index = i;
    }

    public static String getText(int index){
        for(OrderType i:OrderType.values()){
            if(i.getIndex() == index){
                return i.getText();
            }
        }
        return  null;
    }

    public int getIndex(){
        return index;
    }

    public String getText(){
        return text;
    }

    public static int getIndex(String text){
        if(text.equals(ONLYMONEY.getText())){
            return 1;
        }else if(text.equals(MONEYGOODS.getText())){
            return 2;
        }else if(text.equals(CHANGEGOODS.getText())){
            return 3;
        }else if(text.equals(REPAIR.getText())){
            return 4;
        }
        return 0;
    }
}
