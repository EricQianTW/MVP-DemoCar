package com.clown.wyxc.x_classify.servicefragment;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface SimpleCardContract {
    interface View extends BaseInterfaceView<Presenter> {

        void setGetGoodsByGoodsTypeResult(List<GoodsResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {

        void getGoodsByGoodsType(String param);
    }
}