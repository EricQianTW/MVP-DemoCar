package com.clown.wyxc.x_search;

import android.os.Bundle;
import android.view.MenuItem;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.orhanobut.logger.Logger;


/**
 * A login screen that offers login via email/password.
 */
public class SearchActivity extends BaseAppCompatActivity {

    public static String name = null;

    public final static String INTENTNAME_TYPE1 = "typeid";
    public final static String INTENTNAME_TYPE2 = "type2";
    public final static String INTENTNAME_GOODSTYPE = "goodstype";
    public final static String INTENTNAME_KEYVALUE = "keyword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);
        try {
            initFragment();
        } catch (Exception e) {
            Logger.e(e,TAG);
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragment() {
        SearchFragment mineFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (mineFragment == null) {
            mineFragment = SearchFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mineFragment, R.id.contentFrame);
        }
        Bundle data = new Bundle();
        data.putString(INTENTNAME_TYPE1,getIntent().getStringExtra(INTENTNAME_TYPE1));
        data.putString(INTENTNAME_TYPE2,getIntent().getStringExtra(INTENTNAME_TYPE2));
        data.putString(INTENTNAME_KEYVALUE,getIntent().getStringExtra(INTENTNAME_KEYVALUE));
        mineFragment.setArguments(data);
        new SearchPresenter(mineFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

