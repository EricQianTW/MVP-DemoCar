package com.clown.wyxc.x_brandlist;

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
public class BrandListActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    // Parameter名
    public final static String INTENTNAME_CARID = "carid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            BrandListFragment brandlistFragment = (BrandListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (brandlistFragment == null) {
                brandlistFragment = BrandListFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        brandlistFragment, R.id.contentFrame);
            }

            // Create the presenter
            new BrandListPresenter(brandlistFragment);

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