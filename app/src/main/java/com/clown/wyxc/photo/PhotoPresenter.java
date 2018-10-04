package com.clown.wyxc.photo;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PhotoPresenter extends BasePresenter implements PhotoContract.Presenter{
    private final PhotoContract.View mLoginView;

    public PhotoPresenter(@NonNull PhotoContract.View loginView){
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getArticle() {
    }
}
