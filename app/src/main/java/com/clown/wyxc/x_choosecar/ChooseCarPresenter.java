package com.clown.wyxc.x_choosecar;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ChooseCarPresenter extends BasePresenter implements ChooseCarContract.Presenter{
    private final ChooseCarContract.View mView;

    public ChooseCarPresenter(@NonNull ChooseCarContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}