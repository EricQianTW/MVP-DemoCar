package com.clown.wyxc.x_address.address_list;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.DeliveryAddress;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface AddressListContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetDeliveryAddressByUsersIdResult(List<DeliveryAddress> result);
        void setDeleteDeliveryAddressByIdResult(int result);
        void setCilckSetDefaultResult(int result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getDeliveryAddressByUsersId(String param);

        void deleteDeliveryAddressById(String param);

        void cilckSetDefault(String param);
    }
}
