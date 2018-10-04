package com.clown.wyxc.x_firmorder.stores;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.FirmOrderFormResult;
import com.clown.wyxc.x_bean.GetOrderByGuidgoodsOrderNOQuery;
import com.clown.wyxc.x_bean.MaintainItemInfo;
import com.clown.wyxc.x_bean.OrderFirmOrderAddressResult;
import com.clown.wyxc.x_bean.OrderInfo;
import com.clown.wyxc.x_bean.OrderItem;
import com.clown.wyxc.x_bean.OrderServiceInfo;
import com.clown.wyxc.x_bean.x_parambean.CreateOrderPayQuery;
import com.clown.wyxc.x_firmorder.FirmOrderFragment;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class FirmOrderFragment_Stores extends BaseFragment implements FirmOrderContract_Stores.View {

    @Bind(R.id.cb_choice)
    CheckBox cbChoice;
    @Bind(R.id.goods_salvenum)
    TitleTextview goodsSalvenum;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.rl_button)
    RelativeLayout rlButton;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.rv_coupon)
    RecyclerView rvCoupon;

    private FirmOrderContract_Stores.Presenter mPresenter;
    private RecyclerAdapter<OrderInfo> adapterOrderShop;
    private RecyclerAdapter<OrderServiceInfo> adapterOrderService;
    //    private RecyclerAdapter<MsgOrderCoupon> adapterCoupon;
    private String orderNo;
    private OrderFirmOrderAddressResult addressInfo;
    private FirmOrderFormResult msgFirmOrderForm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmorder_frg_stores, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        try {
            orderNo = FirmOrderFragment.newInstance().getOrderNo();
            addressInfo = FirmOrderFragment.newInstance().getmOrderFirmOrderAddressResult();

            initAdapter();
            initViews();
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public FirmOrderFragment_Stores() {
        new FirmOrderPresenter_Stores(this);
    }

    public static FirmOrderFragment_Stores newInstance() {
        return new FirmOrderFragment_Stores();
    }

    private void initAction() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.createOrderPay(GSONUtils.paramToJson(new CreateOrderPayQuery(user.getId(), msgFirmOrderForm)));
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
        rvIcon.setAdapter(adapterOrderShop);

        rvCoupon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCoupon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvCoupon.setAdapter(adapterOrderService);
    }

    private void initAdapter() throws Exception {
        adapterOrderShop = new RecyclerAdapter<OrderInfo>(getContext(), R.layout.firmorder_adp_stores) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, OrderInfo info) {
                final int position = helper.getAdapterPosition();

                helper.setText(R.id.store_name, info.getGoodsShop().getShopName());
                TitleTextview goodsnum = (TitleTextview) helper.getItemView().findViewById(R.id.goodsnum);
                goodsnum.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(info.getSubtotal())));

                TitleTextview postvalue = (TitleTextview) helper.getItemView().findViewById(R.id.postvalue);
                postvalue.setTt_text_back(info.getPostage());

                BaseAdapter goodsAdapter = new BaseListAdapter<OrderItem>(getActivity(), info.getOrderItemList()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        OrderItem item = (OrderItem) list.get(position);
                        if (convertView == null) {
                            convertView = createViewByType();
                        }
                        setData(item, convertView, position);
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.firmorder_adp_goods, null);
                    }

                    private void setData(final OrderItem item, View convertView, int position) {

                        RelativeLayout rl_main = ViewHolder.get(convertView, R.id.rl_main);
                        ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                        TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                        TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                        TextView goods_oldprice = ViewHolder.get(convertView, R.id.goods_oldprice);
                        TextView goods_num = ViewHolder.get(convertView, R.id.goods_num);
                        TextView goods_attr = ViewHolder.get(convertView, R.id.goods_attr);
                        ImageLoader.getInstance().displayImage(item.getPic(), goods_image);
                        goods_name.setText(item.getGoodsName());
                        goods_price.setText("￥" + String.valueOf(BigDecimalUtil.df.format(item.getGoodsPrice())));
                        goods_oldprice.setText("￥" + String.valueOf(BigDecimalUtil.df.format(item.getOldPrice())));
                        goods_num.setText("x" + String.valueOf(item.getGoodsNum()));
                        goods_attr.setText(item.getAttrValue());

                        rl_main.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap();
                                map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(item.getGoodsId()));
                                IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                            }
                        });
                    }
                };

                helper.setAdapter(R.id.rv_item, goodsAdapter);
            }
        };

        adapterOrderService = new RecyclerAdapter<OrderServiceInfo>(getContext(), R.layout.firmorder_adp_stores) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, OrderServiceInfo info) {
                final int position = helper.getAdapterPosition();

                try {
                    if (info.getMerchant().getName() != null) {
                        helper.setText(R.id.store_name, info.getMerchant().getName());
                    }
                    TitleTextview goodsnum = (TitleTextview) helper.getItemView().findViewById(R.id.goodsnum);
                    goodsnum.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(info.getSubtotal())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                TitleTextview postvalue = (TitleTextview) helper.getItemView().findViewById(R.id.postvalue);
//                postvalue.setTt_text_back(info.getPostage());

                BaseAdapter goodsAdapter = new BaseListAdapter<MaintainItemInfo>(getActivity(), info.getMaintainItemInfoList()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        MaintainItemInfo item = (MaintainItemInfo) list.get(position);
                        if (convertView == null) {
                            convertView = createViewByType();
                        }
                        setData(item, convertView, position);
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.firmorder_adp_goods, null);
                    }

                    private void setData(final MaintainItemInfo item, View convertView, int position) {

                        RelativeLayout rl_main = ViewHolder.get(convertView, R.id.rl_main);
                        ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                        TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                        TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                        TextView goods_oldprice = ViewHolder.get(convertView, R.id.goods_oldprice);
                        TextView goods_num = ViewHolder.get(convertView, R.id.goods_num);
                        ImageLoader.getInstance().displayImage(item.getPic(), goods_image);
                        goods_name.setText(item.getMaintainItemName());
                        goods_price.setText("￥" + String.valueOf(BigDecimalUtil.df.format(item.getMaintainItemPrice())));
                        goods_num.setText("x" + String.valueOf(item.getBuyNum()));

                        rl_main.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap();
                                map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(item.getId()));
                                IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                            }
                        });
                    }
                };

                helper.setAdapter(R.id.rv_item, goodsAdapter);
            }
        };
    }

    private void initData() throws Exception {
        mPresenter.getOrderSureByGuidgoodsOrderNO(GSONUtils.paramToJson(new GetOrderByGuidgoodsOrderNOQuery(user.getId(), orderNo, addressInfo)));
    }

    @Override
    public void setPresenter(@NonNull FirmOrderContract_Stores.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetOrderSureByGuidgoodsOrderNOResult(FirmOrderFormResult result) {
        try {
            msgFirmOrderForm = result;
            if (result.getOrderList() != null && result.getOrderList().size() != 0) {
                adapterOrderShop.addAll(result.getOrderList());
            }

            if (result.getOrderServiceInfo() != null) {
                List<OrderServiceInfo> tempArr = new ArrayList<>();
                tempArr.add(result.getOrderServiceInfo());
                adapterOrderService.addAll(tempArr);
            }

            if (result.getTotalPrice() != null) {
                goodsSalvenum.setTt_text_back(String.valueOf(result.getTotalPrice()));
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setCreateOrderPayResult(String result) {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(PayOrderActivity.INTENTNAME_ORDERNO, result);
            IntentUtils.startActivity(getActivity(), PayOrderActivity.class, map);
            getActivity().finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
