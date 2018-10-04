package com.clown.wyxc.x_home;

import android.support.annotation.NonNull;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter implements HomeContract.Presenter {
    private final HomeContract.View mView;

    public HomePresenter(@NonNull HomeContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void test() {
        OkHttpUtils
                .get()
                .url("http://139.196.72.193/api/test")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if ("\"0\"".equals(response)) {
                            mView.settest();
                        }
                    }
                });
    }
}
