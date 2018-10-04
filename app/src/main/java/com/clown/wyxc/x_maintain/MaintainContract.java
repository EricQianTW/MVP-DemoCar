package com.clown.wyxc.x_maintain;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MaintainItemsResult;
import com.clown.wyxc.x_bean.MyCarsDefaultResult;

import java.util.ArrayList;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface MaintainContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMaintainItemsResult(ArrayList<MaintainItemsResult> result);
        void setGetMyCarsDefaultByUsersIdResult(MyCarsDefaultResult result);
        void setMaintainChangeGoodsResult(MaintainItemsResult result);
        void setAddOrderByMaintainResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMaintainItems(String param);
        void getMyCarsDefaultByUsersId(String param);
        void maintainChangeGoods(String param);
        void addOrderByMaintain(String param);
    }
}