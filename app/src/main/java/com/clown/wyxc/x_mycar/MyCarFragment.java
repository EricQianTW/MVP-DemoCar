package com.clown.wyxc.x_mycar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.baselibray.material.app.Dialog;
import com.clown.baselibray.material.app.DialogFragment;
import com.clown.baselibray.material.app.SimpleDialog;
import com.clown.baselibray.material.app.ThemeManager;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_addcar.AddCarActivity;
import com.clown.wyxc.x_bean.MyCarsResult;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_common.Constants;
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
public class MyCarFragment extends BaseFragment implements MyCarContract.View {

    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_addcar)
    TextView tvAddcar;

    private MyCarContract.Presenter mPresenter;

    private RecyclerAdapter<MyCarsResult> adapter;

    private String source;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycar_frg, container, false);
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

            source = getArguments().getString(MyCarActivity.INTENTNAME_SOURCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() throws Exception{
        mPresenter.getMyCarsByUsersId(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    private void initView() {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    public MyCarFragment() {
        new MyCarPresenter(this);
    }

    public static MyCarFragment newInstance() {
        return new MyCarFragment();
    }

    private void initAction() {
        tvAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), AddCarActivity.class);
            }
        });
    }

    @Override
    public void setPresenter(@NonNull MyCarContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MyCarsResult>(getContext(), R.layout.mycar_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MyCarsResult info) {
                try {
                    try {
                        helper.setText(R.id.tv_carname, info.getCarCXName() == null ? "车辆型号" : info.getPpName())
                                .setText(R.id.tv_carplate, info.getCarNo() == null ? "车辆拍照" : info.getCarNo())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (MyCarActivity.INTENTNAME_SOURCE_SELECT.equals(source)) {
                                    Intent mIntent = new Intent();
                                    mIntent.putExtra(MyCarActivity.INTENTNAME_CARID, GSONUtils.toJson(info));
                                    // 设置结果，并进行传送
                                    getActivity().setResult(Constants.RETURN_CODE_COMMON, mIntent);
                                    getActivity().finish();
                                }
                            }
                        });

                        if (info.getLogo() != null && !"".equals(info.getLogo())) {
                            helper.setImageUrl(R.id.iv_carlogo, info.getLogo());
                        }

                        if (info.getIsDefault() != null && info.getIsDefault() == 1) {
                            helper.setText(R.id.tv_default, "默认车辆");
                            helper.setTextColor(R.id.tv_default, Color.RED);
                        } else {
                            helper.setText(R.id.tv_default, "设为默认");
                            helper.setTextColor(R.id.tv_default, Color.BLUE);
                            helper.setOnClickListener(R.id.tv_default, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Dialog.Builder builder = null;
                                    boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                                    builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog){
                                        @Override
                                        public void onPositiveActionClicked(DialogFragment fragment) {
                                            mPresenter.cilckSetDefault(GSONUtils.paramToJson(new QueryId(info.getId(), user.getId())));
                                            super.onPositiveActionClicked(fragment);
                                        }

                                        @Override
                                        public void onNegativeActionClicked(DialogFragment fragment) {
                                            super.onNegativeActionClicked(fragment);
                                        }
                                    };

                                    builder.title("确认设置成默认?")
                                            .positiveAction("确认")
                                            .negativeAction("取消");

                                    DialogFragment fragment = DialogFragment.newInstance(builder);
                                    fragment.show(getFragmentManager(), null);
                                }
                            });
                        }

                        helper.setOnClickListener(R.id.tv_modify, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap map = new HashMap();
                                map.put(AddCarActivity.INTENTNAME_CARID, String.valueOf(info.getId()));
                                IntentUtils.startActivity(getContext(), AddCarActivity.class, map);
                            }
                        });
                        helper.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Dialog.Builder builder = null;
                                boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog){
                                    @Override
                                    public void onPositiveActionClicked(DialogFragment fragment) {
                                        mPresenter.deleteMyCarsById(GSONUtils.paramToJson(new QueryId(info.getId(), user.getId())));
                                        super.onPositiveActionClicked(fragment);
                                    }

                                    @Override
                                    public void onNegativeActionClicked(DialogFragment fragment) {
                                        super.onNegativeActionClicked(fragment);
                                    }
                                };

                                builder.title("确认删除?")
                                        .positiveAction("确认")
                                        .negativeAction("取消");

                                DialogFragment fragment = DialogFragment.newInstance(builder);
                                fragment.show(getFragmentManager(), null);
                            }
                        });
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

    @Override
    public void setGetMyCarsByUsersIdResult(List<MyCarsResult> result) {
        try {
            if (result.size() > 0) {
                adapter.clear();
                adapter.addAll(result);
            } else {
                T.showShort(getContext(), "没有更多的数据了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setDeleteMyCarsByIdResult(Integer result) {
        try {
            if (result == 1) {
                T.showShort(getContext(), "删除成功");
            }
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCilckSetDefaultResult(Integer result) {
        try {
            if (result == 1) {
                T.showShort(getContext(), "设置成功");
            }
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}