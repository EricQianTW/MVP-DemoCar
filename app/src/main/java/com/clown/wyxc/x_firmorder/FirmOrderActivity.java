package com.clown.wyxc.x_firmorder;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class FirmOrderActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public final static String INTENTNAME_ORDERNO = "guid";
    public final static String INTENTNAME_ADDRESSINFO = "addressInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firmorder_act);
        ButterKnife.bind(this);

        try {
            initView();

            String id = getIntent().getStringExtra(INTENTNAME_ORDERNO);
            Bundle data = new Bundle();
            data.putString(INTENTNAME_ORDERNO,id);

            FirmOrderFragment.newInstance().setOrderNo(id);

            FirmOrderFragment mineFragment = (FirmOrderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (mineFragment == null) {
                mineFragment = FirmOrderFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        mineFragment, R.id.contentFrame);
            }

            mineFragment.setArguments(data);

            // Create the presenter
            new FirmOrderPresenter(mineFragment);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() throws Exception {
        setSupportActionBar(toolbar);
        initBack();
    }
}

