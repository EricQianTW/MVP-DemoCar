package com.clown.wyxc.x_search;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.ScreenConditionsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface SearchContract {
    interface View extends BaseInterfaceView<Presenter> {
//        void setConditionsByKey(List<MsgScreenConditions> array);
//        void setSaleInfoListRes(List<MsgSaleInfo> array);

        void setGetScreenConditionsResult(List<ScreenConditionsResult> result);

        void setGetGoodsByScreenConditionsResult(List<GoodsResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
//        void getConditionsByKey(int userId, String keyWords);
//
//        void getSaleInfoList(int userId, String keyValue
//                , List<MsgScreenConditions> screenConditions, int currentPage, int pageSize, int orderCriteria, int ispositive);

        void getScreenConditions(String param);

        void getGoodsByScreenConditions(String param);
    }
}
