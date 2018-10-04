package com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsInfoResult;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Info {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetGoodsByIdResult(GoodsInfoResult result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsById(String param);
    }
}
