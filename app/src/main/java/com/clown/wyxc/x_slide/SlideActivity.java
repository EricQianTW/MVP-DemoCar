package com.clown.wyxc.x_slide;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_welcome.WelcomActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class SlideActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_notitle_act);
        ButterKnife.bind(this);

        try {
            SlideFragment slideFragment = (SlideFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (slideFragment == null) {
                slideFragment = SlideFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        slideFragment, R.id.contentFrame);
            }

            // Create the presenter
            new SlidePresenter(slideFragment);

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
        IntentUtils.startActivityFinish(getActivity(), WelcomActivity.class);
    }
}