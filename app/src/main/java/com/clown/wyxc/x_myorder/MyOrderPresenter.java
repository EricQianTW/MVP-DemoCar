package com.clown.wyxc.x_myorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
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
public class MyOrderPresenter extends BasePresenter implements MyOrderContract.Presenter {
    private final MyOrderContract.View mView;

    public MyOrderPresenter(@NonNull MyOrderContract.View loginView) {
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getOrderListByOrderState(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Order/GetOrderListByOrderState")
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
                                TypeToken<Message<List<OrderInfoDetaliResult>>> token = new TypeToken<Message<List<OrderInfoDetaliResult>>>() {
                                };
                                Message<List<OrderInfoDetaliResult>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetOrderListByOrderStateResult(dataPackage.getBody());
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
//    public void getOrderInfoByUserId(String verify, int userId, int orderState, int currentPage, int pageSize) {
////        try {
////            GetBuilder builder = OkHttpUtils
////                    .get()
////                    .url(Constants.HTTP_IP)
////                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageOrder")
////                    .addParams("_Method", "getOrderInfoByUserId")
////                    .addParams("deviceId", Constants.serialNumber)
////                    .addParams("verify", GSONUtils.toJson(verify))
////                    .addParams("userId", GSONUtils.toJson(userId))
////                    .addParams("currentPage", GSONUtils.toJson(currentPage));
////            if (orderState != -1) {
////                builder = builder.addParams("orderState", GSONUtils.toJson(orderState));
////            }
////
////            if (pageSize != -1) {
////                builder = builder.addParams("pageSize", GSONUtils.toJson(pageSize));
////            }
////
////            builder.build()//
////                    .execute(new Callback<String>() {
////                        @Override
////                        public String parseNetworkResponse(Response response) throws Exception {
////                            return response.body().string();
////                        }
////
////                        @Override
////                        public void onError(Call call, Exception e) {
////                            Logger.e(e, "something happend");
////                        }
////
////                        @Override
////                        public void onResponse(String response) {
////                            try {
////                                TypeToken<Message<Page<MsgOrderInfoDetail>>> token = new TypeToken<Message<Page<MsgOrderInfoDetail>>>() {
////                                };
////                                Message<Page<MsgOrderInfoDetail>> dataPackage = GSONUtils.fromJson(response, token);
////                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
////                                    mView.getOrderInfoByUserId(dataPackage.getBody().getList());
////                                } else {
////                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
////                                    Logger.e(TAG, dataPackage.getCustomMessage());
////                                }
////                            } catch (Exception e) {
////                                Logger.e(e, TAG);
////                            }
////                        }
////                    });
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void cancelOrder(String verify, int userId, List<Integer> orderInfoIdList, final int position) {
////        try {
////            OkHttpUtils
////                    .get()
////                    .url(Constants.HTTP_IP)
////                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageOrder")
////                    .addParams("_Method", "cancelOrder")
////                    .addParams("deviceId", Constants.serialNumber)
////                    .addParams("verify", GSONUtils.toJson(verify))
////                    .addParams("userId", GSONUtils.toJson(userId))
////                    .addParams("orderInfoIdList", GSONUtils.toJson(orderInfoIdList))
////                    .build()//
////                    .execute(new Callback<String>() {
////                        @Override
////                        public String parseNetworkResponse(Response response) throws Exception {
////                            return response.body().string();
////                        }
////
////                        @Override
////                        public void onError(Call call, Exception e) {
////                            Logger.e(e, "something happend");
////                        }
////
////                        @Override
////                        public void onResponse(String response) {
////                            try {
////                                ResBoolean dataPackage = GSONUtils.fromJson(response, ResBoolean.class);
////                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
////                                    mView.setCancelOrder(dataPackage.getBody(), position);
////                                } else {
////                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
////                                    Logger.e(TAG, dataPackage.getCustomMessage());
////                                }
////                            } catch (Exception e) {
////                                Logger.e(e, TAG);
////                            }
////                        }
////                    });
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void repayOrderById(String verify, int userId, List<Integer> orderInfoIdList) {
////        try {
////            OkHttpUtils
////                    .get()
////                    .url(Constants.HTTP_IP)
////                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageOrder")
////                    .addParams("_Method", "repayOrderById")
////                    .addParams("deviceId", Constants.serialNumber)
////                    .addParams("verify", GSONUtils.toJson(verify))
////                    .addParams("userId", GSONUtils.toJson(userId))
////                    .addParams("orderInfoIdList", GSONUtils.toJson(orderInfoIdList))
////                    .build()//
////                    .execute(new Callback<String>() {
////                        @Override
////                        public String parseNetworkResponse(Response response) throws Exception {
////                            return response.body().string();
////                        }
////
////                        @Override
////                        public void onError(Call call, Exception e) {
////                            Logger.e(e, "something happend");
////                        }
////
////                        @Override
////                        public void onResponse(String response) {
////                            try {
////                                ResString dataPackage = GSONUtils.fromJson(response, ResString.class);
////                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
////                                    mView.setRepayOrderById(dataPackage.getBody());
////                                } else {
////                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
////                                    Logger.e(TAG, dataPackage.getCustomMessage());
////                                }
////                            } catch (Exception e) {
////                                Logger.e(e, TAG);
////                            }
////                        }
////                    });
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }
//
//    @Override
//    public void receiptOrder(String verify, int userId, List<Integer> orderInfoIdList, final int poistion) {
////        try {
////
////            OkHttpUtils
////                    .get()
////                    .url(Constants.HTTP_IP)
////                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageOrder")
////                    .addParams("_Method", "receiptOrder")
////                    .addParams("deviceId", Constants.serialNumber)
////                    .addParams("verify", GSONUtils.toJson(verify))
////                    .addParams("userId", GSONUtils.toJson(userId))
////                    .addParams("orderInfoIdList", GSONUtils.toJson(orderInfoIdList))
////                    .build()//
////                    .execute(new Callback<String>() {
////                        @Override
////                        public String parseNetworkResponse(Response response) throws Exception {
////                            return response.body().string();
////                        }
////
////                        @Override
////                        public void onError(Call call, Exception e) {
////                            Logger.e(e, "something happend");
////                        }
////
////                        @Override
////                        public void onResponse(String response) {
////                            try {
////                                ResBoolean resBoolean = GSONUtils.fromJson(response, ResBoolean.class);
////                                if (resBoolean.getState() == Constants.OKHTTP_RESULT_SUCESS) {
////                                    mView.receiptOrderResult(resBoolean.getBody(),poistion);
////                                } else {
////                                    mView.showError(resBoolean.getCustomCode(),resBoolean.getCustomMessage());
////                                }
////
////                            } catch (Exception e) {
////                                Logger.e(e, TAG);
////                            }
////                        }
////                    });
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//    }
}
