package com.clown.wyxc.x_shopmallgoodsdetail.goodswebview;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_WebView {

    interface View extends BaseInterfaceView<Presenter> {
        void setWebsite(String info);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsDetailInfo(String verify, int userId, int goodsId);
    }
}
