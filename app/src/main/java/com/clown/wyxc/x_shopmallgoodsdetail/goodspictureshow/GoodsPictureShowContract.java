package com.clown.wyxc.x_shopmallgoodsdetail.goodspictureshow;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsInfoResult;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface GoodsPictureShowContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetGoodsByIdResult(GoodsInfoResult result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsById(String param);
    }
}