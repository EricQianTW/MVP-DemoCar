package com.clown.wyxc.x_mycar;

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
public class MyCarActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    public static String INTENTNAME_SOURCE = "source";
    public static String INTENTNAME_CARID = "carId";
    public static String INTENTNAME_SOURCE_SELECT = "select";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();
            data.putString(INTENTNAME_SOURCE, getIntent().getStringExtra(INTENTNAME_SOURCE));
            MyCarFragment mycarFragment = (MyCarFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (mycarFragment == null) {
                mycarFragment = MyCarFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        mycarFragment, R.id.contentFrame);
            }

            // Create the presenter
            new MyCarPresenter(mycarFragment);

            mycarFragment.setArguments(data);

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