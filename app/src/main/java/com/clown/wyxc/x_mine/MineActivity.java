package com.clown.wyxc.x_mine;

import android.os.Bundle;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

/**
 * A login screen that offers login via email/password.
 */
public class MineActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act);

        try {

            MineFragment mineFragment = (MineFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (mineFragment == null) {
                mineFragment = MineFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        mineFragment, R.id.contentFrame);
            }

            // Create the presenter
            new MinePresenter(mineFragment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

