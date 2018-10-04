package com.clown.wyxc.x_orderinfo.orderlist;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by CZP on 2016/7/24.
 */
public class OrderListContract {
    interface View extends BaseInterfaceView<Present> {
        void setOrderConfirmByOrderIdResult(int result);
        void setOrderCancelByOrderIdResult(int result);
        void setOrderDeliverPayResult(String result);
    };

    interface Present extends BaseInterfacePresenter {
        void orderConfirmByOrderId(String param);
        void orderCancelByOrderId(String param);
        void orderDeliverPay(String param);
    }
}
