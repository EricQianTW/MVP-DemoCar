package com.clown.wyxc.x_myorder;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 * 废弃
 */
public class MyOrderActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private int orderstate;
    public static final String INTENT_ORDERSTATE = "orderstate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder_act);
        ButterKnife.bind(this);
        try {

            getOrderState();
            initFragment();
        }catch (Exception e){
            e.printStackTrace();
        }
        initView();
    }

    private void initFragment() throws Exception {
        MyOrderFragment myorderFragment = (MyOrderFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (myorderFragment == null) {
            myorderFragment = MyOrderFragment.newInstance();
        }

        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_ORDERSTATE,orderstate);
        myorderFragment.setArguments(bundle);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    myorderFragment, R.id.contentFrame);


        // Create the presenter
        new MyOrderPresenter(myorderFragment);
    }

    private void initView() {
        setSupportActionBar(toolbar);
        initBack();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getOrderState(){
        orderstate = getIntent().getIntExtra(INTENT_ORDERSTATE,0);
    }
}

