package com.clown.wyxc.x_yearwithcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.CarInfo;
import com.clown.wyxc.x_bean.x_parambean.CarInfoYearQuery;
import com.clown.wyxc.x_carwithyear.CarWithYearActivity;
import com.clown.wyxc.x_common.Constants;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class YearWithCarFragment extends BaseFragment implements YearWithCarContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private YearWithCarContract.Presenter mPresenter;

    private RecyclerAdapter<CarInfo> adapter;

    private int parentId;
    private String yearType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yearwithcar_frg, container, false);
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

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initData() {
        Bundle data = getArguments();
        parentId = data.getInt(CarWithYearActivity.INTENTNAME_PARENTID);
        yearType = data.getString(CarWithYearActivity.INTENTNAME_YEARTYPE);
        mPresenter.getCarInfoCXDList(GSONUtils.paramToJson(new CarInfoYearQuery(yearType, parentId, user.getId())));
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<CarInfo>(getContext(), R.layout.carwithyear_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final CarInfo info) {
                try {
                    helper.setText(R.id.tv_year, info.getName());
                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent mIntent = new Intent();
                            mIntent.putExtra(YearWithCarActivity.INTENTNAME_CARINFO, GSONUtils.toJson(info));
                            // 设置结果，并进行传送
                            getActivity().setResult(Constants.RETURN_CODE_COMMON2, mIntent);
                            getActivity().finish();
                        }
                    });
                    helper.getItemView().setTag("hello world");
                } catch (Exception e) {
                    Logger.e(e, TAG);
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

    public YearWithCarFragment() {
        new YearWithCarPresenter(this);
    }

    public static YearWithCarFragment newInstance() {
        return new YearWithCarFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull YearWithCarContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetCarInfoCXDListResult(List<CarInfo> result) {
        try {
            if (result.size() > 0) {
                adapter.addAll(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}