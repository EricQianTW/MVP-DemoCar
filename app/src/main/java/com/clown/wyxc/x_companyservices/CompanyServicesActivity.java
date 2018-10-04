package com.clown.wyxc.x_companyservices;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class CompanyServicesActivity extends BaseAppCompatActivity {

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
            data.putInt(CompanyDetailActivity.INTENTNAME_COMPANYID, Integer.parseInt(getIntent().getStringExtra(CompanyDetailActivity.INTENTNAME_COMPANYID) == null ? "-1" : getIntent().getStringExtra(CompanyDetailActivity.INTENTNAME_COMPANYID)));

            CompanyServicesFragment loginFragment = (CompanyServicesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (loginFragment == null) {
                loginFragment = CompanyServicesFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        loginFragment, R.id.contentFrame);
            }

            loginFragment.setArguments(data);

            // Create the presenter
            new CompanyServicesPresenter(loginFragment);

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

