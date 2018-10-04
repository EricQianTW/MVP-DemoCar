package com.clown.wyxc.x_vehicorder;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.VehiclListResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface VehicOrderContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetVehiclOrderListResult(List<VehiclListResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getVehiclOrderList(String param);
    }
}