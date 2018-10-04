package com.clown.wyxc.x_payorder.payorder;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MsgOrderPay;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface PayOrderContract_PayOrder {

    interface View extends BaseInterfaceView<Presenter> {
        void setOrderUserPayRes(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void orderUserPay(String verify, int userid, String paypass, String orderguid, List<MsgOrderPay> li);
    }
}
