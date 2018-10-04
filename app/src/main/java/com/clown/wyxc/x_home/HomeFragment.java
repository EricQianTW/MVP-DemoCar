package com.clown.wyxc.x_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.clown.wyxc.x_home.goodseller.HomeFragment_GoodSeller;
import com.clown.wyxc.x_home.goodseller.HomePresenter_GoodSeller;
import com.clown.wyxc.x_home.icon.HomeFragment_Icon;
import com.clown.wyxc.x_home.icon.HomePresenter_Icon;
import com.clown.wyxc.x_home.recommendgoods.HomeFragment_RecommendGoods;
import com.clown.wyxc.x_home.recommendgoods.HomePresenter_RecommendGoods;
import com.clown.wyxc.x_home.title.HomeFragment_Title;
import com.clown.wyxc.x_home.title.HomePresenter_Title;
import com.pacific.adapter.RecyclerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.ns_home;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/27.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    @Bind(R.id.bannerFrame)
    FrameLayout bannerFrame;
    @Bind(R.id.recommendGoodsFrame)
    FrameLayout recommendGoodsFrame;
    @Bind(ns_home)
    BottomScrollView nsHome;
    @Bind(R.id.cl_home)
    CoordinatorLayout clHome;

    private RecyclerAdapter<IconInfo> adapter;
    private List<IconInfo> mData = new ArrayList<>();

    private HomeContract.Presenter mPresenter;
    private int pagenum = 1;

    private HomeFragment_Banner bannerFragment;
    private HomeFragment_RecommendGoods recommendGoodsFragment;
    private HomeFragment_Title titleFragment;
    private HomeFragment_Icon iconFragment;
    private HomeFragment_GoodSeller goodSellerFragment;

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

        try {
            initAction();
            initFragment();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initData() {
        mPresenter.test();
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
        bannerFragment = (HomeFragment_Banner) getChildFragmentManager().findFragmentById(R.id.bannerFrame);
        recommendGoodsFragment = (HomeFragment_RecommendGoods) getChildFragmentManager().findFragmentById(R.id.recommendGoodsFrame);
        goodSellerFragment = (HomeFragment_GoodSeller) getChildFragmentManager().findFragmentById(R.id.goodSellerFrame);
        titleFragment = (HomeFragment_Title) getChildFragmentManager().findFragmentById(R.id.titleFrame);
        iconFragment = (HomeFragment_Icon) getChildFragmentManager().findFragmentById(R.id.iconFrame);

        if (titleFragment == null) {
            titleFragment = HomeFragment_Title.newInstance();
        }

        if (bannerFragment == null) {
            bannerFragment = HomeFragment_Banner.newInstance();
        }

        if (iconFragment == null) {
            iconFragment = HomeFragment_Icon.newInstance();
        }

        if (recommendGoodsFragment == null) {
            recommendGoodsFragment = HomeFragment_RecommendGoods.newInstance();
        }

        if (goodSellerFragment == null) {
            goodSellerFragment = HomeFragment_GoodSeller.newInstance();
        }

        // 轮播图fragment
        fragments.add(titleFragment);
        fragmentIds.add(R.id.titleFrame);

        // 轮播图fragment
        fragments.add(bannerFragment);
        fragmentIds.add(R.id.bannerFrame);

        // 轮播图fragment
        fragments.add(iconFragment);
        fragmentIds.add(R.id.iconFrame);

        // 推荐商品fragment
        fragments.add(recommendGoodsFragment);
        fragmentIds.add(R.id.recommendGoodsFrame);

        // 推荐商品fragment
        fragments.add(goodSellerFragment);
        fragmentIds.add(R.id.goodSellerFrame);

        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragments, fragmentIds);

        Bundle data1 = new Bundle();
        data1.putInt(HomeFragment_Banner.INTENTNAME_TYPE, Constants.BANNER_TYPE_SHOUYELUBOTU);
        if (!bannerFragment.isAdded()) {
            bannerFragment.setArguments(data1);
        }

        Bundle dataIcon = new Bundle();
        dataIcon.putInt(HomeFragment_Icon.INTENTNAME_TYPE, Constants.BANNER_TYPE_SHOUYEFENLEI);
        if (!iconFragment.isAdded()) {
            iconFragment.setArguments(dataIcon);
        }

        new HomePresenter_Title(titleFragment);
        new HomePresenter_Banner(bannerFragment);
        new HomePresenter_Icon(iconFragment);
        new HomePresenter_RecommendGoods(recommendGoodsFragment);
        new HomePresenter_GoodSeller(goodSellerFragment);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAction(){
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
                    Log.e(TAG,"y1 - y2:"+(y1 - y2)+"------------isbottom:"+isbottom);
                    if(y1 - y2 > 0 && isbottom){
                        try {
                            goodSellerFragment.initData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void settest() {
        nsHome.setVisibility(View.GONE);
    }
}
