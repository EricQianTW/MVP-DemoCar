package com.clown.wyxc.x_address.address_manager;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.bean.MsgAddressInfo;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.ResBoolean;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class AddressManagerPresenter extends BasePresenter implements AddressManagerContract.Presenter{
    private final AddressManagerContract.View mLoginView;

    public AddressManagerPresenter(@NonNull AddressManagerContract.View loginView){
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getAddressList(String verify, int userid) {
        try {
            OkHttpUtils
                    .get()
                    .url(Constants.HTTP_IP)
                    .addParams("_Interface", "BeautifulCause.USL.Interface.AddressInfoAbout")
                    .addParams("_Method", "MEOrderAddress")
                    .addParams("deviceid", Constants.serialNumber)
                    .addParams("verify", GSONUtils.toJson(verify))
                    .addParams("userid", GSONUtils.toJson(userid))
                    .build()//
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                TypeToken<Message<List<MsgAddressInfo>>> token = new TypeToken<Message<List<MsgAddressInfo>>>() {
                                };
                                Message<List<MsgAddressInfo>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mLoginView.setAddressListRes(dataPackage.getBody());
                                } else {
                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
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
    public void deleteAddress(String verify, int userid, int newreceiverinfoid, final int position) {
        try {
            OkHttpUtils
                    .get()
                    .url(Constants.HTTP_IP)
                    .addParams("_Interface", "BeautifulCause.USL.Interface.AddressInfoAbout")
                    .addParams("_Method", "MEOrderAddressRemove")
                    .addParams("deviceid", Constants.serialNumber)
                    .addParams("verify", GSONUtils.toJson(verify))
                    .addParams("userid", GSONUtils.toJson(userid))
                    .addParams("newreceiverinfoid", GSONUtils.toJson(newreceiverinfoid))
                    .build()//
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                ResBoolean dataPackage = GSONUtils.fromJson(response, ResBoolean.class);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mLoginView.setDeleteAddressRes(dataPackage.getBody(), position);
                                } else {
                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
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
    public void defaultAddress(String verify, int userid, int newreceiverinfoid) {
        try {
            OkHttpUtils
                    .get()
                    .url(Constants.HTTP_IP)
                    .addParams("_Interface", "BeautifulCause.USL.Interface.AddressInfoAbout")
                    .addParams("_Method", "MEOrderAddressDefault")
                    .addParams("deviceid", Constants.serialNumber)
                    .addParams("verify", GSONUtils.toJson(verify))
                    .addParams("userid", GSONUtils.toJson(userid))
                    .addParams("newreceiverinfoid", GSONUtils.toJson(newreceiverinfoid))
                    .build()//
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                ResBoolean dataPackage = GSONUtils.fromJson(response, ResBoolean.class);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mLoginView.setDefaultAddressRes(dataPackage.getBody());
                                } else {
                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }
                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
