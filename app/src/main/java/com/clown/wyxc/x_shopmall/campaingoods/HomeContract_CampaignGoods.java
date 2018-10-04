package com.clown.wyxc.x_shopmall.campaingoods;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgSaleInfo;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract_CampaignGoods {

    interface View extends BaseInterfaceView<Presenter> {
        void setGoods(List<MsgSaleInfo> array);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoods(String deviceId, String verify, int userId);
    }
}
