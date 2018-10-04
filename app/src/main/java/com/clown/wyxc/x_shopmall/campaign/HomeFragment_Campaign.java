package com.clown.wyxc.x_shopmall.campaign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgActivtiySale;
import com.clown.wyxc.components.WrapContentHeightViewPager;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_shopmall.campaingoods.HomeFragment_CampaignGoods;
import com.clown.wyxc.x_shopmall.campaingoods.HomePresenter_CampaignGoods;
import com.orhanobut.logger.Logger;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.LinePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_Campaign extends BaseFragment implements HomeContract_Campaign.View, ViewPager.OnPageChangeListener, OnItemClickListener {
    @Bind(R.id.pager)
    WrapContentHeightViewPager pager;
    @Bind(R.id.indicator)
    LinePageIndicator indicator;

    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private HomeContract_Campaign.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_campaign, container, false);
        ButterKnife.bind(this, view);

        try {
            initViews(view);
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAction();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        try {
            initData();
        } catch (Exception e) {
            Logger.e(e,TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initViews(View view) throws Exception {
        pager = (WrapContentHeightViewPager) view.findViewById(R.id.pager);

//        pager.setAdapter(mAdapter);

        indicator = (LinePageIndicator) view.findViewById(R.id.indicator);
//        indicator.setViewPager(pager);
    }

    private void initData() throws Exception {
//        if(user !=null) {
//            mPresenter.getBanners(Constants.serialNumber, user.getVerify(), user.getUserId());
//        }else{
//            mPresenter.getBanners(Constants.serialNumber, null, -1);
//        }
    }

    public HomeFragment_Campaign() {

    }

    public static HomeFragment_Campaign newInstance() {
        return new HomeFragment_Campaign();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(HomeContract_Campaign.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {
        T.showShort(getActivity(), "点击了第" + position + "个");
    }

    @Override
    public void setGoods(List<MsgActivtiySale> array) {
        CampaingnFragmentAdapter mAdapter = new CampaingnFragmentAdapter(getChildFragmentManager(), array);
        pager.removeAllViews();
        pager.setAdapter(mAdapter);
        indicator.setViewPager(pager);

        pager.setOffscreenPageLimit(array.size() - 1);
    }
}

class CampaingnFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private int mCount = 0;
    private List<MsgActivtiySale> mArray;

    public CampaingnFragmentAdapter(FragmentManager fm, List<MsgActivtiySale> array) {
        super(fm);
        mCount = array.size();
        mArray = array;
    }

    @Override
    public Fragment getItem(int position) {
        HomeFragment_CampaignGoods fragment = HomeFragment_CampaignGoods.newInstance();
        Bundle data = new Bundle();
        data.putString(HomeFragment_CampaignGoods.INTENTNAME_MSGACTIVTIYSALE, GSONUtils.toJson(mArray.get(position)));
        fragment.setArguments(data);
        new HomePresenter_CampaignGoods(fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getIconResId(int index) {
        return -1;
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}
