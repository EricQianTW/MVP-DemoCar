package com.clown.wyxc.x_shopmallpayorder;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class PayOrderActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    public final static String INTENTNAME_ORDERNO = "orderNo";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_act);
        ButterKnife.bind(this);

        try {
            String id = getIntent().getStringExtra(INTENTNAME_ORDERNO);
            Bundle data = new Bundle();
            data.putString(INTENTNAME_ORDERNO, id);

            PayOrderFragment payorderFragment = (PayOrderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (payorderFragment == null) {
                payorderFragment = PayOrderFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        payorderFragment, R.id.contentFrame);
            }

            payorderFragment.setArguments(data);

            // Create the presenter
            new PayOrderPresenter(payorderFragment);

            setSupportActionBar(toolbar);
            initBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}