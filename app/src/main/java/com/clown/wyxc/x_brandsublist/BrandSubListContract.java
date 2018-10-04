package com.clown.wyxc.x_brandsublist;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.CarInfoCResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface BrandSubListContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetCarInfoCXListResult(List<CarInfoCResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getCarInfoCXList(String param);
    }
}