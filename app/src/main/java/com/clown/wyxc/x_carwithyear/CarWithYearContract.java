package com.clown.wyxc.x_carwithyear;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.CarInfo;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface CarWithYearContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetCarInfoYearListResult(List<CarInfo> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getCarInfoYearList(String param);
    }
}