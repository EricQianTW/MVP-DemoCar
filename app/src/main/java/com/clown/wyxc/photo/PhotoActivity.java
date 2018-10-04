package com.clown.wyxc.photo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.scheme.Constants;
import com.clown.wyxc.utils.ActivityUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhoulang on 2016/8/10.
 */
public class PhotoActivity extends BaseAppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout abl_toolbar;
    @Bind(R.id.contentFrame)
    FrameLayout contentFrame;

    private int now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_act);
        ButterKnife.bind(this);
        try {
            initFragment();
            setSupportActionBar(toolbar);
            initBack();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initFragment() throws Exception{

        PhotoFragment commentFragment = (PhotoFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(commentFragment == null){
            commentFragment = PhotoFragment.newInstance();
        }
        Bundle data = new Bundle();
        ArrayList<String> mstr = new ArrayList<String>();
        mstr = getIntent().getStringArrayListExtra(Constants.JUMP_STRING);
        now = getIntent().getIntExtra(Constants.JUMP_STRING1,0);
        data.putInt(Constants.JUMP_STRING1,now);
        data.putStringArrayList(Constants.JUMP_STRING,mstr);
        commentFragment.setArguments(data);
        new PhotoPresenter(commentFragment);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),commentFragment,R.id.contentFrame);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
