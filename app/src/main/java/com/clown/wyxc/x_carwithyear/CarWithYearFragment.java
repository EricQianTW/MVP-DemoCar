package com.clown.wyxc.x_carwithyear;

import android.app.Activity;
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
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_addcar.AddCarActivity;
import com.clown.wyxc.x_bean.CarInfo;
import com.clown.wyxc.x_bean.x_parambean.CarInfoQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_yearwithcar.YearWithCarActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class CarWithYearFragment extends BaseFragment implements CarWithYearContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private CarWithYearContract.Presenter mPresenter;

    private RecyclerAdapter<CarInfo> adapter;

    private int parentId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carwithyear_frg, container, false);
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

    private void initData() {
        Bundle data = getArguments();
        parentId = data.getInt(AddCarActivity.INTENTNAME_PARENTID);
        mPresenter.getCarInfoYearList(GSONUtils.paramToJson(new CarInfoQuery(parentId, user.getId())));
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

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<CarInfo>(getContext(), R.layout.carwithyear_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final CarInfo info) {
                try {
                    helper.setText(R.id.tv_year, info.getYearType());
                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            map.put(CarWithYearActivity.INTENTNAME_PARENTID, String.valueOf(parentId));
                            map.put(CarWithYearActivity.INTENTNAME_YEARTYPE, String.valueOf(info.getYearType()));
                            IntentUtils.startFragmentForResult(CarWithYearFragment.this, getContext(), YearWithCarActivity.class, map);
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

    public CarWithYearFragment() {
        new CarWithYearPresenter(this);
    }

    public static CarWithYearFragment newInstance() {
        return new CarWithYearFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull CarWithYearContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetCarInfoYearListResult(List<CarInfo> result) {
        try {
            if (result.size() > 0) {
                adapter.addAll(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case Activity.RESULT_FIRST_USER:
                try {
                    Intent mIntent = new Intent();
                    mIntent.putExtra(YearWithCarActivity.INTENTNAME_CARINFO, data.getStringExtra(YearWithCarActivity.INTENTNAME_CARINFO));
                    // 设置结果，并进行传送
                    getActivity().setResult(Constants.RETURN_CODE_COMMON2, mIntent);
                    getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}