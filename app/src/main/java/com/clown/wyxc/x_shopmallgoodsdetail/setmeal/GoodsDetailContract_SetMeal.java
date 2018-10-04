package com.clown.wyxc.x_shopmallgoodsdetail.setmeal;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgGoodsPackage;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_SetMeal {

    interface View extends BaseInterfaceView<Presenter> {
        void setGoodsPackage(List<MsgGoodsPackage> array);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsPackage(String verify, int userId, int goodsId);
    }
}
