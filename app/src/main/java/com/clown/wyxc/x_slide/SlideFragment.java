package com.clown.wyxc.x_slide;

import android.os.Bundle;
import android.os.Handler;
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
import com.clown.wyxc.x_bean.Banner;
import com.clown.wyxc.x_bean.x_parambean.BannerQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_slide.slidefragment.SubSlideFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class SlideFragment extends BaseFragment implements SlideContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private SlideContract.Presenter mPresenter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;
    private int index = 0;
    private ViewPager vp;
    private int sum = 0;
    private int mPosition = 0;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run () {
            if (index >= sum) {

            }else{
                vp.setCurrentItem(index);
                index++;
                handler.postDelayed(this,2000);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_frg, container, false);
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
            initAction();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mPresenter.getBannerListByQuery(GSONUtils.paramToJson(new BannerQuery(Constants.BANNER_TYPE_YINDAOYE, aMapLocation.getAdCode(), null)));
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

    public SlideFragment() {
        new SlidePresenter(this);
    }

    public static SlideFragment newInstance() {
        return new SlideFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull SlideContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetBannerListByQueryResult(List<Banner> result) {
        sum = result.size();
        for (Banner banner : result) {
            mFragments.add(SubSlideFragment.getInstance(banner, result.size()));
        }

        View decorView = getActivity().getWindow().getDecorView();
        vp = ViewFindUtils.find(decorView, R.id.vp_slide);
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(mAdapter);

        vp.setCurrentItem(index);
        index++;

        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,2000);


//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },1000);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitles[position];
//        }

        @Override
        public Fragment getItem(int position) {
            mPosition = position;
            return mFragments.get(position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}