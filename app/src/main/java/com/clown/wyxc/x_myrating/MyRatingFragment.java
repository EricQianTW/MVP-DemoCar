package com.clown.wyxc.x_myrating;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.SellerInfo;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MyRatingFragment extends BaseFragment implements MyRatingContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private MyRatingContract.Presenter mPresenter;

    private RecyclerAdapter<SellerInfo> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myrating_frg, container, false);
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
            initAdapter();
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

    public MyRatingFragment() {
        new MyRatingPresenter(this);
    }

    public static MyRatingFragment newInstance() {
        return new MyRatingFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull MyRatingContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<SellerInfo>(getContext(), R.layout.brandsub_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final SellerInfo info) {
                try {
                    try {
                        helper.setText(R.id.tv_brandname, info.getSellerCity());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    helper.getItemView().setTag("hello world");
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        };
    }
}