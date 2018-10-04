package com.clown.wyxc.x_orderinfo.orderstate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.x_bean.IogisticsInfo;
import com.clown.wyxc.x_bean.OrderDetailState;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CZP on 2016/7/24.
 * 订单物流信息
 */
public class OrderStateFragment extends BaseFragment implements OrderStateContract.View {

    @Bind(R.id.tv_text1)
    TextView tvText1;
    @Bind(R.id.tv_text2)
    TextView tvText2;
    @Bind(R.id.iv_pic)
    ImageView ivPic;
    @Bind(R.id.iv_deliver)
    ImageView ivDeliver;
    @Bind(R.id.wuliu_msg)
    TextView wuliuMsg;
    @Bind(R.id.msg_time)
    TextView msgTime;
    @Bind(R.id.rl_state)
    RelativeLayout rlState;
    @Bind(R.id.tv_ordertime)
    TextView tvOrdertime;

    @Override
    public void setPresenter(OrderStateContract.Present presenter) {

    }

    public static OrderStateFragment newInstance() {
        OrderStateFragment fragment = new OrderStateFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderstate_frag, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void initView() {
        try {
            Bundle bundle = getArguments();
            OrderDetailState state = (OrderDetailState) bundle.getSerializable("orderStateClass");
            IogisticsInfo info = (IogisticsInfo) bundle.getSerializable("iogisticsInfo");
            String str = bundle.getString("orderdate");
//            ImageLoader.getInstance().displayImage(state.getImagePath(), ivPic);
            tvText1.setText(state.getStateTitle());
            tvText2.setText(state.getStateRemark());
            tvOrdertime.setText("下单时间" + str);
            if (info != null) {
                wuliuMsg.setText(info.getWuliuInfo());
                msgTime.setText(info.getTime());
            } else {
                rlState.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
