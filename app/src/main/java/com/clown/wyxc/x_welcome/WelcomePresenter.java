package com.clown.wyxc.x_welcome;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class WelcomePresenter extends BasePresenter implements WelcomeContract.Presenter{
    private final WelcomeContract.View mView;

    public WelcomePresenter(@NonNull WelcomeContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
