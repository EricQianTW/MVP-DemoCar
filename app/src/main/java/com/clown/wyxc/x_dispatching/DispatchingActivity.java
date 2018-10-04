package com.clown.wyxc.x_dispatching;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_address.address_list.AddressListFragment;
import com.clown.wyxc.x_address.address_list.AddressListPresenter;
import com.clown.wyxc.x_dispatching.store.DispatchingStoreFragment;
import com.clown.wyxc.x_dispatching.store.DispatchingStorePresenter;
import com.coorchice.library.SuperTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class DispatchingActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_daodian)
    SuperTextView tvDaodian;
    @Bind(R.id.tv_daojia)
    SuperTextView tvDaojia;

    private DispatchingStoreFragment dispatchingHomeFragment;
    //    private DispatchingStoreFragment dispatchingStoreFragment;
    private AddressListFragment dispatchingStoreFragment;
    public final static String INTENTNAME_ORDERNO = "orderNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act_custom);
        ButterKnife.bind(this);

        try {
            String id = getIntent().getStringExtra(INTENTNAME_ORDERNO);
            Bundle data = new Bundle();
            data.putString(INTENTNAME_ORDERNO, id);

            initView();
            initAction();

            dispatchingHomeFragment = (DispatchingStoreFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (dispatchingHomeFragment == null) {
                dispatchingHomeFragment = DispatchingStoreFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), dispatchingHomeFragment, R.id.contentFrame);
            }

            dispatchingHomeFragment.setArguments(data);

            new DispatchingStorePresenter(dispatchingHomeFragment);

            setSupportActionBar(toolbar);
            initBack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
    }

    private void initAction() {
        tvDaodian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDaodian.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_F9454D));
                tvDaojia.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                tvDaodian.setSolid(ContextCompat.getColor(getApplicationContext(), R.color.white));
                tvDaojia.setSolid(ContextCompat.getColor(getApplicationContext(), R.color.gray_theme));

                if (dispatchingHomeFragment == null) {
                    dispatchingHomeFragment = DispatchingStoreFragment.newInstance();
                }

                ActivityUtils.replaceFragmentFromActivity(getSupportFragmentManager(), R.id.contentFrame,
                        dispatchingHomeFragment);

                new DispatchingStorePresenter(dispatchingHomeFragment);
            }
        });
        tvDaojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDaojia.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_F9454D));
                tvDaodian.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                tvDaodian.setSolid(ContextCompat.getColor(getApplicationContext(), R.color.gray_theme));
                tvDaojia.setSolid(ContextCompat.getColor(getApplicationContext(), R.color.white));
                if (dispatchingStoreFragment == null) {
                    dispatchingStoreFragment = AddressListFragment.newInstance();
                }

                ActivityUtils.replaceFragmentFromActivity(getSupportFragmentManager(), R.id.contentFrame,
                        dispatchingStoreFragment);

                new AddressListPresenter(dispatchingStoreFragment);
            }
        });
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