package com.clown.wyxc.x_shopmallgoodsdetail.setmeal;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailPresenter_SetMeal extends BasePresenter implements GoodsDetailContract_SetMeal.Presenter {
    private final GoodsDetailContract_SetMeal.View mView;

    public GoodsDetailPresenter_SetMeal(@NonNull GoodsDetailContract_SetMeal.View loginView){
        mView = checkNotNull(loginView, "mView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getGoodsPackage(String verify, int userId, int goodsId) {
        try {

//            Map<String, String> map = new HashMap<>();
//
//            map.put("_Interface", "BeautifulCause.USL.Interface.PageGoods");
//            map.put("_Method", "getGoodsPackage");
//            map.put("deviceId", Constants.serialNumber);
//            map.put("goodsId", GSONUtils.toJson(goodsId));
//
//            if (verify != null && !"".equals(verify)) {
//                map.put("verify", GSONUtils.toJson(verify));
//            }
//
//            if (userId != -1) {
//                map.put("userId", GSONUtils.toJson(userId));
//            }
//
//            OkHttpUtils
//                    .get()
//                    .url(Constants.HTTP_IP)
//                    .params(map)
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
//                                TypeToken<Message<List<MsgGoodsPackage>>> token = new TypeToken<Message<List<MsgGoodsPackage>>>() {
//                                };
//                                Message<List<MsgGoodsPackage>> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mView.setGoodsPackage(dataPackage.getBody());
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
