package com.clown.wyxc.x_mine.icon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.VerticalImageView;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_mycar.MyCarActivity;
import com.clown.wyxc.x_settleorder.SettleOrderActivity;
import com.clown.wyxc.x_vehicorder.VehicOrderActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MineFragment_Icon extends BaseFragment implements MineContract_Icon.View {

    @Bind(R.id.mycar)
    VerticalImageView mycar;
    @Bind(R.id.allorder)
    VerticalImageView allorder;
    @Bind(R.id.serviceorder)
    VerticalImageView serviceorder;
    @Bind(R.id.goodsorder)
    VerticalImageView goodsorder;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.carappointment)
    VerticalImageView carappointment;
    @Bind(R.id.maintainorder)
    VerticalImageView maintainorder;
    private MineContract_Icon.Presenter mPresenter;

    public MineFragment_Icon() {
        new MinePresenter_Icon(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_frg_icon, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAction();
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

    public static MineFragment_Icon newInstance() {
        return new MineFragment_Icon();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(@NonNull MineContract_Icon.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick({R.id.mycar, R.id.serviceorder, R.id.goodsorder, R.id.allorder,R.id.maintainorder})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mycar:
                IntentUtils.startActivity(getActivity(), MyCarActivity.class);
                break;
            case R.id.serviceorder:
                HashMap mapservice = new HashMap();
                mapservice.put(SettleOrderActivity.ORDER_TYPE, String.valueOf(Constants.ORDER_TYPE_SERVICE));
                IntentUtils.startActivity(getActivity(), SettleOrderActivity.class, mapservice);
                break;
            case R.id.goodsorder:
                HashMap mapgoods = new HashMap();
                mapgoods.put(SettleOrderActivity.ORDER_TYPE, String.valueOf(Constants.ORDER_TYPE_GOODS));
                IntentUtils.startActivity(getActivity(), SettleOrderActivity.class, mapgoods);
                break;
            case R.id.allorder:
                break;
            case R.id.maintainorder:
                IntentUtils.startActivity(getActivity(), VehicOrderActivity.class);
                break;
        }
    }

}
