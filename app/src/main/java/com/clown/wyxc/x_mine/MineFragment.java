package com.clown.wyxc.x_mine;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_mine.header.MineFragment_Header;
import com.clown.wyxc.x_mine.icon.MineFragment_Icon;
import com.clown.wyxc.x_mine.items.MineFragment_Items;
import com.clown.wyxc.x_mine.list.MineFragment_List;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/27.
 */
public class MineFragment extends BaseFragment implements MineContract.View {

    @Bind(R.id.sf_home)
    SwipeRefreshLayout sfHome;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();

    private MineContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_act, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            initFragment();

            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
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

    public MineFragment(){
    }

    private void initFragment() throws Exception {
        MineFragment_Header toolBarFragment = (MineFragment_Header) getChildFragmentManager().findFragmentById(R.id.headerFrame);
        MineFragment_Items itemsFragment = (MineFragment_Items) getChildFragmentManager().findFragmentById(R.id.itemsFrame);
        MineFragment_Icon iconFragment = (MineFragment_Icon) getChildFragmentManager().findFragmentById(R.id.iconFrame);
        MineFragment_List listFragment = (MineFragment_List) getChildFragmentManager().findFragmentById(R.id.listFrame);

        if (toolBarFragment == null) {
            toolBarFragment = MineFragment_Header.newInstance();
        }

        if (itemsFragment == null) {
            itemsFragment = MineFragment_Items.newInstance();
        }

        if (iconFragment == null) {
            iconFragment = MineFragment_Icon.newInstance();
        }

        if (listFragment == null) {
            listFragment = MineFragment_List.newInstance();
        }

        // 工具栏fragment
        fragments.add(toolBarFragment);
        fragmentIds.add(R.id.headerFrame);

        // 工具栏fragment
        fragments.add(itemsFragment);
        fragmentIds.add(R.id.itemsFrame);

        // 工具栏fragment
        fragments.add(iconFragment);
        fragmentIds.add(R.id.iconFrame);

        // 工具栏fragment
        fragments.add(listFragment);
        fragmentIds.add(R.id.listFrame);

        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragments, fragmentIds);
    }

    private void initAction() throws Exception{
        sfHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        sfHome.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}
