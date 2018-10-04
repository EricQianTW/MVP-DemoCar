package com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo2;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailPresenter_Info2 implements GoodsDetailContract_Info2.Presenter {
    private final GoodsDetailContract_Info2.View mBannerView;

    public GoodsDetailPresenter_Info2(@NonNull GoodsDetailContract_Info2.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsDetailInfo() {

    }
}
