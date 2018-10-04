package com.clown.wyxc.x_shopmall.campaign;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgActivtiySale;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract_Campaign {

    interface View extends BaseInterfaceView<Presenter> {
        void setGoods(List<MsgActivtiySale> array);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getBanners(String deviceId, String verify, int userId);
    }
}
