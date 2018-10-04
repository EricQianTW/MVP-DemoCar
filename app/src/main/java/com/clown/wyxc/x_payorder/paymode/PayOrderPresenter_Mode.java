package com.clown.wyxc.x_payorder.paymode;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.PayPathResult;
import com.clown.wyxc.x_bean.ResBigDecimal;
import com.clown.wyxc.x_bean.ResString;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.zhy.http.okhttp.OkHttpUtils.postString;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class PayOrderPresenter_Mode extends BasePresenter implements PayOrderContract_Mode.Presenter {
    private final PayOrderContract_Mode.View mView;

    public PayOrderPresenter_Mode(@NonNull PayOrderContract_Mode.View loginView) {
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getOrderSumAmt(String param) {
        try {
            postString()
                    .url("http://api.ixiuc.com//api/Order/GetOrderSumAmt")
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
                                ResBigDecimal dataPackage = GSONUtils.fromJson(response, ResBigDecimal.class);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetOrderSumAmtResult(dataPackage.getBody());
                                } else {
                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }

                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPayPathBypayOrderNO(String param) {
        try {
            postString()
                    .url("http://api.ixiuc.com//api/Order/GetPayPathBypayOrderNO")
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
                                TypeToken<Message<List<PayPathResult>>> token = new TypeToken<Message<List<PayPathResult>>>() {
                                };
                                Message<List<PayPathResult>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetPayPathBypayOrderNOResult(dataPackage.getBody());
                                } else {
                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }

                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void orderPay(String param) {
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Order/OrderPay")
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
                                ResString dataPackage = GSONUtils.fromJson(response, ResString.class);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setOrderPayResult(dataPackage.getBody());
                                } else {
                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }

                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
