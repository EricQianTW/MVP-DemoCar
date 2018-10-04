package com.clown.wyxc.x_message.messagedetail;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.bean.MsgPushMessage;
import com.clown.wyxc.bean.Page;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.L;
import com.clown.wyxc.x_base.Message;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by CZP on 2016/7/19.
 */
public class MessageDetailPresenter extends BasePresenter implements MessageDetailContract.Presenter {
    private final MessageDetailContract.View mView;

    public MessageDetailPresenter(MessageDetailContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    public void getMsgListInfo(String verf,int userid,int currentpage,int sendTpy){

        try{
            OkHttpUtils.post()
                    .url(Constants.HTTP_IP)
                    .addParams("_Interface","BeautifulCause.USL.Interface.SystemAbout")
                    .addParams("_Method","MsgListInfo")
                    .addParams("deviceid",Constants.serialNumber)
                    .addParams("verify", GSONUtils.toJson(verf))
                    .addParams("userId",GSONUtils.toJson(userid))
                    .addParams("SendTyp",GSONUtils.toJson(sendTpy))
                    .addParams("currentPage",GSONUtils.toJson(currentpage))
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Call call, Exception e) {

                            L.e(TAG,e.toString());
                        }

                        @Override
                        public void onResponse(String response) {
                            TypeToken<Message<Page<MsgPushMessage>>> token = new TypeToken<Message<Page<MsgPushMessage>>>(){};
                            Message<Page<MsgPushMessage>> data = GSONUtils.fromJson(response,token);

                            if(data.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS){
                                mView.setMsgListInfoResult(data.getBody().getList());
                            }else{
                                mView.showError(data.getCustomCode(),data.getInfo());
                            }
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
