package com.clown.wyxc.x_shopmallgoodsdetail.goodswebview;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailPresenter_WebView extends BasePresenter implements GoodsDetailContract_WebView.Presenter {
    private final GoodsDetailContract_WebView.View mView;

    public GoodsDetailPresenter_WebView(@NonNull GoodsDetailContract_WebView.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsDetailInfo(String verify, int userId, int goodsId) {
        try {
//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageGoods")
//                    .addParams("_Method", "getGoodInfo")
//                    .addParams("deviceId", Constants.serialNumber)
//                    .addParams("verify", GSONUtils.toJson(verify))
//                    .addParams("userId", GSONUtils.toJson(userId))
//                    .addParams("goodsId", GSONUtils.toJson(goodsId))
//                    .build()//
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(okhttp3.Call call, Exception e) {
//                            Logger.e(e, "something happend");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                TypeToken<Message<MsgGoodsInfo>> typeToken = new TypeToken<Message<MsgGoodsInfo>>() {
//                                };
//                                Message<MsgGoodsInfo> msg = GSONUtils.fromJson(response, typeToken);
//                                if (msg.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.setWebsite(msg.getBody().getGoodsDetails());
//                                } else {
//                                    mView.showError(msg.getCustomCode(), msg.getCustomMessage());
//                                    Logger.e(TAG, msg.getCustomMessage());
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
