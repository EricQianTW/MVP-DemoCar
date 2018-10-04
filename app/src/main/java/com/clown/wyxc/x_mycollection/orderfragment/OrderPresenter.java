package com.clown.wyxc.x_mycollection.orderfragment;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.MerchantResult;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class OrderPresenter extends BasePresenter implements OrderContract.Presenter{
    private final OrderContract.View mView;

    public OrderPresenter(@NonNull OrderContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void getMyCollectionMerchant(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com/api/Merchant/GetMyCollectionMerchant")
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
                                TypeToken<Message<List<MerchantResult>>> token = new TypeToken<Message<List<MerchantResult>>>() {
                                };
                                Message<List<MerchantResult>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetMyCollectionMerchantResult(dataPackage.getBody());
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

    @Override
    public void getMyCollectionGoods(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Goods/GetMyCollectionGoods")
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
                                TypeToken<Message<List<GoodsResult>>> token = new TypeToken<Message<List<GoodsResult>>>() {
                                };
                                Message<List<GoodsResult>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetMyCollectionGoodsResult(dataPackage.getBody());
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

    @Override
    public void start() {

    }
}