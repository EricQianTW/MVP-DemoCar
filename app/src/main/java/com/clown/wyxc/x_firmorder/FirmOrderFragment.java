package com.clown.wyxc.x_firmorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.x_bean.OrderFirmOrderAddressResult;
import com.clown.wyxc.x_firmorder.address.FirmOrderFragment_Address;
import com.clown.wyxc.x_firmorder.stores.FirmOrderFragment_Stores;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/27.
 */
public class FirmOrderFragment extends BaseFragment implements FirmOrderContract.View {

    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();
    private FirmOrderFragment_Stores itemsFragment;

    private FirmOrderContract.Presenter mPresenter;

    private OrderFirmOrderAddressResult mOrderFirmOrderAddressResult;
    private String orderNo;

    private static FirmOrderFragment firmOrderFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmorder_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            initFragment();

            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");

            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public FirmOrderFragment() {
    }

    public  static FirmOrderFragment newInstance() {
        if (firmOrderFragment == null) {
            firmOrderFragment = new FirmOrderFragment();
        }
        return firmOrderFragment;
    }

    @Override
    public void setPresenter(FirmOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initFragment() throws Exception {
        final Bundle data = getArguments();

        FirmOrderFragment_Address addressFragment = (FirmOrderFragment_Address) getChildFragmentManager().findFragmentById(R.id.addressFrame);

        if (addressFragment == null) {
            addressFragment = FirmOrderFragment_Address.newInstance();
        }

        // 工具栏fragment
        fragments.add(addressFragment);
        fragmentIds.add(R.id.addressFrame);

        addressFragment.setArguments(data);

        ActivityUtils.addFragmentToActivity(getChildFragmentManager(), fragments, fragmentIds);

//        addressFragment.setOnGetAddressListen(new FirmOrderFragment_Address.OnGetAddressListening() {
//            @Override
//            public void GetAddressListening() {
////                Bundle data = getArguments();
////                data.putString(FirmOrderFragment_Address.INTENTNAME_ADDRESSINFO, GSONUtils.toJson(getmOrderFirmOrderAddressResult()));
//
//                itemsFragment = (FirmOrderFragment_Stores) getChildFragmentManager().findFragmentById(R.id.storesFrame);
//                if (itemsFragment == null) {
//                    itemsFragment = FirmOrderFragment_Stores.newInstance();
//                }
//                fragments.add(itemsFragment);
//                fragmentIds.add(R.id.storesFrame);
////                itemsFragment.setArguments(data);
//                ActivityUtils.addFragmentToFragment(getChildFragmentManager(), fragments, fragmentIds);
//            }
//        });
    }

    private void initAction() throws Exception {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public OrderFirmOrderAddressResult getmOrderFirmOrderAddressResult() {
        return mOrderFirmOrderAddressResult;
    }

    public void setmOrderFirmOrderAddressResult(OrderFirmOrderAddressResult mOrderFirmOrderAddressResult) {
        this.mOrderFirmOrderAddressResult = mOrderFirmOrderAddressResult;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
