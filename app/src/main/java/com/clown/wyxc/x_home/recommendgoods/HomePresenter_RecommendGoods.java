package com.clown.wyxc.x_home.recommendgoods;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.Coupon;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter_RecommendGoods extends BasePresenter implements HomeContract_RecommendGoods.Presenter {
    private final HomeContract_RecommendGoods.View mView;

    public HomePresenter_RecommendGoods(@NonNull HomeContract_RecommendGoods.View loginView) {
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getCouponListByQuery(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com/api/Coupon/GetCouponListByQuery")
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
                                TypeToken<Message<List<Coupon>>> token = new TypeToken<Message<List<Coupon>>>() {
                                };
                                Message<List<Coupon>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetCouponListByQueryResult(dataPackage.getBody());
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

