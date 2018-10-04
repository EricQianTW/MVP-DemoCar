package com.clown.wyxc.x_settleorder.orderfragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.CarInfo;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
import com.clown.wyxc.x_bean.OrderItem;
import com.clown.wyxc.x_bean.OrderItemService;
import com.clown.wyxc.x_bean.x_parambean.GetOrderListByOrderStateQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderDeliverPayQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderQuery;
import com.clown.wyxc.x_comment.CommentActivity;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_map.activity.ReGeocoderActivity;
import com.clown.wyxc.x_orderinfo.OrderInfoActivity;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.clown.wyxc.x_wuliu.WuliuActivity;
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

@SuppressLint("ValidFragment")
public class SettleOrderCellFragment extends BaseFragment implements SettleOrderCellContract.View {
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;

    private SettleOrderCellContract.Presenter mPresenter;
    private GetOrderListByOrderStateQuery mTitle;
    private RecyclerAdapter<OrderInfoDetaliResult> adapter;
    private RecyclerAdapter<CarInfo> adapterService;
    private boolean loadfalg = false;
    private int pageIndex = 1;
    private int currentIndex = -1;
    private int orderType = -1;
    private ProgressDialog dialog;

    public static SettleOrderCellFragment getInstance(GetOrderListByOrderStateQuery title) {
        SettleOrderCellFragment sf = new SettleOrderCellFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_frg, null);
        ButterKnife.bind(this, v);

        try {
            initAdapter();
            initViews();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            if (adapter != null) {
                adapter.clear();
            }
            pageIndex = 1;
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mTitle.setPageIndex(pageIndex);
        // TODO
        dialog.show();
        mPresenter.getOrderListByOrderState(GSONUtils.paramToJson(mTitle));
    }

