package com.clown.wyxc.x_mine.icon;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class MinePresenter_Icon implements MineContract_Icon.Presenter {
    private final MineContract_Icon.View mBannerView;

    public MinePresenter_Icon(@NonNull MineContract_Icon.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
