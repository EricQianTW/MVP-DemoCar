package com.clown.wyxc.x_payorder.payorder;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.x_bean.MsgOrderPay;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class PayOrderPresenter_PayOrder extends BasePresenter implements PayOrderContract_PayOrder.Presenter {
    private final PayOrderContract_PayOrder.View mView;

    public PayOrderPresenter_PayOrder(@NonNull PayOrderContract_PayOrder.View view){
        mView = checkNotNull(view, "view be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void orderUserPay(String verify, int userid, String paypass, String orderguid, List<MsgOrderPay> li) {
        try {

//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.OrderPay")
//                    .addParams("_Method", "OrderUserPay")
//                    .addParams("deviceid", GSONUtils.toJson(Constants.serialNumber))
//                    .addParams("verify", GSONUtils.toJson(verify))
//                    .addParams("userid", GSONUtils.toJson(userid))
//                    .addParams("paypass", GSONUtils.toJson(paypass))
//                    .addParams("orderguid", GSONUtils.toJson(orderguid))
//                    .addParams("li", GSONUtils.toJson(li))
//                    .addParams("paysource", GSONUtils.toJson("APP"))
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
//                                    mView.setOrderUserPayRes(dataPackage.getBody());
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
