package com.clown.wyxc.x_shopmallgoodsdetail.goodswuliu;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cc on 2017/5/28.
 */

public class GoodsWuliuPresenter extends BasePresenter implements GoodsWuliuContract.Presenter{

    private final GoodsWuliuContract.View mView;

    public GoodsWuliuPresenter(@NonNull GoodsWuliuContract.View loginView){
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsDetailInfo(int userid,int goodsId,String ip) {
        try {
//            OkHttpUtils
//                    .get()
//                    .url(Constants.NEWMATN_HTTP)
//                    .addParams("_Interface", "Fy.Mall.Api.IGoodsInfo")
//                    .addParams("_Method", "getGoodsExpress")
//                    .addParams("userId", GSONUtils.toJson(userid))
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
//                                TypeToken<Message<MsgInGoodsExpress>> token = new TypeToken<Message<MsgInGoodsExpress>>() {
//                                };
//                                Message<MsgInGoodsExpress> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.getGoodsDetailInfoResult(dataPackage.getBody());
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
