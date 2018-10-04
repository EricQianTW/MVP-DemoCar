package com.clown.wyxc.x_shopmall.recommendgoods;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract_RecommendGoods {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetGoodsForIndexResult(List<GoodsResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsForIndex(String param);
    }
}
