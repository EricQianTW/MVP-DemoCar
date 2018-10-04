package com.clown.wyxc.x_shopmallgoodsdetail.goodsattribute;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsService;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Attr {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetGoodsServiceGoodsIdResult(List<GoodsService> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsServiceGoodsId(String param);
    }
}
