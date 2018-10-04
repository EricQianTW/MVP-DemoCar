package com.clown.wyxc.x_shopmallgoodsdetail.setmeal;

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
import com.clown.wyxc.bean.MsgGoodsPackage;
import com.clown.wyxc.components.WrapContentHeightViewPager;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.setmealpage.GoodsDetailFragment_SetMealPage;
import com.clown.wyxc.x_shopmallgoodsdetail.setmealpage.GoodsDetailPresenter_SetMealPage;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.LinePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_SetMeal extends BaseFragment implements GoodsDetailContract_SetMeal.View, ViewPager.OnPageChangeListener, OnItemClickListener {
    @Bind(R.id.pager)
    WrapContentHeightViewPager pager;
    @Bind(R.id.indicator)
    LinePageIndicator indicator;

    SetMealFragmentAdapter mAdapter;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    private int goodsId;

    private GoodsDetailContract_SetMeal.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_setmeal, container, false);
        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);

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
//        mAdapter = new SetMealFragmentAdapter(getChildFragmentManager());
//        mAdapter.setCount(4);

        pager = (WrapContentHeightViewPager) view.findViewById(R.id.pager);
//        pager.setAdapter(mAdapter);

        indicator = (LinePageIndicator) view.findViewById(R.id.indicator);
//        indicator.setViewPager(pager);
    }

    private void initData() {
//        if (user != null) {
//            mPresenter.getGoodsPackage(user.getVerify(), user.getUserId(), goodsId);
//        }else {
//            mPresenter.getGoodsPackage("", -1, goodsId);
//        }
    }

    public GoodsDetailFragment_SetMeal() {

    }

    public static GoodsDetailFragment_SetMeal newInstance() {
        return new GoodsDetailFragment_SetMeal();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(GoodsDetailContract_SetMeal.Presenter presenter) {
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
    public void setGoodsPackage(List<MsgGoodsPackage> array) {
        try {

            if (array.size() != 0) {
                mAdapter = new SetMealFragmentAdapter(getChildFragmentManager(), array);
                pager.setAdapter(mAdapter);
                indicator.setViewPager(pager);
            } else {
                try {
                    ((GoodsDetailActivity) getActivity()).removeFragment(this);
                    ((GoodsDetailActivity) getActivity()).removeSetMealFrame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class   SetMealFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    private int mCount = 0;
    private FragmentManager manager;
    private List<MsgGoodsPackage> mArray;

    public SetMealFragmentAdapter(FragmentManager fm, List<MsgGoodsPackage> array) {
        super(fm);
        manager = fm;
        mCount = array.size();
        mArray = array;
    }

    @Override
    public Fragment getItem(int position) {

        GoodsDetailFragment_SetMealPage fragment = GoodsDetailFragment_SetMealPage.newInstance();
        Bundle data = new Bundle();
        data.putString("position", GSONUtils.toJson(mArray.get(position)));
        fragment.setArguments(data);
        new GoodsDetailPresenter_SetMealPage(fragment);
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
