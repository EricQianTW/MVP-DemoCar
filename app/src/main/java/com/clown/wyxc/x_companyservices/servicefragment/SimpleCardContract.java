package com.clown.wyxc.x_companyservices.servicefragment;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.ServiceItemsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface SimpleCardContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMerchantServiceItemsListByQueryResult(List<ServiceItemsResult> result);
        void setAddOrderByServiceResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMerchantServiceItemsListByQuery(String param);
        void addOrderByService(String param);
    }
}