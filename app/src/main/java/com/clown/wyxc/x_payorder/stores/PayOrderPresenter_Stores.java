package com.clown.wyxc.x_payorder.stores;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class PayOrderPresenter_Stores implements PayOrderContract_Stores.Presenter {
    private final PayOrderContract_Stores.View mView;

    public PayOrderPresenter_Stores(@NonNull PayOrderContract_Stores.View loginView){
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getStores(String s, int i) {

        mView.setStores(null);
    }
}
