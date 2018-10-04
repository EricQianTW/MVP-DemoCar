package com.clown.wyxc.x_home.title;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter_Title extends BasePresenter implements HomeContract_Title.Presenter {
    private final HomeContract_Title.View mBannerView;

    public HomePresenter_Title(@NonNull HomeContract_Title.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

}
