package com.clown.wyxc.x_brandlist;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.CarInfo;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface BrandListContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetCarInfoPPListResult(List<CarInfo> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getCarInfoPPList(String param);
    }
}