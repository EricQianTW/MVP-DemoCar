package com.clown.wyxc.x_classify;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_search.SearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class ClassifyActivity extends BaseAppCompatActivity {

    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_common_act_search);
        ButterKnife.bind(this);

        try {
            ClassifyFragment classifyFragment = (ClassifyFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

            if (classifyFragment == null) {
                classifyFragment = ClassifyFragment.newInstance();

                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        classifyFragment, R.id.contentFrame);
            }

            // Create the presenter
            new ClassifyPresenter(classifyFragment);

            initBack();
            initAction();
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

    private void initAction(){
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(ClassifyActivity.this, SearchActivity.class);
            }
        });
    }
}