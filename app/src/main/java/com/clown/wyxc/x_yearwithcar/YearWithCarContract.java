package com.clown.wyxc.x_yearwithcar;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.CarInfo;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface YearWithCarContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetCarInfoCXDListResult(List<CarInfo> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getCarInfoCXDList(String param);
    }
}