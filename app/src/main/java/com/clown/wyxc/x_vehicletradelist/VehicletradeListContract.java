package com.clown.wyxc.x_vehicletradelist;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.VehiclBrand;
import com.clown.wyxc.x_bean.VehiclListResult;
import com.clown.wyxc.x_bean.VehiclPriceRangeResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface VehicletradeListContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetVehiclBrandListResult(List<VehiclBrand> result);
        void setGetVehiclPriceRangeListResult(List<VehiclPriceRangeResult> result);
        void setGetVehiclByQueryResult(List<VehiclListResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getVehiclBrandList(String param);
        void getVehiclPriceRangeList(String param);
        void getVehiclByQuery(String param);
    }
}
