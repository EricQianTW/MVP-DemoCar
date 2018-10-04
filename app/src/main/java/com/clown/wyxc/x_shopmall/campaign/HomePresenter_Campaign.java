package com.clown.wyxc.x_shopmall.campaign;

import android.support.annotation.NonNull;

import com.clown.wyxc.base.BasePresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomePresenter_Campaign extends BasePresenter implements HomeContract_Campaign.Presenter {
    private final HomeContract_Campaign.View mBannerView;

    public HomePresenter_Campaign(@NonNull HomeContract_Campaign.View loginView){
        mBannerView = checkNotNull(loginView, "loginView be null!");

        mBannerView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getBanners(String deviceId, String verify, int userId) {
//        String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
//                "http://img2.3lian.com/2014/f2/37/d/40.jpg",
//                "http://d.3987.com/sqmy_131219/001.jpg",
//                "http://img2.3lian.com/2014/f2/37/d/39.jpg",
//                "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
//                "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
//                "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
//        };
//        return Arrays.asList(images);

//        try {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("_Interface", "BeautifulCause.USL.Interface.PageIndex");
//            map.put("_Method", "getIndexLimitActivity");
//            map.put("deviceId", Constants.serialNumber);
//            if (verify != null) {
//                map.put("verify", GSONUtils.toJson(verify));
//            }
//            if (userId != -1) {
//                map.put("userId", GSONUtils.toJson(userId));
//            }
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
//                                TypeToken<Message<List<MsgActivtiySale>>> token = new TypeToken<Message<List<MsgActivtiySale>>>() {
//                                };
//                                Message<List<MsgActivtiySale>> dataPackage = GSONUtils.fromJson(response, token);
//                                if (dataPackage.getState() == Constants.OKHTTP_RESULT_SUCESS) {
//                                    mBannerView.setGoods(dataPackage.getBody());
//                                } else {
//                                    mBannerView.showError(dataPackage.getCustomCode(), dataPackage.getCustomMessage());
//                                    Logger.e(TAG, dataPackage.getCustomMessage());
//                                }
//                            } catch (Exception e) {
//                                Logger.e(e, TAG);
//                            }
//                        }
//                    });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
