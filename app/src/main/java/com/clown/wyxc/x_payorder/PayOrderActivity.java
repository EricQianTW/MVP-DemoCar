package com.clown.wyxc.x_payorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class PayOrderActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public final static String INTENTNAME_ORDERNO = "guid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payorder_act);
        ButterKnife.bind(this);

        try {
            initView();

            String id = getIntent().getStringExtra(INTENTNAME_ORDERNO);
            Bundle data = new Bundle();
            data.putString(INTENTNAME_ORDERNO,id);

            PayOrderFragment payFragment = (PayOrderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (payFragment == null) {
                payFragment = PayOrderFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        payFragment, R.id.contentFrame);
            }
            payFragment.setArguments(data);
            // Create the presenter
            new PayOrderPresenter(payFragment);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() throws Exception {
        setSupportActionBar(toolbar);
//        initBack();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishActivity();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        finishActivity();
    }

    private void finishActivity() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("确认退出支付？");
        builder.setMessage("确认退出支付")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }
}

