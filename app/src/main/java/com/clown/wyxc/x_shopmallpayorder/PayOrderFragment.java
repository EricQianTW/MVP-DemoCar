package com.clown.wyxc.x_shopmallpayorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.MsgOrderPay;
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
public class PayOrderFragment extends BaseFragment implements PayOrderContract.View {

    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_fukuan)
    LinearLayout llFukuan;
    @Bind(R.id.tv_lijizhifu)
    TextView tvLijizhifu;
    @Bind(R.id.tv_password)
    TextView tvPassword;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_payway)
    TextView tvPayway;

    private PayOrderContract.Presenter mPresenter;

    private RecyclerAdapter<MsgOrderPay> adapter;

    private String orderNo;
    private MsgOrderPay selectPay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopmallpayorder_frg, container, false);
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
            initViews();
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
        orderNo = data.getString(PayOrderActivity.INTENTNAME_ORDERNO);
        mPresenter.getOrderInfoByUserId(user.getId(), orderNo);
    }

    public PayOrderFragment() {
        new PayOrderPresenter(this);
    }

    public static PayOrderFragment newInstance() {
        return new PayOrderFragment();
    }

    private void initAction() throws Exception {
        tvLijizhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(tvPassword.getText().toString())) {
                    T.showShort(getContext(), "请输入密码");
                    return;
                }
                mPresenter.OrderPay(user.getId(), orderNo, tvPassword.getText().toString(), selectPay.getPayPathId());
            }
        });
        tvAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llFukuan.setVisibility(View.GONE);
                rvIcon.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void setPresenter(@NonNull PayOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void getOrderInfoByUserIdResult(List<MsgOrderPay> list) {
        try {
            if (list.size() > 0) {
                adapter.addAll(list);
            } else {
                S.showShort(llMain, "没有更多记录了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void OrderPayResult(String result) {
        IntentUtils.startSchemeIntentWithFinish(getContext(), result);
    }

    @Override
    public void GetOrderSumAmtResult(BigDecimal result) {
        tvAmount.setText("需支付：" + BigDecimalUtil.df.format(result) + "元");
        tvPayway.setText(selectPay.getPayPathName());
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MsgOrderPay>(getContext(), R.layout.payorder_frg_way) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MsgOrderPay info) {
                try {
                    helper.setText(R.id.tv_title, info.getPayPathName())
                            .setText(R.id.tv_price, "所需：" + BigDecimalUtil.df.format(info.getDispayPayAmt()) + "，")
                            .setImageUrl(R.id.iv_icon, info.getIconImage())
                            .setText(R.id.tv_myprice, "（账户：" + BigDecimalUtil.df.format(info.getDispayPayMyAmt()) + "）");

                    if (info.getDispayPayAmt().compareTo(info.getDispayPayMyAmt()) > 0) {
                        helper.setTextColor(R.id.tv_title, getResources().getColor(R.color.gray));
                        helper.setTextColor(R.id.tv_price, getResources().getColor(R.color.gray));
                        helper.setTextColor(R.id.tv_myprice, getResources().getColor(R.color.gray));
                    }else{
                        helper.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llFukuan.setVisibility(View.VISIBLE);
                                rvIcon.setVisibility(View.GONE);
                                selectPay = info;
                                mPresenter.GetOrderSumAmt(user.getId(), orderNo);
                            }
                        });
                    }

                    helper.getItemView().setTag("hello world");
                } catch (
                        Exception e)

                {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        }

        ;
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
}