package com.clown.wyxc.x_home.banner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.convenientbanner.NetworkImageHolderView;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Banner;
import com.clown.wyxc.x_bean.x_parambean.BannerQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_Banner extends BaseFragment implements HomeContract_Banner.View, ViewPager.OnPageChangeListener, OnItemClickListener {
    @Bind(R.id.cv_main)
    CardView cvMain;
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;

    private HomeContract_Banner.Presenter mPresenter;
    public final static String INTENTNAME_TYPE = "intentname_type";
    private boolean flagTurning = false;
    private int typeId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_banner, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始自动翻页
        if (flagTurning) {
            convenientBanner.startTurning(5000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void init() throws Exception {
        Bundle data = getArguments();
        typeId = data.getInt(INTENTNAME_TYPE);
        mPresenter.getBannerListByQuery(GSONUtils.toJson(new BannerQuery(typeId, aMapLocation.getAdCode(), user.getId())));
    }

    public HomeFragment_Banner() {

    }

    public static HomeFragment_Banner newInstance() {
        return new HomeFragment_Banner();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(HomeContract_Banner.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //IntentUtils.startSchemeIntent(getActivity(),pagelist.get(position).getAppLink());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(int position) {
        T.showShort(getActivity(), "点击了第" + position + "个");
    }

    @Override
    public void setGetBannerListByQueryResult(final List<Banner> array) {
        if (array == null || array.size() == 1) {
            flagTurning = false;
        } else {
            flagTurning = true;
        }

        //pagelist = array;
        List<String> strArray = new ArrayList<>();
        for (Banner temp : array) {
            strArray.add(temp.getPic());
        }
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, strArray)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        IntentUtils.startSchemeIntent(getActivity(), array.get(position).getUrl());
                    }
                });
        if (flagTurning) {
            convenientBanner.startTurning(5000);
        }
    }
}
