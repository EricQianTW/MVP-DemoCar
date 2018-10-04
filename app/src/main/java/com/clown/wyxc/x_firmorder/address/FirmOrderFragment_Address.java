package com.clown.wyxc.x_firmorder.address;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.clown.wyxc.x_bean.x_parambean.InModel;
import com.clown.wyxc.x_bean.x_parambean.OrderDeliveryAddressQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_dispatching.DispatchingActivity;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class FirmOrderFragment_Address extends BaseFragment implements FirmOrderContract_Address.View {


    @Bind(R.id.cb_choice)
    CheckBox cbChoice;
    @Bind(R.id.goods_salvenum)
    TitleTextview goodsSalvenum;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.rl_button)
    RelativeLayout rlButton;
    @Bind(R.id.icon_address)
    ImageView iconAddress;
    @Bind(R.id.icon_arrow)
    ImageView iconArrow;
    @Bind(R.id.tv_username)
    TextView tvUsername;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.tt_receiver)
    TitleTextview ttReceiver;
    @Bind(R.id.tt_address)
    TextView ttAddress;
    @Bind(R.id.rl_comment)
    RelativeLayout rlComment;
    @Bind(R.id.ll_main_address)
    RelativeLayout llMainAddress;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.rv_coupon)
    RecyclerView rvCoupon;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    private FirmOrderContract_Address.Presenter mPresenter;

    public final static String INTENTNAME_ADDRESSID = "addressId";
    public final static String INTENTNAME_ADDRESSINFO = "addressInfo";

    private RecyclerAdapter<OrderInfo> adapterOrderShop;
    private RecyclerAdapter<OrderServiceInfo> adapterOrderService;

    private String orderNo;
    private FirmOrderFormResult msgFirmOrderForm;
    private ProgressDialog dialog;

    public FirmOrderFragment_Address() {
        new FirmOrderPresenter_Address(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firmorder_frg_address, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initAdapter();
            initViews();
            initAction();

            orderNo = getArguments().getString(FirmOrderActivity.INTENTNAME_ORDERNO);

            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        String param = GSONUtils.paramToJson(new OrderDeliveryAddressQuery(user.getId()
                , orderNo
                , null
                , new BigDecimal(aMapLocation.getLongitude())
                , new BigDecimal(aMapLocation.getLatitude())));
        mPresenter.getOrderAddressByGuidgoodsOrderNO(param);
    }

    private void initViews() throws Exception {
        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
        }
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
                try {
                    if (info.getMerchant().getName() != null) {
                        helper.setText(R.id.store_name, info.getMerchant().getName());
                    }
                    TitleTextview goodsnum = (TitleTextview) helper.getItemView().findViewById(R.id.goodsnum);
                    goodsnum.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(info.getSubtotal())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

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

    public static FirmOrderFragment_Address newInstance() {
        return new FirmOrderFragment_Address();
    }

    private void initAction() throws Exception {
        llMainAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(DispatchingActivity.INTENTNAME_ORDERNO, orderNo);
                IntentUtils.startFragmentForResult(FirmOrderFragment_Address.this, getContext(), DispatchingActivity.class, map);
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.createOrderPay(GSONUtils.paramToJson(new CreateOrderPayQuery(user.getId(), msgFirmOrderForm)));
            }
        });
    }

    @Override
    public void setPresenter(@NonNull FirmOrderContract_Address.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {
                case Activity.RESULT_FIRST_USER:
                    if (resultCode == Constants.RETURN_CODE_COMMON) {
                        String carInfoStr = data.getStringExtra(FirmOrderActivity.INTENTNAME_ADDRESSINFO);
                        OrderFirmOrderAddressResult info = GSONUtils.fromJson(carInfoStr, OrderFirmOrderAddressResult.class);

                        InModel model = new InModel(info.getAddressType(), info.getMerchant().getId());
                        String param = GSONUtils.paramToJson(new OrderDeliveryAddressQuery(user.getId()
                                , orderNo
                                , model
                                , new BigDecimal(aMapLocation.getLongitude())
                                , new BigDecimal(aMapLocation.getLatitude())));
                        mPresenter.getOrderAddressByGuidgoodsOrderNO(param);
                    } else if (resultCode == Constants.RETURN_CODE_COMMON3) {
                        String merchatStr = data.getStringExtra(FirmOrderActivity.INTENTNAME_ADDRESSINFO);
                        OrderFirmOrderAddressResult info = GSONUtils.fromJson(merchatStr, OrderFirmOrderAddressResult.class);

                        InModel model = new InModel(info.getAddressType(), info.getDeliveryAddress().getId());
                        String param = GSONUtils.paramToJson(new OrderDeliveryAddressQuery(user.getId()
                                , orderNo
                                , model
                                , new BigDecimal(aMapLocation.getLongitude())
                                , new BigDecimal(aMapLocation.getLatitude())));
                        mPresenter.getOrderAddressByGuidgoodsOrderNO(param);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setGetOrderAddressByGuidgoodsOrderNOResult(OrderFirmOrderAddressResult result) {
        if (result.getAddressType() == Constants.DELEVERY_HOME) {
            tvUsername.setText("联系人:" + result.getDeliveryAddress().getConsignee());
            ttReceiver.setVisibility(View.GONE);
            tvPhone.setText(result.getDeliveryAddress().getPhone());
            ttAddress.setText(result.getDeliveryAddress().getAddress());
        } else if (result.getAddressType() == Constants.DELEVERY_STORE) {
            tvUsername.setText("联系人:" + result.getDeliveryAddress().getConsignee());
            ttReceiver.setVisibility(View.VISIBLE);
            ttReceiver.setTt_text_front("到店安装");
            ttReceiver.setTt_text_back(result.getMerchant().getName());
            tvPhone.setText(result.getMerchant().getTel());
            ttAddress.setText(result.getMerchant().getProvName() + result.getMerchant().getCityName()
                    + result.getMerchant().getRegionName() + result.getMerchant().getAddress());
        }

        adapterOrderService.clear();
        adapterOrderShop.clear();
        dialog.show();
        mPresenter.getOrderSureByGuidgoodsOrderNO(GSONUtils.paramToJson(new GetOrderByGuidgoodsOrderNOQuery(user.getId(), orderNo, result)));
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

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            },500);

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

    public interface OnGetAddressListening {
        void GetAddressListening();
    }

    OnGetAddressListening mOnGetAddressListen = null;

    public void setOnGetAddressListen(OnGetAddressListening e) {
        mOnGetAddressListen = e;
    }
}
