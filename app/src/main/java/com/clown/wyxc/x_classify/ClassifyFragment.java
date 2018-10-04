package com.clown.wyxc.x_classify;

import android.app.TabActivity;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.flyco.ViewFindUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.GoodsTypeResult;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_classify.servicefragment.SimpleCardFragment;
import com.clown.wyxc.x_search.SearchActivity;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class ClassifyFragment extends BaseFragment implements ClassifyContract.View, OnTabSelectListener {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.stl_title)
    SlidingTabLayout stlTitle;
    @Bind(R.id.iv_action)
    ImageView ivAction;
    @Bind(R.id.ll_home)
    LinearLayout llHome;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.iv_back)
    ImageView mIvBack;
    private boolean carShow = true;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private MyPagerAdapter mAdapter;

    private ClassifyContract.Presenter mPresenter;

    private List<GoodsTypeResult> serviceItemsResultList = new ArrayList<>();

    private int goodsId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifygoods_frg, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mPresenter.getGoodsType(GSONUtils.paramToJson(new QueryUserId(user.getId())));
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

    public ClassifyFragment() {
        new ClassifyPresenter(this);
    }

    public static ClassifyFragment newInstance() {
        return new ClassifyFragment();
    }

    private void initAction() throws Exception {
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getActivity(), SearchActivity.class);
            }
        });

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), TabActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull ClassifyContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void setGetGoodsTypeResult(List<GoodsTypeResult> result) {
        try {
            serviceItemsResultList.addAll(result);

            for (GoodsTypeResult title : serviceItemsResultList) {
                mFragments.add(SimpleCardFragment.newInstance(title));
            }

            View decorView = getActivity().getWindow().getDecorView();
            ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
            mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
            vp.setAdapter(mAdapter);

            stlTitle.setViewPager(vp);
            stlTitle.setOnTabSelectListener(this);
            vp.setOffscreenPageLimit(result.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            return serviceItemsResultList.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}