package com.clown.wyxc.x_findpaypassword;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.ResInt;
import com.clown.wyxc.x_bean.ResString;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class FindPayPasswdPresenter extends BasePresenter implements FindPayPasswdContract.Presenter{
    private final FindPayPasswdContract.View mView;

    public FindPayPasswdPresenter(@NonNull FindPayPasswdContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void updatePayPassword(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Users/UpdatePayPassword")
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
                                    mView.setUpdatePayPasswordResult(dataPackage.getBody());
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
    public void sendCode(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com/api/Users/SendCode")
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
                                    mView.setSendCodeResult(dataPackage.getBody());
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

//    @Override
//    public void checkIsReg(String mobile) {
//        try {
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.User")
//                    .addParams("_Method", "getUserReg")
//                    .addParams("deviceid", Constants.serialNumber)
//                    .addParams("accountMobile", GSONUtils.toJson(mobile))
//                    .build()//
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Logger.e(e, "something happend");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                ResBoolean dataPackage = GSONUtils.fromJson(response, ResBoolean.class);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mLoginView.setCheckResult(LoginRes.LOGIN);
//                                } else {
//                                    mLoginView.setCheckResult(LoginRes.REGISTER);
////                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void login(String mobile,String password) {
//        try {
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.User")
//                    .addParams("_Method", "userLogin")
//                    .addParams("deviceid", Constants.serialNumber)
//                    .addParams("accountMobile", GSONUtils.toJson(mobile))
//                    .addParams("password", GSONUtils.toJson(password))
//                    .build()//
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Logger.e(e, "something happend");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                TypeToken<Message<MsgUserInfo>> token = new TypeToken<Message<MsgUserInfo>>() {
//                                };
//                                Message<MsgUserInfo> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mLoginView.setLoginResult(dataPackage.getBody());
//                                } else {
////                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void register(String mobile,String password) {
//        try {
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.User")
//                    .addParams("_Method", "userRegisterLogin")
//                    .addParams("deviceid", Constants.serialNumber)
//                    .addParams("accountMobile", GSONUtils.toJson(mobile))
//                    .addParams("password", GSONUtils.toJson(password))
//                    .build()//
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Logger.e(e, "something happend");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                TypeToken<Message<MsgUserInfo>> token = new TypeToken<Message<MsgUserInfo>>() {
//                                };
//                                Message<MsgUserInfo> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mLoginView.setLoginResult(dataPackage.getBody());
//                                } else {
////                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Override
    public void start() {

    }
}
