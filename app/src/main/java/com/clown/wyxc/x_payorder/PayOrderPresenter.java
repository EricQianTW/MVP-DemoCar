package com.clown.wyxc.x_payorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PayOrderPresenter extends BasePresenter implements PayOrderContract.Presenter{
    private final PayOrderContract.View mView;

    public PayOrderPresenter(@NonNull PayOrderContract.View loginView){
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