    private void initAction() {
        rvIcon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, dy + "");
                if (isSlideToBottom(recyclerView, dy)) {
                    if (!loadfalg) {
                        loadfalg = true;
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                mTitle.setPageIndex(pageIndex);
                                // TODO
                                dialog.show();
                                mPresenter.getOrderListByOrderState(GSONUtils.paramToJson(mTitle));
                            }
                        }, 200);
                    }
                }
            }

            protected boolean isSlideToBottom(RecyclerView recyclerView, int dy) {
                if (recyclerView == null) return false;
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange() && dy > 0)
                    return true;
                return false;
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initViews() throws Exception {

        if (dialog == null) {
            dialog = new ProgressDialog(getContext());
            dialog.setMessage("等待加载...");
        }

        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        orderType = mTitle.getOrderType();
        if (mTitle.getOrderType() == Constants.ORDER_TYPE_GOODS) {
            adapter = new RecyclerAdapter<OrderInfoDetaliResult>(getContext(), R.layout.myorder_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final OrderInfoDetaliResult info) {
                    final int position = helper.getAdapterPosition();
                    helper.setText(R.id.store_name, info.getGoodsShop().getShopName() +"(单号:"+info.getOrderId()+")");
                    setViews(helper, info);

                    BaseAdapter goodsAdapter = new BaseListAdapter<OrderItem>(getActivity(), info.getOrderItemList()) {
                        @Override
                        public View bindView(int position, View convertView, ViewGroup parent) {
                            convertView = null;
                            OrderItem item = (OrderItem) list.get(position);
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(item, convertView, position);
                            convertView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
                                    intent.putExtra(OrderInfoActivity.INTENTNAME_ORDERID, info.getOrderId());
                                    startActivity(intent);
                                }
                            });
                            return convertView;
                        }

                        private View createViewByType() {
                            return mInflater.inflate(R.layout.myorder_adp_goods, null);
                        }

                        private void setData(OrderItem item, View convertView, int position) {
                            ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                            TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                            TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                            TextView goods_count = ViewHolder.get(convertView, R.id.goods_count);
                            TextView goods_attr = ViewHolder.get(convertView, R.id.goods_attr);
                            goods_name.setText(item.getGoodsName());
                            goods_count.setText("X" + item.getGoodsNum());
                            goods_price.setText("￥" + String.valueOf(item.getGoodsPrice()));
                            goods_attr.setText(item.getAttrValue());
                            try {
                                ImageLoader.getInstance().displayImage(item.getPic(), goods_image);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    helper.setAdapter(R.id.rv_item, goodsAdapter);

                    setButtons(helper, info);

                    setButtonAction(helper, info, position);
                }
            };
        } else if (mTitle.getOrderType() == Constants.ORDER_TYPE_SERVICE) {
            adapter = new RecyclerAdapter<OrderInfoDetaliResult>(getContext(), R.layout.myorder_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final OrderInfoDetaliResult info) {
                    try {
                        final int position = helper.getAdapterPosition();
                        if (info.getSendMode() == 1) {
                            helper.setText(R.id.store_name, info.getOrderFirmOrderAddressResult().getMerchant().getName()+"(单号:"+info.getOrderId()+")");
                        } else {
                            helper.setText(R.id.store_name, info.getGoodsShop().getShopName()+"(单号:"+info.getOrderId()+")");
                        }
                        setViews(helper, info);

                        BaseAdapter goodsAdapter = new BaseListAdapter<OrderItemService>(getActivity(), info.getOrderItemServiceList()) {
                            @Override
                            public View bindView(int position, View convertView, ViewGroup parent) {
                                convertView = null;
                                OrderItemService item = (OrderItemService) list.get(position);
                                if (convertView == null) {
                                    convertView = createViewByType();
                                }
                                setData(item, convertView, position);
                                convertView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
                                        intent.putExtra(OrderInfoActivity.INTENTNAME_ORDERID, info.getOrderId());
                                        startActivity(intent);
                                    }
                                });
                                return convertView;
                            }

                            private View createViewByType() {
                                return mInflater.inflate(R.layout.myorder_adp_goods, null);
                            }

                            private void setData(OrderItemService item, View convertView, int position) {
                                ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                                TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                                TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                                TextView goods_count = ViewHolder.get(convertView, R.id.goods_count);
                                TextView goods_attr = ViewHolder.get(convertView, R.id.goods_attr);
                                goods_name.setText(item.getName());
                                goods_count.setText("X" + item.getBuyNum());
                                goods_price.setText("￥" + BigDecimalUtil.df.format(item.getPrice()));
                                goods_attr.setText(item.getSimpleDetail());
                                try {
                                    ImageLoader.getInstance().displayImage(item.getPic(), goods_image);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        helper.setAdapter(R.id.rv_item, goodsAdapter);

                        setButtons(helper, info);

                        setButtonAction(helper, info, position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        } else if (mTitle.getOrderType() == Constants.ORDER_TYPE_MAINTAIN) {
            adapter = new RecyclerAdapter<OrderInfoDetaliResult>(getContext(), R.layout.myorder_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final OrderInfoDetaliResult info) {
                    final int position = helper.getAdapterPosition();
                    if (info.getSendMode() == 1) {
                        helper.setText(R.id.store_name, info.getOrderFirmOrderAddressResult().getMerchant().getName()+"(单号:"+info.getOrderId()+")");
                    } else {
                        helper.setText(R.id.store_name, info.getGoodsShop().getShopName()+"(单号:"+info.getOrderId()+")");
                    }
                    setViews(helper, info);

                    BaseAdapter goodsAdapter = new BaseListAdapter<OrderItem>(getActivity(), info.getOrderItemList()) {
                        @Override
                        public View bindView(int position, View convertView, ViewGroup parent) {
                            convertView = null;
                            OrderItem item = (OrderItem) list.get(position);
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(item, convertView, position);
                            convertView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
                                    intent.putExtra(OrderInfoActivity.INTENTNAME_ORDERID, info.getOrderId());
                                    startActivity(intent);
                                }
                            });
                            return convertView;
                        }

                        private View createViewByType() {
                            return mInflater.inflate(R.layout.myorder_adp_goods, null);
                        }

                        private void setData(OrderItem item, View convertView, int position) {
                            ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                            TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                            TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                            TextView goods_count = ViewHolder.get(convertView, R.id.goods_count);
                            TextView goods_attr = ViewHolder.get(convertView, R.id.goods_attr);
                            goods_name.setText(item.getGoodsName());
                            goods_count.setText("X" + item.getGoodsNum());
                            goods_price.setText("￥" + String.valueOf(item.getGoodsPrice()));
                            goods_attr.setText(item.getAttrValue());
                            try {
                                ImageLoader.getInstance().displayImage(item.getPic(), goods_image);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    helper.setAdapter(R.id.rv_item, goodsAdapter);

                    setButtons(helper, info);

                    setButtonAction(helper, info, position);
                }
            };
        }

    }

    private void setViews(RecyclerAdapterHelper helper, OrderInfoDetaliResult info) {
        try {
            helper.setText(R.id.order_number, "（订单号:" + info.getGoodsOrderNO() + ")");
            if (info.getOrderDetailState() != null) {
                if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFUKUAN) {
                    helper.setText(R.id.buystatus, "待付款");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFAHUO) {
                    helper.setText(R.id.buystatus, "待发货");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAISHOUHUO) {
                    helper.setText(R.id.buystatus, "待收货");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIPINGJIA) {
                    helper.setText(R.id.buystatus, "待评价");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_YIPINGJIA) {
                    helper.setText(R.id.buystatus, "已完成");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFUWU) {
                    helper.setText(R.id.buystatus, "待服务");
                } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIQUEREN) {
                    helper.setText(R.id.buystatus, "待确认");
                }
            }

            int count = 0;
            if (info.getOrderItemList() != null) {
                count = count + info.getOrderItemList().size();
            }

            if (info.getOrderItemServiceList() != null) {
                count = count + info.getOrderItemServiceList().size();
            }
            helper.setText(R.id.tv_comment1, "共" + count + "件商品 合计：");
            helper.setText(R.id.tv_comment2, "￥" + info.getSubtotal());
            if (info.getPostage() != null && !"".equals(info.getPostage())) {
                helper.setText(R.id.tv_comment3, " (" + info.getPostage() + ") ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setButtonAction(RecyclerAdapterHelper helper, final OrderInfoDetaliResult info, final int position) {
        helper.setOnClickListener(R.id.btn_cancelorder, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = position;
                mPresenter.orderCancelByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), info.getOrderId())));
            }
        });

        helper.setOnClickListener(R.id.btn_pingjia, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra(CommentActivity.INTENT_ORDERID, info.getOrderId());
                startActivity(intent);
            }
        });

        helper.setOnClickListener(R.id.btn_payorder, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> arr = new ArrayList<>();
                arr.add(info.getOrderId());
                mPresenter.orderDeliverPay(GSONUtils.paramToJson(new OrderDeliverPayQuery(user.getId(), arr)));
            }
        });

        helper.setOnClickListener(R.id.btn_surereceive, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = position;
                mPresenter.orderConfirmByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), info.getOrderId())));
            }
        });

        helper.setOnClickListener(R.id.btn_searchwuliu, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WuliuActivity.class);
                intent.putExtra(WuliuActivity.INTENTNAME_ORDERID, info.getOrderId());
                startActivity(intent);
            }
        });

        helper.setOnClickListener(R.id.btn_daohang, new View.OnClickListener() {
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

    private void setButtons(RecyclerAdapterHelper helper, OrderInfoDetaliResult info) {
        RelativeLayout rl_group = (RelativeLayout) helper.getItemView().findViewById(R.id.btn_group);
        View lines = helper.getItemView().findViewById(R.id.lines);
        TextView btn_cancelorder = (TextView) helper.getItemView().findViewById(R.id.btn_cancelorder);
        TextView btn_pingjia = (TextView) helper.getItemView().findViewById(R.id.btn_pingjia);
        TextView btn_payorder = (TextView) helper.getItemView().findViewById(R.id.btn_payorder);
        TextView btn_surereceive = (TextView) helper.getItemView().findViewById(R.id.btn_surereceive);
//        TextView btn_wuliu = (TextView) helper.getItemView().findViewById(R.id.btn_searchwuliu);
//        TextView btn_shanchu = (TextView) helper.getItemView().findViewById(R.id.btn_shanchu);
        TextView btn_daohang = (TextView) helper.getItemView().findViewById(R.id.btn_daohang);
        if (info.getOrderDetailState() != null) {
            if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFUKUAN) {
                btn_cancelorder.setVisibility(View.VISIBLE);
                btn_payorder.setVisibility(View.VISIBLE);
                rl_group.setVisibility(View.VISIBLE);
                lines.setVisibility(View.VISIBLE);
            } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFAHUO) {
                rl_group.setVisibility(View.GONE);
                lines.setVisibility(View.GONE);
            } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAISHOUHUO) {
                btn_surereceive.setVisibility(View.VISIBLE);
                rl_group.setVisibility(View.VISIBLE);
//                btn_wuliu.setVisibility(View.VISIBLE);
                lines.setVisibility(View.VISIBLE);
            } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIPINGJIA) {
                btn_pingjia.setVisibility(View.VISIBLE);
                rl_group.setVisibility(View.VISIBLE);
//                btn_wuliu.setVisibility(View.VISIBLE);
//                btn_shanchu.setVisibility(View.VISIBLE);
                lines.setVisibility(View.VISIBLE);
            } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_YIPINGJIA) {
                rl_group.setVisibility(View.VISIBLE);
//                btn_wuliu.setVisibility(View.VISIBLE);
//                btn_shanchu.setVisibility(View.VISIBLE);
                lines.setVisibility(View.VISIBLE);
            } else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIFUWU) {
                rl_group.setVisibility(View.VISIBLE);
                btn_daohang.setVisibility(View.VISIBLE);
                lines.setVisibility(View.VISIBLE);
            } else {
                rl_group.setVisibility(View.GONE);
            }
