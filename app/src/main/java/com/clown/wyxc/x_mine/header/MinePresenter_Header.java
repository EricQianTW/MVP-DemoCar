package com.clown.wyxc.x_mine.header;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.Users;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class MinePresenter_Header extends BasePresenter implements MineContract_Header.Presenter {
    private final MineContract_Header.View mView;

    public MinePresenter_Header(@NonNull MineContract_Header.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getUsersById(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com/api/Users/GetUsersById")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(param)
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(okhttp3.Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                TypeToken<Message<Users>> token = new TypeToken<Message<Users>>() {
                                };
                                Message<Users> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetUsersByIdResult(dataPackage.getBody());
                                } else {
                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }

                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
