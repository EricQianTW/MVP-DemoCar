package com.clown.wyxc.x_citychoose;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class CityChoosePresenter extends BasePresenter implements CityChooseContract.Presenter{
    private final CityChooseContract.View mLoginView;

    public CityChoosePresenter(@NonNull CityChooseContract.View loginView){
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
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
//                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
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
//                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
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
//                                    mLoginView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
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
