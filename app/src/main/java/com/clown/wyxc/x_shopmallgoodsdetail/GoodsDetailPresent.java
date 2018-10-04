package com.clown.wyxc.x_shopmallgoodsdetail;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.GoodsInfoResult;
import com.clown.wyxc.x_bean.ResInt;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by cc on 2016/8/4.
 */
public class GoodsDetailPresent extends BasePresenter implements GoodsDetailContract.Present {
    private GoodsDetailContract.View mView;

    public GoodsDetailPresent(GoodsDetailContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void cilckCollection(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Goods/CilckCollection")
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
                                ResInt dataPackage = GSONUtils.fromJson(response, ResInt.class);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setCilckCollectionResult(dataPackage.getBody());
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
    public void getGoodsById(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Goods/GetGoodsById")
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
                                TypeToken<Message<GoodsInfoResult>> token = new TypeToken<Message<GoodsInfoResult>>() {
                                };
                                Message<GoodsInfoResult> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetGoodsByIdResult(dataPackage.getBody());
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
