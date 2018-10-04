package com.clown.wyxc.x_changeheader;

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
public class ChangeHeaderActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    public static String TYPE = "type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);
        ButterKnife.bind(this);

        try {
            Bundle data = new Bundle();
            data.putInt(TYPE,getIntent().getIntExtra(TYPE,1));
            ChangeHeaderFragment changeheaderFragment = (ChangeHeaderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (changeheaderFragment == null) {
                changeheaderFragment = ChangeHeaderFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        changeheaderFragment, R.id.contentFrame);
            }

            changeheaderFragment.setArguments(data);
            // Create the presenter
            new ChangeHeaderPresenter(changeheaderFragment);

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