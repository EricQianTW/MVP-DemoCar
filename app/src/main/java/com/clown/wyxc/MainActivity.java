package com.clown.wyxc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;

import com.clown.wyxc.base.AppManager;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.bean.TabEntity;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_commercial.CommercialFragment;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_home.HomeFragment;
import com.clown.wyxc.x_home.HomePresenter;
import com.clown.wyxc.x_html.HtmlActivity;
import com.clown.wyxc.x_html.HtmlFragment;
import com.clown.wyxc.x_login.LoginFragment;
import com.clown.wyxc.x_mine.MineFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {

    @Bind(R.id.container)
    ViewPager container;
    @Bind(R.id.tabs)
    CommonTabLayout tabs;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static ViewPager mViewPager;

    private String[] mTitles = {"首页", "商户", "车百度", "车商城", "我的"};
    private int[] mIconUnselectIds = {
            R.drawable.tabbarhome_2x, R.drawable.tabbarstore_2x,
            R.drawable.tabbarcarbaidu_2x, R.drawable.tabbarshopcar_2x, R.drawable.tabbarme_2x};
    private int[] mIconSelectIds = {
            R.drawable.tabbarhomeselected_2x, R.drawable.tabbarstoreselected_2x,
            R.drawable.tabbarcarbaiduselected_2x, R.drawable.tabbarshopcarselected_2x, R.drawable.tabbarmeselected_2x};

    private static long firstTime;

    private int mPosition;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public static String PARAM_TAB_INDEX = "index";
    public static String PARAM_TAB2_KEYWORD = "keyword";
    public static Integer index = 0;
    public static String keyword = "";
    private boolean newIntentFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        ButterKnife.bind(this);

        try {

            initView();

            initAdapter();

            initAction();

            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        newIntentFlag = true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            index = Integer.parseInt(getIntent().getStringExtra(PARAM_TAB_INDEX));
            if (index != -1 && newIntentFlag) {
                setIndex(index);
                newIntentFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAdapter() throws Exception {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void initView() throws Exception {
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(5);    //可以防止frag 重复调用oncreateview方法
    }

    private void initData() throws Exception {
        if (tabs.getTabCount() != 0) {
            return;
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabs.setTabData(mTabEntities);

        keyword = getIntent().getStringExtra(PARAM_TAB2_KEYWORD);
    }

    private void initAction() throws Exception {
        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position, false);
                mPosition = position;

                if (position == 2) {
                    swipeRefresh.setEnabled(false);
                }else {
                    swipeRefresh.setEnabled(true);
                }
            }

            @Override
            public void onTabReselect(int position) {
                mPosition = position;
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position < mTitles.length) {
                    tabs.setCurrentTab(position);
                }
                mPosition = position;
                if (position == 2) {
                    swipeRefresh.setEnabled(false);
                }else {
                    swipeRefresh.setEnabled(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {

                    initView();

                    initAdapter();

                    initAction();

                    initData();

                    setIndex(mPosition);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefresh.setRefreshing(false);
                        }
                    },1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setIndex(int index) {
        mViewPager.setCurrentItem(index);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    HomeFragment homeFragment = HomeFragment.newInstance();
                    new HomePresenter(homeFragment);
                    return homeFragment;
                case 1:
                    CommercialFragment commercialFragment = CommercialFragment.newInstance();
                    Bundle dataCommercial = new Bundle();
                    dataCommercial.putString(MainActivity.PARAM_TAB2_KEYWORD, keyword);
                    commercialFragment.setArguments(dataCommercial);
                    return CommercialFragment.newInstance();
                case 2:
                    HtmlFragment htmlFragment = HtmlFragment.newInstance();
                    Bundle data = new Bundle();
                    data.putString(HtmlActivity.PARAM_STRING_URL, "http://api.ixiuc.com/page/chebaidu/chebaidu_index.aspx");
                    data.putString(HtmlActivity.PARAM_STRING_TITLE, "车百度");
                    htmlFragment.setArguments(data);
                    return htmlFragment;
                case 3:
                    com.clown.wyxc.x_shopmall.HomeFragment shopmallFragment = com.clown.wyxc.x_shopmall.HomeFragment.newInstance();
                    new com.clown.wyxc.x_shopmall.HomePresenter(shopmallFragment);
                    return shopmallFragment;
                case 4:
//                    if (isLogin()) {
                    return MineFragment.newInstance();
//                    } else {
//                        return LoginFragment.newInstance();
//                    }
                default:
                    return LoginFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (firstTime + 2000 > System.currentTimeMillis()) {
            AppManager.getAppManager().AppExit(getActivity());
        } else {
            S.showShort(mainContent, "再按一次退出程序");
        }
        firstTime = System.currentTimeMillis();
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        try {
            switch (requestCode) {
                case Constants.REQUEST_CODE_COMMON:
                    try {

                        initView();

                        initAdapter();

                        initAction();

                        initData();

                        setIndex(resultCode);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
