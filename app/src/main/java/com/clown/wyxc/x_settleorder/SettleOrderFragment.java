package com.clown.wyxc.x_settleorder;

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
import com.clown.wyxc.x_bean.x_parambean.GetOrderListByOrderStateQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_settleorder.orderfragment.SettleOrderCellFragment;
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
public class SettleOrderFragment extends BaseFragment implements SettleOrderContract.View, OnTabSelectListener {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.stl_title)
    SlidingTabLayout stlTitle;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
//    private final String[] mTitles = {
//            "已完成", "待付款", "待发货"
//            , "待收货", "待评价"
//    };

    private List<GetOrderListByOrderStateQuery> goodsArr = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    private SettleOrderContract.Presenter mPresenter;

    private Integer orderType, orderState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settleorder_frg, container, false);
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
            initView();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void initData() {
        Bundle data = getArguments();
        orderType = data.getInt(SettleOrderActivity.ORDER_TYPE);
        orderState = data.getInt(SettleOrderActivity.ORDER_STATE);

        if (Constants.ORDER_TYPE_GOODS == orderType) {
            GetOrderListByOrderStateQuery temp0 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, null, "全部");
            GetOrderListByOrderStateQuery temp1 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, Constants.ORDER_STATUS_YIPINGJIA, "已完成");
            GetOrderListByOrderStateQuery temp2 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, Constants.ORDER_STATUS_DAIFUKUAN, "待付款");
            GetOrderListByOrderStateQuery temp3 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, Constants.ORDER_STATUS_DAIFAHUO, "待发货");
            GetOrderListByOrderStateQuery temp4 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, Constants.ORDER_STATUS_DAISHOUHUO, "待收货");
            GetOrderListByOrderStateQuery temp5 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_GOODS, Constants.ORDER_STATUS_DAIPINGJIA, "待评价");

            goodsArr.add(temp0);
            goodsArr.add(temp2);
            goodsArr.add(temp3);
            goodsArr.add(temp4);
            goodsArr.add(temp5);
            goodsArr.add(temp1);
        } else if (Constants.ORDER_TYPE_SERVICE == orderType) {
            GetOrderListByOrderStateQuery temp0 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, null, "全部");
            GetOrderListByOrderStateQuery temp1 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, Constants.ORDER_STATUS_YIPINGJIA, "已完成");
            GetOrderListByOrderStateQuery temp2 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, Constants.ORDER_STATUS_DAIFUKUAN, "待付款");
            GetOrderListByOrderStateQuery temp3 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, Constants.ORDER_STATUS_DAIFUWU, "待服务");
            GetOrderListByOrderStateQuery temp4 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, Constants.ORDER_STATUS_DAIQUEREN, "待确认");
            GetOrderListByOrderStateQuery temp5 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_SERVICE, Constants.ORDER_STATUS_DAIPINGJIA, "待评价");

            goodsArr.add(temp0);
            goodsArr.add(temp2);
            goodsArr.add(temp3);
            goodsArr.add(temp4);
            goodsArr.add(temp5);
            goodsArr.add(temp1);
        } else if (Constants.ORDER_TYPE_MAINTAIN == orderType) {
            GetOrderListByOrderStateQuery temp0 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_MAINTAIN, null, "全部");
            GetOrderListByOrderStateQuery temp1 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_MAINTAIN, Constants.ORDER_STATUS_YIPINGJIA, "已完成");
            GetOrderListByOrderStateQuery temp2 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_MAINTAIN, Constants.ORDER_STATUS_DAIFUKUAN, "待付款");
            GetOrderListByOrderStateQuery temp3 = new GetOrderListByOrderStateQuery(user.getId(), 1, Constants.ORDER_TYPE_MAINTAIN, Constants.ORDER_STATUS_DAIPINGJIA, "待评价");

            goodsArr.add(temp0);
            goodsArr.add(temp2);
            goodsArr.add(temp3);
            goodsArr.add(temp1);
        }

    }

    private void initView() throws Exception {
        for (GetOrderListByOrderStateQuery title : goodsArr) {
            mFragments.add(SettleOrderCellFragment.newInstance(title));
        }

        View decorView = getActivity().getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);
        vp.setOffscreenPageLimit(goodsArr.size());

        stlTitle.setViewPager(vp);
        stlTitle.setOnTabSelectListener(this);

        for (int i = 0; i < goodsArr.size(); i++) {
            if (goodsArr.get(i).getOrderState() == orderState) {
                vp.setCurrentItem(i);
            }
        }
    }

    public SettleOrderFragment() {
        new SettleOrderPresenter(this);
    }

    public static SettleOrderFragment newInstance() {
        return new SettleOrderFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull SettleOrderContract.Presenter presenter) {
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
            return goodsArr.get(position).getTitleName();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}