package com.clown.wyxc.x_dispatching;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class DispatchingPresenter extends BasePresenter implements DispatchingContract.Presenter{
    private final DispatchingContract.View mView;

    public DispatchingPresenter(@NonNull DispatchingContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}