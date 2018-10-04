package com.clown.wyxc.x_maintainall;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_maintain.MaintainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class MaintainAllActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();
            String maintainAll = getIntent().getStringExtra(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL);
            data.putString(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL, maintainAll);

            MaintainAllFragment maintainallFragment = (MaintainAllFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (maintainallFragment == null) {
                maintainallFragment = MaintainAllFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        maintainallFragment, R.id.contentFrame);
            }

            maintainallFragment.setArguments(data);

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