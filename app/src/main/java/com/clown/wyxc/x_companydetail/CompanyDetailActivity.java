package com.clown.wyxc.x_companydetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_companydetail.goodsassess.GoodsDetailFragment_Assess;
import com.clown.wyxc.x_companydetail.goodsassess.GoodsDetailPresenter_Assess;
import com.clown.wyxc.x_companydetail.goodsinfo.GoodsDetailFragment_Info;
import com.clown.wyxc.x_companydetail.goodsinfo.GoodsDetailPresenter_Info;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eric_qiantw on 16/4/21.
 */
public class CompanyDetailActivity extends BaseAppCompatActivity implements CompanyDetailContract.View {
    @Bind(R.id.infoFrame)
    FrameLayout infoFrame;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomsheet;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.webViewFrame)
    FrameLayout webViewFrame;
    @Bind(R.id.ns_home)
    NestedScrollView nsHome;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;

    // Parameter名
    public final static String INTENTNAME_COMPANYID = "storeid";

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();

    private GoodsDetailFragment_Info infoFragment;
    private GoodsDetailFragment_Assess assessFragment;
    private CompanyDetailContract.Present mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsdetail_act);
        ButterKnife.bind(this);
        mPresent = new CompanyDetailPresent(this);
        try {
            initFragment();
            initView();
            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    private void initFragment() throws Exception {
        Bundle data = new Bundle();
        data.putInt(INTENTNAME_COMPANYID, Integer.parseInt(getIntent().getStringExtra(INTENTNAME_COMPANYID) == null ? "-1" : getIntent().getStringExtra(INTENTNAME_COMPANYID)));

        infoFragment = initInfoFragment();
        assessFragment = initAssessFragment();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragments, fragmentIds);

        infoFragment.setArguments(data);
        assessFragment.setArguments(data);

        new GoodsDetailPresenter_Info(infoFragment);
        new GoodsDetailPresenter_Assess(assessFragment);

    }

    @NonNull
    private GoodsDetailFragment_Info initInfoFragment() throws Exception{
        GoodsDetailFragment_Info infoFragment = (GoodsDetailFragment_Info) getSupportFragmentManager().findFragmentById(R.id.infoFrame);

        if (infoFragment == null) {
            infoFragment = GoodsDetailFragment_Info.newInstance();
        }

        // 工具栏fragment
        fragments.add(infoFragment);
        fragmentIds.add(R.id.infoFrame);
        return infoFragment;
    }

    @NonNull
    private GoodsDetailFragment_Assess initAssessFragment() throws Exception{
        GoodsDetailFragment_Assess webViewFragment = (GoodsDetailFragment_Assess) getSupportFragmentManager().findFragmentById(R.id.webViewFrame);

        if (webViewFragment == null) {
            webViewFragment = GoodsDetailFragment_Assess.newInstance();
        }

        fragments.add(webViewFragment);
        fragmentIds.add(R.id.webViewFrame);
        return webViewFragment;
    }

    private void initView() throws Exception {
        setSupportActionBar(toolbar);
        initBack();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(CompanyDetailContract.Present presenter) {

    }

    @Override
    public void modifyGoodsCollectionResult(int i) {
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
