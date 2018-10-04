package com.clown.wyxc.x_shopmallgoodsdetail.goodsplaceorder;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.ActualGoodsListResult;
import com.clown.wyxc.x_bean.GoodsAttrResult;
import com.clown.wyxc.x_bean.GoodsInfoResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface GoodsDetailContract_PlaceOrder {

    interface View extends BaseInterfaceView<Presenter> {

        void setGetActualGoodsByGoodsIdResult(List<ActualGoodsListResult> result);

        void setGetGoodsAttrByGoodsIdResult(List<GoodsAttrResult> result);

        void setGetGoodsByIdResult(GoodsInfoResult result);

        void setAddShoppingCartResult(int result);

        void setAddOrderByGoodsDetailResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {

        void getGoodsAttrByGoodsId(String param);

        void getActualGoodsByGoodsId(String param);

        void getGoodsById(String param);

        void addShoppingCart(String param);

        void addOrderByGoodsDetail(String param);
    }
}
