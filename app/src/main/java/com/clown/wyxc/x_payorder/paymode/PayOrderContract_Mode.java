package com.clown.wyxc.x_payorder.paymode;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.PayPathResult;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface PayOrderContract_Mode {

    interface View extends BaseInterfaceView<Presenter> {
//        void setOrderSumAmtRes(BigDecimal decimal);
//        void setFullCutOrderSumAmtRes(MsgFullCut fullCut);
//        void setSysetemPaySplitSetRes(List<Memberaccount_Set> result);
//        void setOrderPayCenterRes(List<MsgOrderPay> result);

        void setGetOrderSumAmtResult(BigDecimal result);
        void setGetPayPathBypayOrderNOResult(List<PayPathResult> result);
        void setOrderPayResult(String result);
        void settest();
    }

    interface Presenter extends BaseInterfacePresenter {
//        void getOrderSumAmt(String verify, int userid, String orderguid);
//        void getFullCutOrderSumAmt(String verify, int userid, String orderguid);
//        void getSysetemPaySplitSet(String verify, int userid, String orderguid);
//        void getOrderPayCenter(String verify, int userid, String orderguid);

        void getOrderSumAmt(String param);
        void getPayPathBypayOrderNO(String param);
        void orderPay(String param);
        void test();
    }
}
