package com.clown.wyxc.x_firmorder.address;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.FirmOrderFormResult;
import com.clown.wyxc.x_bean.OrderFirmOrderAddressResult;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface FirmOrderContract_Address {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetOrderAddressByGuidgoodsOrderNOResult(OrderFirmOrderAddressResult result);
        void setGetOrderSureByGuidgoodsOrderNOResult(FirmOrderFormResult result);
        void setCreateOrderPayResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getOrderAddressByGuidgoodsOrderNO(String param);
        void getOrderSureByGuidgoodsOrderNO(String param);

        void createOrderPay(String param);
    }
}
