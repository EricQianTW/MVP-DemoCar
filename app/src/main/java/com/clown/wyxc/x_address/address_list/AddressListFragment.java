package com.clown.wyxc.x_address.address_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

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
import com.clown.wyxc.x_address.address_new.AddressNewActivity;
import com.clown.wyxc.x_bean.DeliveryAddress;
import com.clown.wyxc.x_bean.OrderFirmOrderAddressResult;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
import com.clown.wyxc.x_firmorder.FirmOrderFragment;
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
public class AddressListFragment extends BaseFragment implements AddressListContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.bt_addAddress)
    Button btAddAddress;

    private AddressListContract.Presenter mPresenter;
    private RecyclerAdapter<DeliveryAddress> adapter;
    private String orderNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addresslist_frg, container, false);
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
        orderNo = FirmOrderFragment.newInstance().getOrderNo();
        mPresenter.getDeliveryAddressByUsersId(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    public AddressListFragment() {
        new AddressListPresenter(this);
    }

    public static AddressListFragment newInstance() {
        return new AddressListFragment();
    }

    private void initAction() {
        btAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(),AddressNewActivity.class);
            }
        });
    }

    private void initViews() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.nav_header_vertical_spacing)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<DeliveryAddress>(getContext(), R.layout.addresslist_apt) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final DeliveryAddress info) {
                final int position = helper.getAdapterPosition();
                helper.setText(R.id.username, info.getConsignee())
                        .setText(R.id.phone, info.getPhone())
                        .setText(R.id.address, info.getAddress());

                if (info.getIsDefault() != null) {
                    helper.setChecked(R.id.ck_default, info.getIsDefault() == 1 ? true : false);
                    helper.setText(R.id.tv_default, info.getIsDefault() == 1 ? "默认地址" : "设为默认");
                    if(info.getIsDefault()==1){
                        helper.setTextColor(R.id.tv_default, ContextCompat.getColor(getContext(), R.color.red_ff));
                    }else{
                        helper.setTextColor(R.id.tv_default, ContextCompat.getColor(getContext(), R.color.gray_99));
                    }
                }


                helper.setOnClickListener(R.id.ll_edit, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(AddressNewActivity.INTENTNAME_NEWRECEIVERINFOID, String.valueOf(info.getId()));
                        IntentUtils.startActivity(getContext(), AddressNewActivity.class, map);
                    }
                });

                helper.setOnClickListener(R.id.delete_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog.Builder builder = null;
                        boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                        builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog){
                            @Override
                            public void onPositiveActionClicked(DialogFragment fragment) {
                                mPresenter.deleteDeliveryAddressById(GSONUtils.paramToJson(new QueryId(info.getId(),user.getId())));
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

                helper.setOnCheckedChangeListener(R.id.ck_default, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
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
                    }
                });
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (orderNo != null && !"".equals(orderNo)) {
                            Intent mIntent = new Intent();
                            OrderFirmOrderAddressResult result = new OrderFirmOrderAddressResult(info,null, Constants.DELEVERY_HOME);
                            mIntent.putExtra(FirmOrderActivity.INTENTNAME_ADDRESSINFO, GSONUtils.toJson(result));
                            // 设置结果，并进行传送
                            getActivity().setResult(Constants.RETURN_CODE_COMMON3, mIntent);
                            getActivity().finish();
                        }
                    }
                });
            }
        };
    }

    @Override
    public void setPresenter(@NonNull AddressListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetDeliveryAddressByUsersIdResult(List<DeliveryAddress> result) {
        try {
            if (result.size() > 0) {
                adapter.replaceAll(result);
            } else {
                T.showShort(getContext(), "没有更多的地址信息了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setDeleteDeliveryAddressByIdResult(int result) {
        initData();
    }

    @Override
    public void setCilckSetDefaultResult(int result) {
//        adapter.notifyDataSetChanged();
        initData();
    }
}
