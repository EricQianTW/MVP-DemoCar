package com.clown.wyxc.x_changepassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ChangePasswordFragment extends BaseFragment implements ChangePasswordContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private ChangePasswordContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.changepassword_frg, container, false);
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

    public ChangePasswordFragment() {
        new ChangePasswordPresenter(this);
    }

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull ChangePasswordContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

}