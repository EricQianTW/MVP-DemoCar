package com.clown.wyxc.x_yearwithcar;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_carwithyear.CarWithYearActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class YearWithCarActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    // Parameter名
    public final static String INTENTNAME_CARINFO = "carInfo";
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
            data.putInt(CarWithYearActivity.INTENTNAME_PARENTID, Integer.parseInt(getIntent().getStringExtra(CarWithYearActivity.INTENTNAME_PARENTID) == null ? "-1" : getIntent().getStringExtra(CarWithYearActivity.INTENTNAME_PARENTID)));

            data.putString(CarWithYearActivity.INTENTNAME_YEARTYPE, getIntent().getStringExtra(CarWithYearActivity.INTENTNAME_YEARTYPE) == null ? "" : getIntent().getStringExtra(CarWithYearActivity.INTENTNAME_YEARTYPE));

            YearWithCarFragment yearwithcarFragment = (YearWithCarFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (yearwithcarFragment == null) {
                yearwithcarFragment = YearWithCarFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        yearwithcarFragment, R.id.contentFrame);
            }

            yearwithcarFragment.setArguments(data);
            // Create the presenter
            new YearWithCarPresenter(yearwithcarFragment);

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