package com.clown.wyxc.x_welcome;

import android.os.Bundle;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

/**
 * Created by eric_qiantw on 16/5/2.
 */
public class WelcomActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome_act);

        try {
            WelcomeFragment welcomeFragment = (WelcomeFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (welcomeFragment == null) {
                welcomeFragment = WelcomeFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        welcomeFragment, R.id.contentFrame);
            }

            // Create the presenter
            new WelcomePresenter(welcomeFragment);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
