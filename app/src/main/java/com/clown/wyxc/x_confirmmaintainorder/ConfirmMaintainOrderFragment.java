package com.clown.wyxc.x_confirmmaintainorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clown.baselibray.material.widget.RelativeLayout;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ConfirmMaintainOrderFragment extends BaseFragment implements ConfirmMaintainOrderContract.View {

    @Bind(R.id.ll_main)
    RelativeLayout llMain;

    private ConfirmMaintainOrderContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirmmaintainorder_frg, container, false);
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
            initView();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() throws Exception {
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

    public ConfirmMaintainOrderFragment() {
        new ConfirmMaintainOrderPresenter(this);
    }

    public static ConfirmMaintainOrderFragment newInstance() {
        return new ConfirmMaintainOrderFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull ConfirmMaintainOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


}