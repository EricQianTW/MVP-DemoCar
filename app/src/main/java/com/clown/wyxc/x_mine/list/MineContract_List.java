package com.clown.wyxc.x_mine.list;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.CenterMenuResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface MineContract_List {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetCarInfoPPListResult(List<CenterMenuResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getCarInfoPPList(String param);
    }
}
