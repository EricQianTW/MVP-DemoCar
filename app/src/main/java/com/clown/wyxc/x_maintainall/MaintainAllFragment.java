package com.clown.wyxc.x_maintainall;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.MaintainItemsResult;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_maintain.MaintainActivity;
import com.google.gson.reflect.TypeToken;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eric_clown on 2017/6/16.
 */

public class MaintainAllFragment extends BaseFragment {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_commit)
    TextView tvCommit;

    private RecyclerAdapter<MaintainItemsResult> adapter;
    private List<MaintainItemsResult> mData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintainall_frg, container, false);
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

    private void initData() {
        String maintainAll = getArguments().getString(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL);
        TypeToken<List<MaintainItemsResult>> token = new TypeToken<List<MaintainItemsResult>>() {
        };
        mData = GSONUtils.fromJson(maintainAll, token);
        adapter.addAll(mData);
        setCommitTv();
    }

    private void initAction() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent();
                mIntent.putExtra(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL, GSONUtils.toJson(mData));
                // 设置结果，并进行传送
                getActivity().setResult(Constants.RETURN_CODE_COMMON2, mIntent);
                getActivity().finish();
            }
        });
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

    public static MaintainAllFragment newInstance() {
        return new MaintainAllFragment();
    }

    private void initAdapter() {
        adapter = new RecyclerAdapter<MaintainItemsResult>(getContext(), mData, R.layout.maintainall_apt) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MaintainItemsResult info) {
                try {
                    helper.setText(R.id.tv_commnet, info.getDetail());
                    final CheckBox checkBox = helper.getView(R.id.cb_choice);
                    checkBox.setText(info.getName());
                    checkBox.setChecked(info.getIsCheck() == 1 ? true : false);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!buttonView.isPressed()) {
                                return;
                            }
                            if (!isChecked) {
                                info.setIsCheck(0);
                            } else {
                                info.setIsCheck(1);
                            }

                            setCommitTv();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void setCommitTv() {
        int count = 0;
        for (MaintainItemsResult maintainItemsResult : mData) {
            if (maintainItemsResult.getIsCheck() == 1) {
                count++;
            }
        }
        tvCommit.setText("确定选择" + count + "项");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
