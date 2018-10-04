package com.clown.wyxc.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.clown.wyxc.R;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.wx.Constants;
import com.clown.wyxc.x_settleorder.SettleOrderActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by cc on 2016/9/30.
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    IntentUtils.startActivity(getApplicationContext(), SettleOrderActivity.class);
                    //分享成功
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    T.showShort(getApplicationContext(),"支付取消");
                    finish();
                    //分享取消
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                    T.showShort(getApplicationContext(),"支付失败 ");
                    finish();
                    //分享拒绝
                    break;
            }
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
//        api.handleIntent(getIntent(), this);
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onReq(BaseReq arg0) { }
//
//    @Override
//    public void onResp(BaseResp resp) {
//        switch (resp.errCode) {
//            case BaseResp.ErrCode.ERR_OK:
//                try {
//                    IntentUtils.startActivity(getApplicationContext(), SettleOrderActivity.class);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                //分享成功
//                break;
//            case BaseResp.ErrCode.ERR_USER_CANCEL:
//                //分享取消
//                break;
//            case BaseResp.ErrCode.ERR_AUTH_DENIED:
//                //分享拒绝
//                break;
//        }
//        this.finish();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent){
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent,this);
//    }
}
