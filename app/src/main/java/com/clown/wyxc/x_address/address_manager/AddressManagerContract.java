package com.clown.wyxc.x_address.address_manager;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgAddressInfo;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface AddressManagerContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setAddressListRes(List<MsgAddressInfo> result);
        void setDeleteAddressRes(boolean result, int position);
        void setDefaultAddressRes(boolean result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getAddressList(String verify, int userid);

        void deleteAddress(String verify, int userid, int newreceiverinfoid, int position);

        void defaultAddress(String verify, int userid, int newreceiverinfoid);
    }
}
