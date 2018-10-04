package com.clown.wyxc.x_login;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseAppCompatActivity  {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        ButterKnife.bind(this);

        try {
            LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (loginFragment == null) {
                loginFragment = LoginFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        loginFragment, R.id.contentFrame);
            }

            // Create the presenter
            new LoginPresenter(loginFragment);

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

