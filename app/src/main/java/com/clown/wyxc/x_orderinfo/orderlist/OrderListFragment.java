package com.clown.wyxc.x_orderinfo.orderlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
import com.clown.wyxc.x_bean.OrderItem;
import com.clown.wyxc.x_bean.OrderItemService;
import com.clown.wyxc.x_bean.x_parambean.OrderDeliverPayQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderQuery;
import com.clown.wyxc.x_comment.CommentActivity;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.clown.wyxc.x_qrcode.QrCodeActivity;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CZP on 2016/7/24.
 * 订单商品信息
 */
public class OrderListFragment extends BaseFragment implements OrderListContract.View {


    @Bind(R.id.icon_store)
    ImageView iconStore;
    @Bind(R.id.store_name)
    TextView storeName;
    @Bind(R.id.order_number)
    TextView orderNumber;
    @Bind(R.id.buystatus)
    TextView buystatus;
    @Bind(R.id.rv_item)
    RecyclerView rvItem;
    @Bind(R.id.tv_yunfei)
    TextView tvYunfei;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_comment1)
    TextView tvComment1;
    @Bind(R.id.btn_cancelorder)
    Button btnCancelorder;
    @Bind(R.id.btn_pay)
    Button btnPay;
    @Bind(R.id.btn_pingjia)
    Button btnPingjia;
    @Bind(R.id.btn_shouhuo)
    Button btnShouhuo;
    @Bind(R.id.btn_querenwancheng)
    Button btnQuerenwancheng;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_itemservice)
    RecyclerView rvItemservice;
    @Bind(R.id.tv_erweima)
    TextView tvErweima;
    @Bind(R.id.rl_buttons)
    RelativeLayout rlButtons;
    @Bind(R.id.rl_erweima)
    RelativeLayout rlErweima;

    private RecyclerAdapter<OrderItem> adapter;
    private RecyclerAdapter<OrderItemService> adapterservice;
    private OrderInfoDetaliResult msgDetail;
    private OrderListContract.Present mPresent;

    @Override
    public void setPresenter(OrderListContract.Present presenter) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderlist_frag, container, false);
        ButterKnife.bind(this, view);
        try {
            mPresent = new OrderListPresent(this);
            initAdapter();
            initView();
            getData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();
        return fragment;
    }

    public void initView() throws Exception {
        rvItem.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvItem.addItemDecoration((new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build()));
        rvItem.setAdapter(adapter);


        rvItemservice.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvItemservice.addItemDecoration((new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build()));
        rvItemservice.setAdapter(adapterservice);
    }

    public void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<OrderItem>(getActivity(), R.layout.orderlist_item) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, final OrderItem item) {
                if (item.getPic() != null) {
                    helper.setImageUrl(R.id.goods_image, item.getPic());
                }
                if (item.getGoodsName() != null) {
                    helper.setText(R.id.goods_name, item.getGoodsName());
                }
                helper.setText(R.id.goods_price, String.valueOf(item.getGoodsPrice().setScale(2)));
                helper.setText(R.id.goods_count, "X" + item.getGoodsNum());
            }
        };
        adapterservice = new RecyclerAdapter<OrderItemService>(getActivity(), R.layout.orderlist_item) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, final OrderItemService item) {
                if (item.getPic() != null) {
                    helper.setImageUrl(R.id.goods_image, item.getPic());
                }
                if (item.getName() != null) {
                    helper.setText(R.id.goods_name, item.getName());
                }
                helper.setText(R.id.goods_price, String.valueOf(item.getPrice().setScale(2)));
                helper.setText(R.id.goods_count, "X" + item.getBuyNum());
            }
        };
    }

    public void getData() throws Exception {
        Bundle bundle = getArguments();
        msgDetail = (OrderInfoDetaliResult) bundle.getSerializable("MsgOrderInfoDetail");

        if (msgDetail.getSendMode() == 1) {
            storeName.setText(msgDetail.getOrderFirmOrderAddressResult().getMerchant().getName());
        } else {
            storeName.setText(msgDetail.getGoodsShop().getShopName());
        }
        if (msgDetail.getGoodsOrderNO() != null) {
            orderNumber.setText("（订单号" + msgDetail.getGoodsOrderNO() + ")");
        }

        if (msgDetail.getOrderDetailState() != null) {
            buystatus.setText(msgDetail.getOrderDetailState().getStateTitle());
        }

        //运费
        if (msgDetail.getPostage() != null && !"".equals(msgDetail.getPostage())) {
            tvYunfei.setText(getString(R.string.orderllist_yunfei, msgDetail.getPostage()));
        }

        if (msgDetail.getSubtotal() != null) {
            tvPrice.setText(getString(R.string.orderllist_sale_price, msgDetail.getSubtotal()));
        }

        int count = 0;
        if (msgDetail.getOrderItemList() != null) {
            count = count + msgDetail.getOrderItemList().size();
        }

        if (msgDetail.getOrderItemServiceList() != null) {
            count = count + msgDetail.getOrderItemServiceList().size();
        }
//        helper.setText(R.id.tv_comment1, "共" + count + "件商品 合计：");
        tvComment1.setText("共" + count + "件商品 合计：");

        if (msgDetail.getOrderItemList() != null) {
            adapter.replaceAll(msgDetail.getOrderItemList());
        }
        if (msgDetail.getOrderItemServiceList() != null) {
            adapterservice.replaceAll(msgDetail.getOrderItemServiceList());
        }
        getButton();

        if (msgDetail.getErweima() != null) {
            tvErweima.setText("点击显示二维码:" + msgDetail.getErweima());
            rlErweima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap map = new HashMap();
                    map.put(QrCodeActivity.INTANTNAME_STRING, msgDetail.getErweima());
                    IntentUtils.startActivity(getActivity(), QrCodeActivity.class, map);
                }
            });
        }else{
            rlErweima.setVisibility(View.GONE);
        }
    }

    public void getButton() {
        switch (msgDetail.getOrderDetailState().getFlag()) {
            case Constants.ORDER_STATUS_DAIFUKUAN:
                btnCancelorder.setVisibility(View.VISIBLE);
                btnPay.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_STATUS_DAISHOUHUO:
//                btnWuliu.setVisibility(View.VISIBLE);
                btnShouhuo.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_STATUS_DAIPINGJIA:
//                btnWuliu.setVisibility(View.VISIBLE);
//                btnShanchuorder.setVisibility(View.VISIBLE);
                btnPingjia.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_STATUS_YIPINGJIA:
//                btnShanchuorder.setVisibility(View.VISIBLE);
//                btnWuliu.setVisibility(View.VISIBLE);
                break;
            case Constants.ORDER_STATUS_DAIQUEREN:
                break;
            default:
                rlButtons.setVisibility(View.GONE);
                break;
        }
    }

    public void initAction() throws Exception {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Integer> arr = new ArrayList<>();
                arr.add(msgDetail.getOrderId());
                mPresent.orderDeliverPay(GSONUtils.paramToJson(new OrderDeliverPayQuery(user.getId(), arr)));
            }
        });

        btnShouhuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.orderConfirmByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), msgDetail.getOrderId())));
            }
        });

        btnPingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra(CommentActivity.INTENT_ORDERID, msgDetail.getOrderId());
                startActivity(intent);
            }
        });

//        btnShanchuorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAlertDialog(getActivity());
//            }
//        });

//        btnWuliu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), WuliuActivity.class);
//                intent.putExtra(WuliuActivity.INTENTNAME_ORDERID, msgDetail.getOrderId());
//                startActivity(intent);
//            }
//        });

        btnCancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.orderCancelByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), msgDetail.getOrderId())));
            }
        });

    }

    @Override
    public void setOrderConfirmByOrderIdResult(int result) {
        T.showShort(getContext(), "交易完成");
        getActivity().finish();
    }

    @Override
    public void setOrderCancelByOrderIdResult(int result) {
        T.showShort(getContext(), "取消成功");
        getActivity().finish();
    }

    @Override
    public void setOrderDeliverPayResult(String result) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PayOrderActivity.INTENTNAME_ORDERNO, result);
        IntentUtils.startActivity(getActivity(), PayOrderActivity.class, map);
    }

    public void showAlertDialog(Context context) {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ;
            builder.setMessage("是否取消订单");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPresent.orderCancelByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), msgDetail.getOrderId())));
                }
            });

            builder.create();
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
