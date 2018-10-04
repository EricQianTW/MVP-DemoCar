package com.clown.wyxc.x_shopcar;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.ShoppingCartResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface ShopCarContract {
    interface View extends BaseInterfaceView<Presenter> {

        void setDeleteShoppingCartResult(int result);

        void setGetShoppingCartResult(List<ShoppingCartResult> result);

        void setAddOrderByShoppingCarResult(String result);
    }

    interface Presenter extends BaseInterfacePresenter {

        void deleteShoppingCart(String param);

        void getShoppingCart(String param);

        void addOrderByShoppingCar(String param);
    }
}
