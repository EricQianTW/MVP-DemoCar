package com.clown.wyxc.x_mine.qr_code;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.base.Message;
import com.clown.wyxc.bean.MsgUserInfo;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MineQrcodePresenter extends BasePresenter implements MineQrCodeContract.Presenter{
    private final MineQrCodeContract.View mView;

    public MineQrcodePresenter(@NonNull MineQrCodeContract.View passWordView){
        mView = checkNotNull(passWordView, "passWordView be null!");

       mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getPhone(String verify,int userId) {
        try {
            OkHttpUtils
                    .get()
                    .url(Constants.HTTP_IP)
                    .addParams("_Interface", "BeautifulCause.USL.Interface.User")
                    .addParams("_Method", "getMsgUserShaerInfo")
                    .addParams("deviceId", Constants.serialNumber)
                    .addParams("verify", GSONUtils.toJson(verify))
                    .addParams("userId", GSONUtils.toJson(userId))
                    .build()//
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                com.google.gson.reflect.TypeToken<Message<MsgUserInfo>> token = new com.google.gson.reflect.TypeToken<Message<MsgUserInfo>>() {
                                };
                                Message<MsgUserInfo> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setReback(dataPackage.getBody());
                                } else {
//                                    mView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
                                    Logger.e(TAG, dataPackage.getCustomMessage());
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
