package com.clown.wyxc.x_address.address_manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgAddressInfo;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_address.address_list.AddressListActivity;
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
public class AddressManagerFragment extends BaseFragment implements AddressManagerContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.bt_managerAddress)
    Button btManagerAddress;

    private AddressManagerContract.Presenter mPresenter;
    private RecyclerAdapter<MsgAddressInfo> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addressmanager_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            initAdapter();
            initViews();
            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mPresenter.start();
            initData();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() {
//        mPresenter.getAddressList(user.getVerify(), user.getUserId());
    }

    public AddressManagerFragment() {
        new AddressManagerPresenter(this);
    }

    public static AddressManagerFragment newInstance() {
        return new AddressManagerFragment();
    }

    private void initAction() throws Exception {
        btManagerAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), AddressListActivity.class);
            }
        });
    }

    private void initViews() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MsgAddressInfo>(getContext(), R.layout.addressmanager_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MsgAddressInfo info) {
                final int position = helper.getAdapterPosition();
                helper.setText(R.id.username, info.getReceiverName())
                        .setText(R.id.phone, info.getReceiverPhone())
                        .setText(R.id.address, info.getProvince() + info.getCity() + info.getDistrict() + info.getStreetNumber());
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.putExtra(FirmOrderFragment_Address.INTENTNAME_ADDRESSID,info.getId());
//                        getActivity().setResult(getActivity().RESULT_OK,intent);
//                        getActivity().finish();
                    }
                });
            }
        };
    }

    @Override
    public void setPresenter(@NonNull AddressManagerContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setAddressListRes(List<MsgAddressInfo> result) {
        adapter.clear();
        adapter.addAll(result);
    }

    @Override
    public void setDeleteAddressRes(boolean result, int position) {
        adapter.removeAt(position);
    }

    @Override
    public void setDefaultAddressRes(boolean result) {
        initData();
    }
}
