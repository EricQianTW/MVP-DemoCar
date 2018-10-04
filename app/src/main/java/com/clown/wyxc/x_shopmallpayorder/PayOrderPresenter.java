package com.clown.wyxc.x_shopmallpayorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PayOrderPresenter extends BasePresenter implements PayOrderContract.Presenter{
    private final PayOrderContract.View mView;

    public PayOrderPresenter(@NonNull PayOrderContract.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getOrderInfoByUserId(int userId, String guidOrderNo) {
        try {
//            GetBuilder builder = OkHttpUtils
//                    .get()
//                    .url(Constants.NEWMATN_HTTP)
//                    .addParams("_Interface", "Fy.Mall.Api.IOrderInfo")
//                    .addParams("_Method", "GetPayList")
//                    .addParams("userId", GSONUtils.toJson(userId))
//                    .addParams("guidOrderNo", GSONUtils.toJson(guidOrderNo));
//
//            builder.build()//
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
//                                TypeToken<Message<List<MsgOrderPay>>> token = new TypeToken<Message<List<MsgOrderPay>>>() {
//                                };
//                                Message<List<MsgOrderPay>> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.getOrderInfoByUserIdResult(dataPackage.getBody());
//                                } else {
//                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void OrderPay(int userId, String orderGuid, String payPass, int payPathId) {
        try {
//            HashMap map = new HashMap();
//            map.put("_Interface", "Fy.Mall.Api.IOrderInfo");
//            map.put("_Method", "OrderPay");
//            map.put("userId", GSONUtils.toJson(userId));
//            map.put("payPass", GSONUtils.toJson(payPass));
//            map.put("payPathId", GSONUtils.toJson(payPathId));
//            map.put("orderGuid", GSONUtils.toJson(orderGuid));
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.NEWMATN_HTTP)
//                    .params(map)
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
//                                ResString dataPackage = GSONUtils.fromJson(response, ResString.class);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.OrderPayResult(dataPackage.getBody());
//                                } else {
//                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void GetOrderSumAmt(int userId, String orderGuid) {
        try {
//            HashMap map = new HashMap();
//            map.put("_Interface", "Fy.Mall.Api.IOrderInfo");
//            map.put("_Method", "GetOrderSumAmt");
//            map.put("userId", GSONUtils.toJson(userId));
//            map.put("orderGuid", GSONUtils.toJson(orderGuid));
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.NEWMATN_HTTP)
//                    .params(map)
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
//                                ResBigDecimal dataPackage = GSONUtils.fromJson(response, ResBigDecimal.class);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.GetOrderSumAmtResult(dataPackage.getBody());
//                                } else {
//                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}