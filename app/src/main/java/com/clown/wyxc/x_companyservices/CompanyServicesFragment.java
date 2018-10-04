package com.clown.wyxc.x_companyservices;

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
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.ServiceItemsResult;
import com.clown.wyxc.x_bean.x_parambean.ServiceItemsQuery;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.clown.wyxc.x_companyservices.servicefragment.SimpleCardFragment;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class CompanyServicesFragment extends BaseFragment implements CompanyServicesContract.View, OnTabSelectListener {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.stl_title)
    SlidingTabLayout stlTitle;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private MyPagerAdapter mAdapter;

    private CompanyServicesContract.Presenter mPresenter;

    private List<ServiceItemsResult> serviceItemsResultList = new ArrayList<>();

    private int goodsId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.companyservices_frg, container, false);
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
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        Bundle data = getArguments();
        goodsId = data.getInt(CompanyDetailActivity.INTENTNAME_COMPANYID);

        mPresenter.getMerchantServiceItemsListByQuery(GSONUtils.paramToJson(new ServiceItemsQuery(goodsId,user.getId())));
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

    public CompanyServicesFragment() {
        new CompanyServicesPresenter(this);
    }

    public static CompanyServicesFragment newInstance() {
        return new CompanyServicesFragment();
    }

    private void initAction() throws Exception{

    }

    @Override
    public void setPresenter(@NonNull CompanyServicesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onTabSelect(int position) {

    }

    @Override
    public void onTabReselect(int position) {

    }

    @Override
    public void setGetMerchantServiceItemsListByQueryResult(List<ServiceItemsResult> result) {
        try {
            serviceItemsResultList.addAll(result);

            for (ServiceItemsResult title : serviceItemsResultList) {
                mFragments.add(SimpleCardFragment.newInstance(title,goodsId));
            }

            View decorView = getActivity().getWindow().getDecorView();
            ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
            mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
            vp.setAdapter(mAdapter);

            stlTitle.setViewPager(vp);
            stlTitle.setOnTabSelectListener(this);
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
