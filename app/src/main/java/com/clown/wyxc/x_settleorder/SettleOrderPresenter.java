package com.clown.wyxc.x_settleorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class SettleOrderPresenter extends BasePresenter implements SettleOrderContract.Presenter{
    private final SettleOrderContract.View mView;

    public SettleOrderPresenter(@NonNull SettleOrderContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}