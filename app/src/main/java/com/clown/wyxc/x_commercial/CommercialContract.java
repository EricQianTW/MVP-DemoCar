package com.clown.wyxc.x_commercial;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MerchantResult;
import com.clown.wyxc.x_bean.ServiceItemsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface CommercialContract {
    interface View extends BaseInterfaceView<CommercialContract.Presenter> {
        void setGetServiceItemsListByQueryResult(List<ServiceItemsResult> result);
        void setGetMerchantListByQueryResult(List<MerchantResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getServiceItemsListByQuery();
        void getMerchantListByQuery(String param);
    }
}
