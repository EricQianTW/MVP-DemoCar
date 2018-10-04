package com.clown.wyxc.x_myorder;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface MyOrderContract {
    interface View extends BaseInterfaceView<Presenter> {
//        void getOrderInfoByUserId(List<MsgOrderInfoDetail> result);
//        void setCancelOrder(boolean result, int position);
//        void setRepayOrderById(String result);
//        void receiptOrderResult(Boolean isdone, int position);

        void setGetOrderListByOrderStateResult(List<OrderInfoDetaliResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
//        void getOrderInfoByUserId(String verify, int userId, int orderState, int currentPage, int pageSize);
//        void cancelOrder(String verify, int userId, List<Integer> orderInfoIdList, int position);
//        void repayOrderById(String verify, int userId, List<Integer> orderInfoIdList);
//        void receiptOrder(String verify, int userId, List<Integer> orderInfoIdList, int position);

        void getOrderListByOrderState(String param);
    }
}
