package com.clown.wyxc.x_payorder.stores;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgFirmOrderForm;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface PayOrderContract_Stores {

    interface View extends BaseInterfaceView<Presenter> {
        void setStores(MsgFirmOrderForm array);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getStores(String s, int i);
    }
}
