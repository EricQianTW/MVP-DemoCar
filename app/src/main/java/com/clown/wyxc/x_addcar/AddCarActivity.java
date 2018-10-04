package com.clown.wyxc.x_addcar;

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
public class AddCarActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    // Parameter名
    public final static String INTENTNAME_PARENTID = "parentId";
    public final static String INTENTNAME_CARID = "id";
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
            data.putInt(INTENTNAME_CARID, Integer.parseInt(getIntent().getStringExtra(INTENTNAME_CARID) == null ? "0" : getIntent().getStringExtra(INTENTNAME_CARID)));

            AddCarFragment addcarFragment = (AddCarFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (addcarFragment == null) {
                addcarFragment = AddCarFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        addcarFragment, R.id.contentFrame);
            }

            addcarFragment.setArguments(data);

            // Create the presenter
            new AddCarPresenter(addcarFragment);

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