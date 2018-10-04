package com.clown.wyxc.x_myrating;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MyRatingPresenter extends BasePresenter implements MyRatingContract.Presenter{
    private final MyRatingContract.View mView;

    public MyRatingPresenter(@NonNull MyRatingContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}