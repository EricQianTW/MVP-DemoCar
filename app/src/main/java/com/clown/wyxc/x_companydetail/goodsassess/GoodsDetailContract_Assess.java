package com.clown.wyxc.x_companydetail.goodsassess;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MerchantEvaluateResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Assess {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetMerchantEvaluateResult(List<MerchantEvaluateResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMerchantEvaluate(String param);
    }
}
