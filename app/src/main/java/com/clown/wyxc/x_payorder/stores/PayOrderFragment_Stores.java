package com.clown.wyxc.x_payorder.stores;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgFirmOrderForm;
import com.clown.wyxc.bean.MsgOrderCoupon;
import com.clown.wyxc.bean.MsgOrderInfo;
import com.clown.wyxc.bean.MsgOrderItem;
import com.clown.wyxc.components.TagView;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PayOrderFragment_Stores extends BaseFragment implements PayOrderContract_Stores.View {

    @Bind(R.id.cb_choice)
    RadioButton cbChoice;
    @Bind(R.id.goods_salvenum)
    TitleTextview goodsSalvenum;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.rl_button)
    RelativeLayout rlButton;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;

    private PayOrderContract_Stores.Presenter mPresenter;
    private RecyclerAdapter<MsgOrderInfo> adapterOrderShop;
    private RecyclerAdapter<MsgOrderCoupon> adapterCoupon;

    private String orderNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payorder_frg_stores, container, false);

        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            orderNo = data.getString(PayOrderActivity.INTENTNAME_ORDERNO);
            initAdapter();
            initViews(view);
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public PayOrderFragment_Stores() {
        new PayOrderPresenter_Stores(this);
    }

    public static PayOrderFragment_Stores newInstance() {
        return new PayOrderFragment_Stores();
    }

    private void initAction() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initViews(View view) throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapterOrderShop);

        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCoupon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvCoupon.setAdapter(adapterCoupon);
    }

    private void initAdapter() throws Exception {
        adapterOrderShop = new RecyclerAdapter<MsgOrderInfo>(getContext(), R.layout.payorder_adp_stores) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, MsgOrderInfo info) {
                final int position = helper.getAdapterPosition();

                BaseAdapter goodsAdapter = new BaseListAdapter<MsgOrderItem>(getActivity(), info.getOrderItem()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        MsgOrderItem item = (MsgOrderItem) list.get(position);
                        if (convertView == null) {
                            convertView = createViewByType();
                        }
                        setData(item, convertView, position);
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.payorder_adp_goods, null);
                    }

                    private void setData(MsgOrderItem item, View convertView, int position) {
                        TagView goods_tag = ViewHolder.get(convertView, R.id.goods_tag);
//                        List<String> arr = new ArrayList<>();
//                        if (item.getServiceList() != null) {
//                            for (MsgService service : item.getServiceList()) {
//                                arr.add(service.getServiceTitle());
//                            }
//                        }
//                        goods_tag.setTags(arr);
                        TagView.Tag[] tags = {
                                new TagView.Tag("ss", Color.parseColor("#eb6877")),
                                new TagView.Tag("1111", Color.parseColor("#eb6877"))
                        };
                        goods_tag.setTags(tags, " ");
                    }
                };

                helper.setAdapter(R.id.rv_item, goodsAdapter);
            }
        };

        adapterCoupon = new RecyclerAdapter<MsgOrderCoupon>(getContext(), R.layout.firmorder_adp_fav) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, MsgOrderCoupon info) {
                final int position = helper.getAdapterPosition();
                helper.setText(R.id.tv_fav, info.getTitle());

            }
        };
    }

    private void initData() {

        mPresenter.getStores("", 0);
    }

    @Override
    public void setPresenter(@NonNull PayOrderContract_Stores.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setStores(MsgFirmOrderForm item) {
        adapterOrderShop.addAll(item.getOrderinfo());
        adapterCoupon.addAll(item.getMallCoupon());
    }
}
