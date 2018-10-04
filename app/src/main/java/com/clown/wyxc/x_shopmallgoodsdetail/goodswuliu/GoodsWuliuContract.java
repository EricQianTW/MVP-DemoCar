package com.clown.wyxc.x_shopmallgoodsdetail.goodswuliu;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MsgInGoodsExpress;

/**
 * Created by cc on 2017/5/28.
 */

public interface GoodsWuliuContract {

    interface View extends BaseInterfaceView<Presenter> {
        void getGoodsDetailInfoResult(MsgInGoodsExpress msgInGoodsExpress);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsDetailInfo(int userid, int goodsId, String ip);
    }
}
