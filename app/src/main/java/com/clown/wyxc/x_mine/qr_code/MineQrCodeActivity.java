package com.clown.wyxc.x_mine.qr_code;

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

/**
 *   密码修改获取验证码页面
 */
public class MineQrCodeActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passwordfrist_act);
        ButterKnife.bind(this);

        try {

            MineQrCodeFragment PersonalFragment = (MineQrCodeFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (PersonalFragment == null) {
                PersonalFragment = MineQrCodeFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        PersonalFragment, R.id.contentFrame);
            }

            // Create the presenter
            new MineQrcodePresenter(PersonalFragment);

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

}

