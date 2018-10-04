package com.clown.wyxc.x_vehicletradedetail;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.VehiclResult;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface VehicletradedetailContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetVehiclByIdResult(VehiclResult result);
        void setCreateVehiclOrderResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getVehiclById(String param);
        void createVehiclOrder(String param);
    }
}
