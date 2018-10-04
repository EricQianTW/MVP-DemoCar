package com.clown.wyxc.x_shopmall;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter implements HomeContract.Presenter {
    private final HomeContract.View mView;

    public HomePresenter(@NonNull HomeContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
