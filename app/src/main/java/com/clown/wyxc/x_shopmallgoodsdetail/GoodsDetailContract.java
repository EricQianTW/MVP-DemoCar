package com.clown.wyxc.x_shopmallgoodsdetail;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsInfoResult;

/**
 * Created by cc on 2016/8/4.
 */
public interface GoodsDetailContract {

    interface View extends BaseInterfaceView<Present> {
        void setGetGoodsByIdResult(GoodsInfoResult result);

        void setCilckCollectionResult(int result);
    };

    interface Present extends BaseInterfacePresenter {
        void getGoodsById(String param);
        void cilckCollection(String param);
    }
}
