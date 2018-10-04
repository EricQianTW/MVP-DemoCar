package com.clown.wyxc.x_maintain;

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
public class MaintainActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    public static String INTENT_ARGUMENT_MYCARSID = "myCarsId";
    public static String INTENT_ARGUMENT_GOODSID = "goodsId";
    public static String INTENT_ARGUMENT_MAINTAINALL = "maintainAll";
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
            String myCarsId = getIntent().getStringExtra(INTENT_ARGUMENT_MYCARSID);
            data.putString(INTENT_ARGUMENT_MYCARSID, myCarsId);
            MaintainFragment maintainFragment = (MaintainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (maintainFragment == null) {
                maintainFragment = MaintainFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        maintainFragment, R.id.contentFrame);
            }

            maintainFragment.setArguments(data);
            // Create the presenter
            new MaintainPresenter(maintainFragment);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSupportActionBar(toolbar);
        initBack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}