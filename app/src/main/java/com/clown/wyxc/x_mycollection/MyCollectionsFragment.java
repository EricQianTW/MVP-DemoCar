package com.clown.wyxc.x_mycollection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.flyco.ViewFindUtils;
import com.clown.wyxc.x_mycollection.orderfragment.OrderFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MyCollectionsFragment extends BaseFragment implements MyCollectionsContract.View, OnTabSelectListener {

    @Bind(R.id.stl_title)
    SlidingTabLayout stlTitle;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public final String[] mTitles = {
            "店铺", "商品"
    };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    private MyCollectionsContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycollections_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initView();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() throws Exception {
//        for (String title : mTitles) {
//            mFragments.add(OrderFragment.newInstance(title));
//        }
//
//        for (int i = 0; i < mTitles.length; i++) {
//            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
//        }

//        stlTitle.setTabData(mTabEntities, getActivity(), R.id.fl_change, mFragments);

        for (String title : mTitles) {
            mFragments.add(OrderFragment.newInstance(title));
        }
//
//        View decorView = getActivity().getWindow().getDecorView();
//        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
//        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
//        vp.setAdapter(mAdapter);
//
//        stlTitle.setViewPager(vp);
//        stlTitle.setOnTabSelectListener(this);

        View decorView = getActivity().getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(mTitles.length);

        stlTitle.setViewPager(vp);
        stlTitle.setOnTabSelectListener(this);

//        for (int i = 0; i < mTitles.length; i++) {
//            if (mTitles[i].getOrderState() == orderState) {
//                vp.setCurrentItem(i);
//            }
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public MyCollectionsFragment() {
        new MyCollectionsPresenter(this);
    }

    public static MyCollectionsFragment newInstance() {
        return new MyCollectionsFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull MyCollectionsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}