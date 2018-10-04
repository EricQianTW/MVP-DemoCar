package com.clown.wyxc.x_carwithyear;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_addcar.AddCarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class CarWithYearActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    // Parameter名
    public final static String INTENTNAME_PARENTID = "parentId";
    public final static String INTENTNAME_YEARTYPE = "yearType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();
            data.putInt(AddCarActivity.INTENTNAME_PARENTID, Integer.parseInt(getIntent().getStringExtra(AddCarActivity.INTENTNAME_PARENTID) == null ? "-1" : getIntent().getStringExtra(AddCarActivity.INTENTNAME_PARENTID)));

            CarWithYearFragment carwithyearFragment = (CarWithYearFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (carwithyearFragment == null) {
                carwithyearFragment = CarWithYearFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        carwithyearFragment, R.id.contentFrame);
            }

            carwithyearFragment.setArguments(data);
            // Create the presenter
            new CarWithYearPresenter(carwithyearFragment);

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