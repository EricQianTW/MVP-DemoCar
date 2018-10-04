package com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo2;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.GoodsDetailInfo;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Info2 {

    interface View extends BaseInterfaceView<Presenter> {
        void initView(GoodsDetailInfo info);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsDetailInfo();
    }
}
