package com.clown.wyxc.x_shopmall.campaingoods;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter_CampaignGoods extends BasePresenter implements HomeContract_CampaignGoods.Presenter {
    private final HomeContract_CampaignGoods.View mBannerView;

    public HomePresenter_CampaignGoods(@NonNull HomeContract_CampaignGoods.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoods(String deviceId, String verify, int userId) {
    }
}
