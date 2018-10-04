package com.clown.wyxc.x_shopmallpayorder;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MsgOrderPay;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface PayOrderContract {
    interface View extends BaseInterfaceView<Presenter> {
        void getOrderInfoByUserIdResult(List<MsgOrderPay> arr);
        void OrderPayResult(String result);
        void GetOrderSumAmtResult(BigDecimal result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getOrderInfoByUserId(int userId, String guidOrderNo);
        void OrderPay(int userId, String orderGuid, String payPass, int payPathId);
        void GetOrderSumAmt(int userId, String orderGuid);
    }
}