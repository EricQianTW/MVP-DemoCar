package com.clown.wyxc.x_shopmallgoodsdetail.goodsassess;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GetEvaluateByGoodsIdResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Assess {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetEvaluateByGoodsIdResult(List<GetEvaluateByGoodsIdResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getEvaluateByGoodsId(String param);
    }
}
