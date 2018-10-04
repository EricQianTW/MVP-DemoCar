package com.clown.wyxc.ali;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.scheme.SchemeUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.x_parambean.AliPayQuery;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/5/2.
 */
public class AliPayActivity extends BaseAppCompatActivity implements AliPayContract.View{
    private String out_trade_no;
    private String total_fee;
    private String subject;
    private String notifyurl_type;
    private String body;

    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);

    private AliPayContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        out_trade_no = intent.getStringExtra("out_trade_no");
        subject = intent.getStringExtra("subject");
        body = intent.getStringExtra("body");
        total_fee = intent.getStringExtra("total_fee");
        notifyurl_type = intent.getStringExtra("notifyurl_type");

        mPresenter = new AliPayPresenter(this);
        mPresenter.aliPayApp(AliPayActivity.this, GSONUtils.paramToJson(new AliPayQuery(subject,out_trade_no,body,new BigDecimal(total_fee),user.getId(),notifyurl_type)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AliPayContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showMessage(String message) {
        try {
            SchemeUtil.startSchemeIntentNewTaskWithFinish(getApplicationContext(),message);
        } catch (Exception e) {
            Logger.e(e,TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setAliPayAppResult() {
        finish();
    }
}
