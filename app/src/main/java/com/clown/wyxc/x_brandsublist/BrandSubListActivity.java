package com.clown.wyxc.x_brandsublist;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_brandlist.BrandListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class BrandSubListActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    // Parameter名
    public final static String INTENTNAME_CARINFOCX = "carInfoCX";
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
            data.putInt(BrandListActivity.INTENTNAME_CARID, Integer.parseInt(getIntent().getStringExtra(BrandListActivity.INTENTNAME_CARID) == null ? "-1" : getIntent().getStringExtra(BrandListActivity.INTENTNAME_CARID)));

            BrandSubListFragment brandsublistFragment = (BrandSubListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (brandsublistFragment == null) {
                brandsublistFragment = BrandSubListFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        brandsublistFragment, R.id.contentFrame);
            }

            brandsublistFragment.setArguments(data);

            // Create the presenter
            new BrandSubListPresenter(brandsublistFragment);

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