package com.clown.wyxc.x_dispatching.home;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class DispatchingHomePresenter extends BasePresenter implements DispatchingHomeContract.Presenter{
    private final DispatchingHomeContract.View mView;

    public DispatchingHomePresenter(@NonNull DispatchingHomeContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}