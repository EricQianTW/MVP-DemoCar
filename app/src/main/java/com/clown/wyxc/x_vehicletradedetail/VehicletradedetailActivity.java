package com.clown.wyxc.x_vehicletradedetail;

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
public class VehicletradedetailActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    public final static String INTANTNAME_VEHICLID = "vehiclid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();

            String vehiclid = getIntent().getStringExtra(INTANTNAME_VEHICLID);
            data.putString(INTANTNAME_VEHICLID,vehiclid);

            VehicletradedetailFragment loginFragment = (VehicletradedetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (loginFragment == null) {
                loginFragment = VehicletradedetailFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        loginFragment, R.id.contentFrame);
            }

            loginFragment.setArguments(data);
            // Create the presenter
            new VehicletradedetailPresenter(loginFragment);

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

