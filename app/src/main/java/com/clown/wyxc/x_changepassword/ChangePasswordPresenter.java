package com.clown.wyxc.x_changepassword;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ChangePasswordPresenter extends BasePresenter implements ChangePasswordContract.Presenter{
    private final ChangePasswordContract.View mView;

    public ChangePasswordPresenter(@NonNull ChangePasswordContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}