package com.clown.wyxc.x_mine;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MinePresenter extends BasePresenter implements MineContract.Presenter{
    private final MineContract.View mLoginView;

    public MinePresenter(@NonNull MineContract.View loginView){
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
