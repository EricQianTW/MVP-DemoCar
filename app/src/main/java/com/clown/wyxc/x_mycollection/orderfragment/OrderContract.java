package com.clown.wyxc.x_mycollection.orderfragment;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.MerchantResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface OrderContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMyCollectionMerchantResult(List<MerchantResult> result);
        void setGetMyCollectionGoodsResult(List<GoodsResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMyCollectionMerchant(String param);
        void getMyCollectionGoods(String param);
    }
}