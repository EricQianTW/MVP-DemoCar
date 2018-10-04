package com.clown.wyxc.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.base.Message;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.wx.Constants;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 微信客户端回调activity示例
 */
public class WXEntryActivity extends BaseAppCompatActivity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.handleIntent(getIntent(), this);
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public void onReq(BaseReq arg0) {
//    }

    @Override
    public void onResp(BaseResp resp) {
        try {
            //T.showShort(getApplicationContext(), "ERR_OK1");
            if (resp != null) {
                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        //T.showShort(getApplicationContext(), "ERR_OK");
                        //分享成功
						returnShareSucess();
						finish();
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        //T.showShort(getApplicationContext(), "ERR_USER_CANCEL");
                        //分享取消
                        finish();
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        //T.showShort(getApplicationContext(), "ERR_AUTH_DENIED");
                        //分享拒绝
                        finish();
                        break;
                    default:
                        finish();
                }
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    /**微信主动请求我们**/
    @Override
    public void onReq(BaseReq baseResp) {
    }

	public void returnShareSucess(){
		try{
			OkHttpUtils.get()
					.url(com.clown.wyxc.constant.Constants.HTTP_IP)
					.addParams("_Interface","BeautifulCause.USL.Interface.PayInfo")
					.addParams("_Method","returnShareSucess")
					.addParams("deviceId", com.clown.wyxc.constant.Constants.serialNumber)
					.addParams("userId",GSONUtils.toJson(user.getId()))
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
							TypeToken<Message<String>> token = new TypeToken<Message<String>>(){};
							Message<String> message = GSONUtils.fromJson(response,token);
							if(message.getState() == com.clown.wyxc.constant.Constants.OKHTTP_RESULT_SUCESS){
                                 if("".equals(message.getBody())){
                                     return;
                                 }else {
                                     IntentUtils.startSchemeIntent(WXEntryActivity.this,message.getBody());
                                 }
							}

						}
					});

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}