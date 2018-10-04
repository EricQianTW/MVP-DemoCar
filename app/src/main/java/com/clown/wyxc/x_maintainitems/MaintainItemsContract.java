package com.clown.wyxc.x_maintainitems;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MaintainItmeGoodsChangeResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface MaintainItemsContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMaintainGoodsChangeResult(List<MaintainItmeGoodsChangeResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMaintainGoodsChange(String param);
    }
}