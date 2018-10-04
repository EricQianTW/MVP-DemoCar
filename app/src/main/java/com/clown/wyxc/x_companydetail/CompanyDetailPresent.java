package com.clown.wyxc.x_companydetail;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.base.Message;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by cc on 2016/8/4.
 */
public class CompanyDetailPresent extends BasePresenter implements CompanyDetailContract.Present {
    private CompanyDetailContract.View mView;

    public CompanyDetailPresent(CompanyDetailContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    public void modifyGoodsCollection(String verf,int userid,int goodsId){
        try {
            Map<String, String> map = new HashMap<>();

            map.put("_Interface", "BeautifulCause.USL.Interface.PageCollection");
            map.put("_Method", "modifyGoodsCollection");
            map.put("deviceId", Constants.serialNumber);
            if (verf != null && !"".equals(verf)) {
                map.put("verify", GSONUtils.toJson(verf));
            }

            if (userid != -1) {
                map.put("userId", GSONUtils.toJson(userid));
            }
            map.put("goodsId", GSONUtils.toJson(goodsId));

            OkHttpUtils.get()
                    .url(Constants.HTTP_IP)
                    .params(map)
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Logger.e(e,TAG);
                        }

                        @Override
                        public void onResponse(String response) {
                            TypeToken<Message<Integer>> token = new TypeToken<Message<Integer>>(){};
                            Message<Integer> data = GSONUtils.fromJson(response,token);
                            if(data.getState() == Constants.OKHTTP_RESULT_SUCESS){
                                mView.modifyGoodsCollectionResult(data.getBody());
                            }else {
//                                mView.showError(data.getCustomCode(),data.getCustomMessage());
                            }

                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
