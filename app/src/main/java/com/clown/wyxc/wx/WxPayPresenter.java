package com.clown.wyxc.wx;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.WeiXinPayResult;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class WxPayPresenter extends BasePresenter implements WxPayContract.Presenter {
    private final WxPayContract.View mLoginView;

    private PayReq req;

    public WxPayPresenter(@NonNull WxPayContract.View loginView) {
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void weiXinPayApp(final IWXAPI msgApi,String param) {
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Pay/WeiXinPayApp")
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
                                TypeToken<Message<WeiXinPayResult>> token = new TypeToken<Message<WeiXinPayResult>>() {
                                };
                                Message<WeiXinPayResult> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == 1) {
                                    WeiXinPayResult temp = dataPackage.getBody();
                                    msgApi.registerApp(Constants.APP_ID);
                                    req = new PayReq();
                                    req.appId = Constants.APP_ID;
                                    req.partnerId = temp.getPartnerId();
                                    req.prepayId = temp.getPrepayId();
                                    req.packageValue = "Sign=WXPay";
                                    req.nonceStr = temp.getNonceStr();
                                    req.timeStamp = temp.getTimeStamp();
                                    req.sign = temp.getSign();

                                    msgApi.sendReq(req);

                                    mLoginView.setWeiXinPayAppResult();

                                } else {
//                                mLoginView.showError(dataPackage.getCustomCode(),dataPackage.getCustomMessage());
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
}
