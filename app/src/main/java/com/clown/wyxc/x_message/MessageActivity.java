package com.clown.wyxc.x_message;

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
 * Created by CZP on 2016/7/19.
 */
public class MessageActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.contentfrag)
    FrameLayout contentfrag;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_act);
        ButterKnife.bind(this);
        try {

            initFragment();
            setSupportActionBar(toolbar);
            initBack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFragment() throws Exception {
        MessageFragment msgfrag = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.contentfrag);
        if (msgfrag == null) {
            msgfrag = MessageFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), msgfrag
                , R.id.contentfrag);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
