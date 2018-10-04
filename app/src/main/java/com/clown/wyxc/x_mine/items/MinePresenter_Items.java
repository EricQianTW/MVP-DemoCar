package com.clown.wyxc.x_mine.items;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class MinePresenter_Items implements MineContract_Items.Presenter {
    private final MineContract_Items.View mBannerView;

    public MinePresenter_Items(@NonNull MineContract_Items.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
