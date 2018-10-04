package com.clown.wyxc.x_firmorder.stores;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.FirmOrderFormResult;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface FirmOrderContract_Stores {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetOrderSureByGuidgoodsOrderNOResult(FirmOrderFormResult result);
        void setCreateOrderPayResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getOrderSureByGuidgoodsOrderNO(String param);

        void createOrderPay(String param);
    }
}
