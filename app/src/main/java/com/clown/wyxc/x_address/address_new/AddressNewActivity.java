package com.clown.wyxc.x_address.address_new;

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
public class AddressNewActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    public final static String INTENTNAME_NEWRECEIVERINFOID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_act);
        ButterKnife.bind(this);

        try {
            String id = getIntent().getStringExtra(INTENTNAME_NEWRECEIVERINFOID);
            Bundle data = new Bundle();
            data.putString(INTENTNAME_NEWRECEIVERINFOID, id);

            AddressNewFragment addressNewFragment = (AddressNewFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (addressNewFragment == null) {
                addressNewFragment = AddressNewFragment.newInstance();
            }

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addressNewFragment, R.id.contentFrame);

            addressNewFragment.setArguments(data);

            // Create the presenter
            new AddressNewPresenter(addressNewFragment);

            setSupportActionBar(toolbar);
            initBack();
        }catch (Exception e){
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

