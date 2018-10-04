package com.clown.wyxc.x_updatecar;

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
public class UpdateCarActivity extends BaseAppCompatActivity {

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
            UpdateCarFragment updatecarFragment = (UpdateCarFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (updatecarFragment == null) {
                updatecarFragment = UpdateCarFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        updatecarFragment, R.id.contentFrame);
            }

            // Create the presenter
            new UpdateCarPresenter(updatecarFragment);

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