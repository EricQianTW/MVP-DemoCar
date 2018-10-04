package com.clown.wyxc.x_settleorder.orderfragment;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;

import java.util.List;

/**
 * Created by eric_clown on 2017/6/28.
 */

public interface SettleOrderCellContract {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetOrderListByOrderStateResult(List<OrderInfoDetaliResult> result);
        void setOrderCancelByOrderIdResult(int result);
        void setOrderConfirmByOrderIdResult(int result);
        void setOrderPayResult(String result);
        void setOrderDeleteByOrderIdResult(int result);
        void setOrderDeliverPayResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getOrderListByOrderState(String param);
        void orderCancelByOrderId(String param);

        void orderConfirmByOrderId(String param);
        void orderPay(String param);

        void orderDeleteByOrderId(String param);
        void orderDeliverPay(String param);
    }

}
