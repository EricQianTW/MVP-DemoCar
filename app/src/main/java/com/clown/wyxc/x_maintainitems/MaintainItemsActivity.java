package com.clown.wyxc.x_maintainitems;

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
public class MaintainItemsActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    // Parameter名
    public final static String INTENTNAME_MAINTAINITEMID = "maintainItemId";
    public final static String INTENTNAME_MAINTAINITEMGOODSID = "maintainItemGoodsId";
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
            data.putString(INTENTNAME_MAINTAINITEMID, getIntent().getStringExtra(INTENTNAME_MAINTAINITEMID));
            data.putString(INTENTNAME_MAINTAINITEMGOODSID, getIntent().getStringExtra(INTENTNAME_MAINTAINITEMGOODSID));

            MaintainItemsFragment maintainitemsFragment = (MaintainItemsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (maintainitemsFragment == null) {
                maintainitemsFragment = MaintainItemsFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        maintainitemsFragment, R.id.contentFrame);
            }

            maintainitemsFragment.setArguments(data);

            // Create the presenter
            new MaintainItemsPresenter(maintainitemsFragment);

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