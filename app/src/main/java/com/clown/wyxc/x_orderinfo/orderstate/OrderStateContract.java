package com.clown.wyxc.x_orderinfo.orderstate;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by CZP on 2016/7/24.
 */
public interface OrderStateContract {

    interface View extends BaseInterfaceView<Present> {};

    interface Present extends BaseInterfacePresenter {}
}
