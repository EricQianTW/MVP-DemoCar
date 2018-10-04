package com.clown.wyxc.x_comment;

import android.util.Log;

import com.clown.wyxc.base.BasePresenter;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.GetCommOrderItemInfoResult;
import com.clown.wyxc.x_bean.ResString;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by CZP on 2016/7/11.
 */
public class CommentPresenter extends BasePresenter implements CommentContract.Presenter{
    private final CommentContract.View mView;

    public CommentPresenter(CommentContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

//    public void addGoodsComment(String verf, int userid, List<MsgAfferentComm> comms, int orderid){//添加评论
//        try{
//            OkHttpUtils.post()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface","BeautifulCause.USL.Interface.PageComment")
//                    .addParams("_Method","addGoodsComment")
//                    .addParams("deviceId", Constants.serialNumber)
//                    .addParams("verify",GSONUtils.toJson(verf))
//                    .addParams("userId",GSONUtils.toJson(userid))
//                    .addParams("msgAfferentCommList",GSONUtils.toJson(comms))
//                    .addParams("orderId",GSONUtils.toJson(orderid))
//                    .build()
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Logger.e(e,"wrong");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            ResBoolean result = GSONUtils.fromJson(response,ResBoolean.class);
//                            if(result.getState() == Constants.OKHTTP_RESULT_SUCESS){
//                                mView.getcommentResult(result.getBody());
//                            }else{
//                                mView.showError(result.getCustomCode(),result.getCustomMessage());
//                            }
//
//                        }
//                    });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public void uploadImage(String verf, int userid
//            , int[] imagelist){
//        try{
//            OkHttpUtils.post()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface","BeautifulCause.USL.Interface.PageFileService")
//                    .addParams("_Method","uploadCommonImage")
//                    .addParams("deviceId",Constants.serialNumber)
//                    .addParams("verify",GSONUtils.toJson(verf))
//                    .addParams("userid",GSONUtils.toJson(userid))
//                    .addParams("Image",GSONUtils.toJson(imagelist))
//                    .build()
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Logger.e(e,"wrong");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            TypeToken<Message<MsgImage>> typeToken = new TypeToken<Message<MsgImage>>(){};
//                            Message<MsgImage> data = GSONUtils.fromJson(response,typeToken);
//                            if(data.getState() == Constants.OKHTTP_RESULT_SUCESS){
//                                mView.getAddGoodsCommentResult(data.getBody());
//                            }else{
//                                mView.showError(data.getCustomCode(),data.getCustomMessage());
//                            }
//                        }
//                    });
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public void getCommOrderItemInfo(String verf, int useid, int orderid) {
//        try {
//            OkHttpUtils.get()
//                    .url(Constants.HTTP_IP)
//                    .addParams("_Interface", "BeautifulCause.USL.Interface.PageComment")
//                    .addParams("_Method", "getCommOrderItemInfo")
//                    .addParams("deviceId", Constants.serialNumber)
//                    .addParams("verify", GSONUtils.toJson(verf))
//                    .addParams("userId", GSONUtils.toJson(useid))
//                    .addParams("orderInfoId", GSONUtils.toJson(orderid))
//                    .build()
//                    .execute(new Callback<String>() {
//                        @Override
//                        public String parseNetworkResponse(Response response) throws Exception {
//                            return response.body().string();
//                        }
//
//                        @Override
//                        public void onError(Call call, Exception e) {
//                            Log.d("tag", "something wrong");
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            TypeToken<Message<List<MsgOrderItemDetail>>> typeToken = new TypeToken<Message<List<MsgOrderItemDetail>>>() {
//                            };
//                            Message<List<MsgOrderItemDetail>> data = GSONUtils.fromJson(response, typeToken);
//                            if (data.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                mView.getCommOrderItemInfoResult(data.getBody());
//                            } else {
//                                mView.showError(data.getCustomCode(), data.getCustomMessage());
//                            }
//                        }
//                    });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @Override
    public void getCommOrderItemInfo(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Evaluate/GetCommOrderItemInfo")
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
                                TypeToken<Message<GetCommOrderItemInfoResult>> token = new TypeToken<Message<GetCommOrderItemInfoResult>>() {
                                };
                                Message<GetCommOrderItemInfoResult> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setGetCommOrderItemInfoResult(dataPackage.getBody());
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

    @Override
    public void addEvaluateByOrderId(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Evaluate/AddEvaluateByOrderId")
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
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setAddEvaluateByOrderIdResult(dataPackage.getBody());
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

    @Override
    public void post(String param){
        try {
            File file = new File(param);
            if (!file.exists())
            {
                mView.showError(0, "文件不存在，请修改文件路径");
                return;
            }
            OkHttpUtils.post()
                    .addFile("mFile", "test1.jpg", file)
                    .url("http://api.ixiuc.com/api/Upload/Post")
                    .build()//
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
                        public void inProgress(float progress)
                        {
                            Log.e(TAG,String.valueOf(progress));
                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG,response);
                            try {
                                TypeToken<Message<List<String>>> token = new TypeToken<Message<List<String>>>() {
                                };
                                Message<List<String>> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    mView.setPostResult(dataPackage.getBody());
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
