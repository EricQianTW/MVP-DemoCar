package com.clown.wyxc.wx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.WeiXinPayQuery;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/5/2.
 */
public class WxPayActivity extends BaseAppCompatActivity implements WxPayContract.View{
    private String outtradeno;
    private String totalfee;
    private String body;
    private String notifyurl_type;

    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);

    private WxPayContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        outtradeno = intent.getStringExtra("out_trade_no");
        body = intent.getStringExtra("body");
        totalfee = intent.getStringExtra("total_fee");
        notifyurl_type = intent.getStringExtra("notifyurl_type");

        WxPayPresenter mPresenter = new WxPayPresenter(this);
        mPresenter.weiXinPayApp(msgApi, GSONUtils.paramToJson(new WeiXinPayQuery(body,outtradeno,totalfee,user.getId(),"127.0.0.1",notifyurl_type)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull WxPayContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setWeiXinPayAppResult() {
        finish();
    }
}
