package com.clown.wyxc.x_home.goodseller;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MerchantResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract_GoodSeller {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetMerchantListByQueryResult(List<MerchantResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMerchantListByQuery(String param);
    }
}
