package com.clown.wyxc.x_mine.list;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.CenterMenuResult;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class MinePresenter_List extends BasePresenter implements MineContract_List.Presenter {
    private final MineContract_List.View mView;

    public MinePresenter_List(@NonNull MineContract_List.View loginView){
        mView = checkNotNull(loginView, "loginView be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getCarInfoPPList(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/CenterMenu/GetCarInfoPPList")
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
                                TypeToken<Message<List<CenterMenuResult>>> token = new TypeToken<Message<List<CenterMenuResult>>>() {
                                };
                                Message<List<CenterMenuResult>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetCarInfoPPListResult(dataPackage.getBody());
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

}
