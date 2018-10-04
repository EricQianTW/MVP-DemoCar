package com.clown.wyxc.x_shopmall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.IconInfo;
import com.clown.wyxc.components.BottomScrollView;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_home.banner.HomeFragment_Banner;
import com.clown.wyxc.x_home.banner.HomePresenter_Banner;
import com.clown.wyxc.x_home.icon.HomeFragment_Icon;
import com.clown.wyxc.x_home.icon.HomePresenter_Icon;
import com.clown.wyxc.x_shopmall.recommendgoods.HomeFragment_RecommendGoods;
import com.clown.wyxc.x_shopmall.recommendgoods.HomePresenter_RecommendGoods;
import com.clown.wyxc.x_shopmall.searchbar.HomeFragment_SearchBar;
import com.pacific.adapter.RecyclerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/27.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    @Bind(R.id.recommendGoodsFrame)
    FrameLayout recommendGoodsFrame;
    @Bind(R.id.cl_home)
    CoordinatorLayout clHome;
    @Bind(R.id.titleFrame)
    FrameLayout titleFrame;
    @Bind(R.id.bannerFrame)
    FrameLayout bannerFrame;
    @Bind(R.id.iconFrame)
    FrameLayout iconFrame;
    @Bind(R.id.goodSellerFrame)
    FrameLayout goodSellerFrame;
    @Bind(R.id.ns_home)
    BottomScrollView nsHome;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();

    private RecyclerAdapter<IconInfo> adapter;
    private List<IconInfo> mData = new ArrayList<>();

    private HomeContract.Presenter mPresenter;
    private int pagenum = 1;

    private HomeFragment_Banner bannerFragment;
    private HomeFragment_RecommendGoods recommendGoodsFragment;
    private HomeFragment_SearchBar searchBarFragment;
    private HomeFragment_Icon iconFragment;

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    private boolean isbottom = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_act, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        initAction();
        initHome();

        return view;
    }

    private void initAction() {
        nsHome.setOnScrollToBottomLintener(new BottomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                isbottom = true;
            }
        });
        nsHome.setOnScrollToNoBottomLintener(new BottomScrollView.OnScrollToNoBottomListener() {
            @Override
            public void onScrollNoBottomListener(boolean isBottom) {
                isbottom = false;
            }
        });
        nsHome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 继承了Activity的onTouchEvent方法，直接监听点击事件
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // 当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    Log.e(TAG, "y1 - y2:" + (y1 - y2) + "------------isbottom:" + isbottom);
                    if (y1 - y2 > 0 && isbottom) {
                        try {
                            recommendGoodsFragment.initData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });
    }

    private void initHome() {
        try {
            initFragment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");

            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public HomeFragment() {
    }

    private void initFragment() throws Exception {
        searchBarFragment = (HomeFragment_SearchBar) getChildFragmentManager().findFragmentById(R.id.titleFrame);

        bannerFragment = (HomeFragment_Banner) getChildFragmentManager().findFragmentById(R.id.bannerFrame);

        iconFragment = (HomeFragment_Icon) getChildFragmentManager().findFragmentById(R.id.iconFrame);

        recommendGoodsFragment = (HomeFragment_RecommendGoods) getChildFragmentManager().findFragmentById(R.id.recommendGoodsFrame);

        if (bannerFragment == null) {
            bannerFragment = HomeFragment_Banner.newInstance();
        }

        if (recommendGoodsFragment == null) {
            recommendGoodsFragment = HomeFragment_RecommendGoods.newInstance();
        }

        if (searchBarFragment == null) {
            searchBarFragment = HomeFragment_SearchBar.newInstance();
        }

        if (iconFragment == null) {
            iconFragment = HomeFragment_Icon.newInstance();
        }

        // 轮播图fragment
        fragments.add(bannerFragment);
        fragmentIds.add(R.id.bannerFrame);

        // 搜索fragment
        fragments.add(searchBarFragment);
        fragmentIds.add(R.id.titleFrame);

        // 推荐商品fragment
        fragments.add(recommendGoodsFragment);
        fragmentIds.add(R.id.recommendGoodsFrame);

        // 活动fragment
        fragments.add(iconFragment);
        fragmentIds.add(R.id.iconFrame);

        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragments, fragmentIds);

        Bundle data1 = new Bundle();
        data1.putInt(HomeFragment_Banner.INTENTNAME_TYPE, Constants.BANNER_TYPE_SHANGCHENGBANNER);
        if (!bannerFragment.isAdded()) {
            bannerFragment.setArguments(data1);
        }

        Bundle dataIcon = new Bundle();
        dataIcon.putInt(HomeFragment_Icon.INTENTNAME_TYPE, Constants.BANNER_TYPE_SHANGCHENGICON);
        if (!iconFragment.isAdded()) {
            iconFragment.setArguments(dataIcon);
        }

        new HomePresenter_Banner(bannerFragment);
        new HomePresenter_RecommendGoods(recommendGoodsFragment);
        new HomePresenter_Icon(iconFragment);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}
