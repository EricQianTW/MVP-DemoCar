package com.clown.wyxc.x_confirmmaintainorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ConfirmMaintainOrderPresenter extends BasePresenter implements ConfirmMaintainOrderContract.Presenter{
    private final ConfirmMaintainOrderContract.View mView;

    public ConfirmMaintainOrderPresenter(@NonNull ConfirmMaintainOrderContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}