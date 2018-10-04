package com.clown.wyxc.x_address.address_new;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.DeliveryAddress;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface AddressNewContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setSaveDeliveryAddressResult(int result);

        void setDeleteDeliveryAddressByIdResult(int result);

        void setCilckSetDefaultResult(int result);

        void setGetDeliveryAddressByIdResult(DeliveryAddress result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void saveDeliveryAddress(String param);

        void deleteDeliveryAddressById(String param);

        void cilckSetDefault(String param);

        void getDeliveryAddressById(String param);
    }
}
