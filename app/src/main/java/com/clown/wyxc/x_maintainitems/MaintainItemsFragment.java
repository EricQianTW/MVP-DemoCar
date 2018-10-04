package com.clown.wyxc.x_maintainitems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.MaintainItmeGoodsChangeResult;
import com.clown.wyxc.x_bean.x_parambean.MaintainItemGoodsChangeQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_maintain.MaintainActivity;
import com.mancj.slideup.SlideUp;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MaintainItemsFragment extends BaseFragment implements MaintainItemsContract.View {

    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_sliding)
    LinearLayout llSliding;

    private MaintainItemsContract.Presenter mPresenter;

    private RecyclerAdapter<MaintainItmeGoodsChangeResult> adapter;
    private List<MaintainItmeGoodsChangeResult> mData = new ArrayList<>();
    private SlideUp slideUp;
    private int maintainItemId = 0;
    private int maintainItemGoodsId = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintainitems_frg, container, false);
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
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() throws Exception {
        try {
            maintainItemId = Integer.parseInt(getArguments().getString(MaintainItemsActivity.INTENTNAME_MAINTAINITEMID));
            maintainItemGoodsId = Integer.parseInt(getArguments().getString(MaintainItemsActivity.INTENTNAME_MAINTAINITEMGOODSID));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        mPresenter.getMaintainGoodsChange(GSONUtils.paramToJson(new MaintainItemGoodsChangeQuery(user.getId(), maintainItemId, maintainItemGoodsId)));
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);

        slideUp = new SlideUp.Builder(llSliding)
                .withListeners(new SlideUp.Listener.Events() {
                    @Override
                    public void onSlide(float percent) {
                    }

                    @Override
                    public void onVisibilityChanged(int visibility) {
                        if (visibility == View.GONE) {
                            rvIcon.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .withStartGravity(Gravity.BOTTOM)
                .withLoggingEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .build();
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MaintainItmeGoodsChangeResult>(getContext(), mData, R.layout.maintainitems_apt) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MaintainItmeGoodsChangeResult info) {
                try {
                    helper.setText(R.id.goods_name, info.getName());
                    helper.setText(R.id.goods_price, "￥" + BigDecimalUtil.df.format(info.getPrice()));
                    helper.setImageUrl(R.id.goods_pic, info.getPic());
                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent mIntent = new Intent();
                            mIntent.putExtra(MaintainActivity.INTENT_ARGUMENT_GOODSID, String.valueOf(info.getActualGoodsId()));
                            // 设置结果，并进行传送
                            getActivity().setResult(Constants.RETURN_CODE_COMMON3, mIntent);
                            getActivity().finish();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
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

    public MaintainItemsFragment() {
        new MaintainItemsPresenter(this);
    }

    public static MaintainItemsFragment newInstance() {
        return new MaintainItemsFragment();
    }

    private void initAction() throws Exception {
    }

    @Override
    public void setPresenter(@NonNull MaintainItemsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetMaintainGoodsChangeResult(List<MaintainItmeGoodsChangeResult> result) {
        adapter.addAll(result);
    }
}