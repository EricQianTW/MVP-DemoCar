package com.clown.wyxc.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.clown.wyxc.R;
import com.clown.wyxc.scheme.ExceptionHandle;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.SPUtils;
import com.clown.wyxc.x_bean.UsersResult;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    // 当前class名
    protected String TAG = this.getClass().getName();

    public static UsersResult user;
    protected static AMapLocation aMapLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        aMapLocation = ApplicationManager.getInstance().getaMapLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            TextView tv = (TextView)findViewById(R.id.head_title);
            if(tv != null){
                tv.setText(getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏ActionBar
     */
    protected void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected void initBack() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }

        if(findViewById(R.id.head_back) != null){
            findViewById(R.id.head_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                        getActivity().finish();
                    } else {
                        getSupportFragmentManager().popBackStack();
                    }
                }
            });
        }
    }

    protected void initTitle(String title) {
        TextView tv_title = (TextView) findViewById(R.id.head_title);
        if (tv_title != null) {
            tv_title.setText(title);
        }
    }

    /**
     * 取得当前Activity的弱引用
     *
     * @return
     */
    public static Activity getActivity() {
        Class activityThreadClass = null;
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersResult getUser() {
        try {
            user = GSONUtils.fromJson(String.valueOf(SPUtils.get(this, SPUtils.SP_STRING_USER, "")), UsersResult.class);
            if (user == null) {
                user = new UsersResult();
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
        return user;
    }

    public boolean isLogin() {
        try {
            return Boolean.parseBoolean(SPUtils.get(this, SPUtils.SP_BOOLEAN_LOGIN, false).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
            finish();
        } else {
            getFragmentManager().popBackStack();
        }

    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        try {
            Configuration config = new Configuration();
            if (res != null && config != null) {
                config.setToDefaults();
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void showError(int errorCode, String errorMessage) {
        try {
            ExceptionHandle.handleError(getApplicationContext(), errorCode, errorMessage);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}
