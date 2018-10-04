package com.clown.wyxc.x_comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;

/**
 * Created by CZP on 2016/7/11.
 */
public class CommentActivity extends BaseAppCompatActivity {

    public static final String INTENT_ORDERID = "orderid";
    private  int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_act);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//软键盘默认不弹出
        try {
            getOrderId();
            initFragment();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            initBack();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initFragment()throws Exception {
        CommentFragment commentFragment = (CommentFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(commentFragment == null){
            commentFragment = CommentFragment.newInstance();
        }
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_ORDERID,id);
        commentFragment.setArguments(bundle);
        new CommentPresenter(commentFragment);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),commentFragment,R.id.contentFrame);

    }

    public void getOrderId() throws Exception {
        Intent intent = getIntent();
        id = intent.getIntExtra(CommentActivity.INTENT_ORDERID,0);
    }
}