//        else if (info.getOrderDetailState().getFlag() == Constants.ORDER_STATUS_DAIQUEREN) {
//            rl_group.setVisibility(View.VISIBLE);
//            btn_wuliu.setVisibility(View.VISIBLE);
//            btn_shanchu.setVisibility(View.VISIBLE);
//            lines.setVisibility(View.VISIBLE);
//        }
        }
    }

    @Override
    public void setPresenter(SettleOrderCellContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public SettleOrderCellFragment() {
        new SettleOrderCellPresenter(this);
    }

    public static SettleOrderCellFragment newInstance(GetOrderListByOrderStateQuery query) {
        SettleOrderCellFragment fragment = new SettleOrderCellFragment();
        fragment.mTitle = query;
        return fragment;
    }

    @Override
    public void setGetOrderListByOrderStateResult(List<OrderInfoDetaliResult> result) {

        try {
            if (result.size() != 0) {
                rvIcon.setVisibility(View.VISIBLE);
                ivEmpty.setVisibility(View.INVISIBLE);
                adapter.replaceAll(result);
                pageIndex++;
//                adapter.addAll(result);
            }
            loadfalg = true;

            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setOrderCancelByOrderIdResult(int result) {
        T.showShort(getContext(), "取消成功");
        adapter.removeAt(currentIndex);
        adapter.notifyDataSetChanged();
        currentIndex = -1;
    }

    @Override
    public void setOrderConfirmByOrderIdResult(int result) {
        T.showShort(getContext(), "交易完成");
        adapter.removeAt(currentIndex);
        adapter.notifyDataSetChanged();
        currentIndex = -1;
    }

    @Override
    public void setOrderPayResult(String result) {

    }

    @Override
    public void setOrderDeleteByOrderIdResult(int result) {
        T.showShort(getContext(), "删除成功");
        adapter.removeAt(currentIndex);
        adapter.notifyDataSetChanged();
        currentIndex = -1;
    }

    @Override
    public void setOrderDeliverPayResult(String result) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PayOrderActivity.INTENTNAME_ORDERNO, result);
        IntentUtils.startActivity(getActivity(), PayOrderActivity.class, map);
    }
}