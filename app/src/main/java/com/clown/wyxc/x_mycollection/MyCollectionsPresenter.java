package com.clown.wyxc.x_mycollection;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MyCollectionsPresenter extends BasePresenter implements MyCollectionsContract.Presenter{
    private final MyCollectionsContract.View mView;

    public MyCollectionsPresenter(@NonNull MyCollectionsContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}