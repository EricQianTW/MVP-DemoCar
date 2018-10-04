package com.clown.wyxc.ali;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.utils.GSONUtils;
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
public class AliPayPresenter extends BasePresenter implements AliPayContract.Presenter{
    private final AliPayContract.View mLoginView;

    private static final int SDK_PAY_FLAG = 1;

    public AliPayPresenter(@NonNull AliPayContract.View loginView){
        mLoginView = checkNotNull(loginView, "loginView be null!");

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void aliPayApp(final Activity mContext,String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Pay/AliPayApp")
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
                                if (dataPackage.getStatusCode() == 1) {
//                                    MsgReturnZhifu zhifuInfo = dataPackage.getBody();
                                    // 完整的符合支付宝参数规范的订单信息
                                    final String payInfo = dataPackage.getBody();

                                    Runnable payRunnable = new Runnable() {

                                        @Override
                                        public void run() {
                                            // 构造PayTask 对象
                                            PayTask alipay = new PayTask(mContext);
                                            // 调用支付接口，获取支付结果
                                            String result = alipay.pay(payInfo,true);

                                            android.os.Message msg = new android.os.Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    };

                                    // 必须异步调用
                                    Thread payThread = new Thread(payRunnable);
                                    payThread.start();
                                } else {
                                    mLoginView.showError(dataPackage.getCustomCode(),dataPackage.getInfo());
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
//    public void aliPay(final Activity mContext, String subject, String out_trade_no, String body, String total_fee, String notifyurl_type,int userid) {
//        OkHttpUtils
//                .post()
//                .url(com.clown.wyxc.constant.Constants.HTTP_IP)
//                .addParams("_Interface", "BeautifulCause.USL.Interface.PayInfo")
//                .addParams("_Method", "zhifbPay")
//                .addParams("subject", GSONUtils.toJson(subject))
//                .addParams("out_trade_no", GSONUtils.toJson(out_trade_no))
//                .addParams("body", GSONUtils.toJson(body))
//                .addParams("total_fee", GSONUtils.toJson(total_fee))
//                .addParams("userid", GSONUtils.toJson(userid))
//                .addParams("notifyurl_type", GSONUtils.toJson(notifyurl_type))
//                .build()//
//                .execute(new Callback<String>() {
//                    @Override
//                    public String parseNetworkResponse(Response response) throws Exception {
//                        return response.body().string();
//                    }
//
//                    @Override
//                    public void onError(okhttp3.Call call, Exception e) {
//                        Logger.e(e, "something happend");
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            TypeToken<Message<MsgReturnZhifu>> token = new TypeToken<Message<MsgReturnZhifu>>() {
//                            };
//                            Message<MsgReturnZhifu> dataPackage = GSONUtils.fromJson(response, token);
//                            if (dataPackage.getState() == 1) {
//                                MsgReturnZhifu zhifuInfo = dataPackage.getBody();
//                                // 完整的符合支付宝参数规范的订单信息
//                                final String payInfo = zhifuInfo.getOrderString() + "&sign=\"" + URLEncoder.encode(zhifuInfo.getSign(), "UTF-8") + "\"&"
//                                        + zhifuInfo.getSign_Type();
//
//                                Runnable payRunnable = new Runnable() {
//
//                                    @Override
//                                    public void run() {
//                                        // 构造PayTask 对象
//                                        PayTask alipay = new PayTask(mContext);
//                                        // 调用支付接口，获取支付结果
//                                        String result = alipay.pay(payInfo);
//
//                                        android.os.Message msg = new android.os.Message();
//                                        msg.what = SDK_PAY_FLAG;
//                                        msg.obj = result;
//                                        mHandler.sendMessage(msg);
//                                    }
//                                };
//
//                                // 必须异步调用
//                                Thread payThread = new Thread(payRunnable);
//                                payThread.start();
//                            } else {
////                                mLoginView.showError(dataPackage.getCustomCode(),dataPackage.getCustomMessage());
//                                Logger.e(TAG, dataPackage.getCustomMessage());
//                            }
//                        } catch (Exception e) {
//                            Logger.e(e,TAG);
//                        }
//                    }
//                });
//    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        mLoginView.showMessage("支付成功");
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            mLoginView.showMessage("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            mLoginView.showMessage("支付失败");
                        }
                    }
                    mLoginView.setAliPayAppResult();
                    break;
                }
                default:
                    break;
            }
        };
    };
}
