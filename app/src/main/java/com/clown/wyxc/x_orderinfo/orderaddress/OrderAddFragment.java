package com.clown.wyxc.x_orderinfo.orderaddress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.Merchant;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_map.activity.ReGeocoderActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by CZP on 2016/7/24.
 * 订单联系人地址信息
 */
public class OrderAddFragment extends BaseFragment implements OrderAddContract.View {

    @Bind(R.id.buyer)
    TextView buyer;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;

    @Override
    public void setPresenter(OrderAddContract.Present presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderadd_frag, container, false);

        ButterKnife.bind(this, view);
        getData();
        return view;

    }

    public static OrderAddFragment newInstance() {
        OrderAddFragment fragment = new OrderAddFragment();
        return fragment;
    }

    public void getData() {
        try {
            Bundle bundle = getArguments();
            final OrderInfoDetaliResult info = (OrderInfoDetaliResult) bundle.getSerializable("addressInfo");
            if (info != null) {
                if (info.getSendMode() == Constants.DELEVERY_HOME) {
                    buyer.setVisibility(View.VISIBLE);
                    buyer.setText(info.getOrderFirmOrderAddressResult().getDeliveryAddress().getConsignee());
                    address.setText(info.getOrderFirmOrderAddressResult().getDeliveryAddress().getAddress());
                    tvPhone.setText(info.getOrderFirmOrderAddressResult().getDeliveryAddress().getPhone());

                } else if (info.getSendMode() == Constants.DELEVERY_STORE) {
                    Merchant merchant = info.getOrderFirmOrderAddressResult().getMerchant();
                    address.setText(merchant.getProvName() + merchant.getCityName() + merchant.getRegionName() + merchant.getAddress());
                    tvPhone.setText(info.getOrderFirmOrderAddressResult().getMerchant().getTel());

                    rlMain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            String totalAddress = info.getOrderFirmOrderAddressResult().getMerchant().getProvName() + info.getOrderFirmOrderAddressResult().getMerchant().getCityName()
                                    + info.getOrderFirmOrderAddressResult().getMerchant().getRegionName() + info.getOrderFirmOrderAddressResult().getMerchant().getAddress();
                            map.put(ReGeocoderActivity.INTANTNAME_ADDRESS, totalAddress);
                            IntentUtils.startActivity(getActivity(), ReGeocoderActivity.class, map);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
