package com.clown.wyxc.x_shopmallgoodsdetail.setmealpage;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailPresenter_SetMealPage implements GoodsDetailContract_SetMealPage.Presenter {
    private final GoodsDetailContract_SetMealPage.View mBannerView;

    public GoodsDetailPresenter_SetMealPage(@NonNull GoodsDetailContract_SetMealPage.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
