package com.clown.wyxc.x_dispatching.store;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.Merchant;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface DispatchingStoreContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMerchantListByGuidgoodsOrderNOResult(List<Merchant> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMerchantListByGuidgoodsOrderNO(String param);
    }
}