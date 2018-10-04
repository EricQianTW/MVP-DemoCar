package com.clown.wyxc.x_settleorder;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_common.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class SettleOrderActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    public static String ORDER_TYPE = "ordertype";
    public static String ORDER_STATE = "orderstate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();
            data.putInt(ORDER_TYPE, Integer.parseInt(getIntent().getStringExtra(ORDER_TYPE) == null ? "-1" : getIntent().getStringExtra(ORDER_TYPE)));
            data.putInt(ORDER_STATE, Integer.parseInt(getIntent().getStringExtra(ORDER_STATE) == null ? "-1" : getIntent().getStringExtra(ORDER_STATE)));

            int orderType = Integer.parseInt(getIntent().getStringExtra(ORDER_TYPE) == null ? "-1" : getIntent().getStringExtra(ORDER_TYPE));

            if (Constants.ORDER_TYPE_GOODS == orderType) {
                toolbar.setTitle("商品订单");
            } else if (Constants.ORDER_TYPE_SERVICE == orderType) {
                toolbar.setTitle("服务订单");
            } else if (Constants.ORDER_TYPE_MAINTAIN == orderType) {
                toolbar.setTitle("保养订单");
            }

            SettleOrderFragment settleorderFragment = (SettleOrderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (settleorderFragment == null) {
                settleorderFragment = SettleOrderFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        settleorderFragment, R.id.contentFrame);
            }

            settleorderFragment.setArguments(data);

            // Create the presenter
            new SettleOrderPresenter(settleorderFragment);

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