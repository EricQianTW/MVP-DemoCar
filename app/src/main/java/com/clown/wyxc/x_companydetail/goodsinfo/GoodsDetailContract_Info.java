package com.clown.wyxc.x_companydetail.goodsinfo;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MerchantResult;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_Info {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetMerchantByIdResult(MerchantResult result);
        void setCilckCollectionResult(int result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMerchantById(String param);
        void cilckCollection(String param);
    }
}
