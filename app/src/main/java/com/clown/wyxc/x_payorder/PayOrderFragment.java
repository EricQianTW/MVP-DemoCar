package com.clown.wyxc.x_payorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_payorder.paymode.PayOrderFragment_Mode;
import com.clown.wyxc.x_payorder.paymode.PayOrderPresenter_Mode;
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
public class PayOrderFragment extends BaseFragment implements PayOrderContract.View {

    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();

    private PayOrderContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmorder_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            initFragment();

            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }

        return view;
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

    public PayOrderFragment() {
    }

    public static PayOrderFragment newInstance() {
        return new PayOrderFragment();
    }

    @Override
    public void setPresenter(PayOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initFragment() throws Exception {
        Bundle data = getArguments();

        PayOrderFragment_Mode itemsFragment = (PayOrderFragment_Mode) getChildFragmentManager().findFragmentById(R.id.storesFrame);

        if (itemsFragment == null) {
            itemsFragment = PayOrderFragment_Mode.newInstance();
        }

        // 工具栏fragment
        fragments.add(itemsFragment);
        fragmentIds.add(R.id.storesFrame);

        itemsFragment.setArguments(data);

        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragments, fragmentIds);

        new PayOrderPresenter_Mode(itemsFragment);
    }

    private void initAction() throws Exception {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
