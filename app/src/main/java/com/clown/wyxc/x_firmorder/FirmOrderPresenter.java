package com.clown.wyxc.x_firmorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class FirmOrderPresenter extends BasePresenter implements FirmOrderContract.Presenter{
    private final FirmOrderContract.View mView;

    public FirmOrderPresenter(@NonNull FirmOrderContract.View loginView){
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
