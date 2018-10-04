package com.clown.wyxc.x_dispatching.store;

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
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Merchant;
import com.clown.wyxc.x_bean.OrderFirmOrderAddressResult;
import com.clown.wyxc.x_bean.x_parambean.OrderMerchantQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class DispatchingStoreFragment extends BaseFragment implements DispatchingStoreContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    private DispatchingStoreContract.Presenter mPresenter;

    private RecyclerAdapter<Merchant> adapter;
    private String orderNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dispatchingstore_frg, container, false);
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
        orderNo = getArguments().getString(FirmOrderActivity.INTENTNAME_ORDERNO);
        mPresenter.getMerchantListByGuidgoodsOrderNO(GSONUtils.paramToJson(
                new OrderMerchantQuery(orderNo, new BigDecimal(aMapLocation.getLongitude()), new BigDecimal(aMapLocation.getLatitude()))));
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

    public DispatchingStoreFragment() {
        new DispatchingStorePresenter(this);
    }

    public static DispatchingStoreFragment newInstance() {
        return new DispatchingStoreFragment();
    }

    private void initAction() throws Exception {

    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<Merchant>(getContext(), R.layout.dispatching_store_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final Merchant info) {
                try {
                    helper.setText(R.id.seller_city, info.getCityName())
                            .setText(R.id.seller_name, info.getName())
                            .setText(R.id.seller_address, info.getAddress())
                            .setText(R.id.seller_pingfen, String.valueOf(info.getSort()))
                            .setVisible(R.id.seller_leixing,View.GONE)
                            .getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent mIntent = new Intent();
                                OrderFirmOrderAddressResult result = new OrderFirmOrderAddressResult(null, info, Constants.DELEVERY_STORE);
                                mIntent.putExtra(FirmOrderActivity.INTENTNAME_ADDRESSINFO, GSONUtils.toJson(result));
                                // 设置结果，并进行传送
                                getActivity().setResult(Constants.RETURN_CODE_COMMON, mIntent);
                                getActivity().finish();
                        }
                    });

                    if (info.getMerchantPics() != null && info.getMerchantPics().size() > 0 && info.getMerchantPics().get(0).getPic() != null) {
                        helper.setImageUrl(R.id.seller_img, info.getMerchantPics().get(0).getPic());
                    }
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public void setPresenter(@NonNull DispatchingStoreContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void setGetMerchantListByGuidgoodsOrderNOResult(List<Merchant> result) {
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
}