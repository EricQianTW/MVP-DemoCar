package com.clown.wyxc.x_orderinfo;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;

/**
 * Created by CZP on 2016/7/24.
 */
public interface OrderInfoContract {

    interface View extends BaseInterfaceView<Present> {
        void setGetOrderByOrderIdResult(OrderInfoDetaliResult result);
    };

    interface Present extends BaseInterfacePresenter {
        void getOrderByOrderId(String param);
    }
}
