package com.clown.wyxc.x_myorder;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.TabEntity;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
import com.clown.wyxc.x_bean.OrderItem;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.cb_select;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MyOrderFragment extends BaseFragment implements MyOrderContract.View {

    @Bind(R.id.tabs)
    CommonTabLayout tabs;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.btn_hebing)
    Button btnHebing;
    @Bind(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    private MyOrderContract.Presenter mPresenter;
    private RecyclerAdapter<OrderInfoDetaliResult> adapter;
    private String[] mTitles = {"全部", "待支付", "待发货", "待收货", "待评价"};
    private int[] mIds = {-1, 1, 2, 4, 5};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int pagenum = 1;
    private boolean loadfalg = false;
    private int flag = -1;
    private List<OrderInfoDetaliResult> array = new ArrayList<OrderInfoDetaliResult>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myorder_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            initAdapter();
            initViews(view);

            initData(mIds[0]);
            tabs.setCurrentTab(getPagePosition());
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            array.clear();
            tvPrice.setText("0.00");
            initData(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (flag != -1) {
//            loadfalg = false;
//            mPresenter.getOrderInfoByUserId(user.getVerify(), user.getUserId(), flag, pagenum, -1);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData(int id) throws Exception {
        loadfalg = false;
//        mPresenter.getOrderInfoByUserId(user.getVerify(), user.getUserId(), id, pagenum, -1);
    }

    public MyOrderFragment() {
        new MyOrderPresenter(this);
    }

    public static MyOrderFragment newInstance() {
        return new MyOrderFragment();
    }

    private void initAction() {
        tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                try {
                    ivEmpty.setVisibility(View.VISIBLE);
                    rvIcon.setVisibility(View.INVISIBLE);
                    adapter.clear();
                    flag = mIds[position];
                    initData(mIds[position]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

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
//                                mPresenter.getOrderInfoByUserId(user.getVerify(), user.getUserId(), flag, pagenum, -1);
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

        btnHebing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> arraylist = new ArrayList<Integer>();
                for (int i = 0; i < array.size(); i++) {
                    arraylist.add(array.get(i).getOrderId());
                }
//                mPresenter.repayOrderById(user.getVerify(), user.getUserId(),arraylist);
            }
        });
    }

    @Override
    public void setPresenter(@NonNull MyOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initViews(View view) throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        tabs.setTabData(mTabEntities);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<OrderInfoDetaliResult>(getContext(), R.layout.myorder_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final OrderInfoDetaliResult info) {
                final int position = helper.getAdapterPosition();

                helper.setText(R.id.store_name, info.getGoodsShop().getShopName());
                helper.setText(R.id.order_number, "（订单号:" + info.getGoodsOrderNO() + ")");
                if (info.getOrderType() == 1) {
                    helper.setText(R.id.buystatus, "待付款");
                } else if (info.getOrderType() == 2) {
                    helper.setText(R.id.buystatus, "待发货");
                } else if (info.getOrderType() == 4) {
                    helper.setText(R.id.buystatus, "待收货");
                } else if (info.getOrderType() == 5) {
                    helper.setText(R.id.buystatus, "待评价");
                } else if (info.getOrderType() == 6) {
                    helper.setText(R.id.buystatus, "已完成");
                }

                if (tabs.getCurrentTab() == 1) {
                    helper.setVisible(cb_select, View.VISIBLE);
                } else {
                    helper.setVisible(cb_select, View.GONE);
                }
                helper.setText(R.id.tv_comment1, "共" + info.getOrderItemList().size() + "件商品 合计：");
                helper.setText(R.id.tv_comment2, "￥" + info.getSubtotal());
                helper.setText(R.id.tv_comment3, " (" + info.getPostage() + ") ");

                helper.setChecked(cb_select,false);

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
//                                Intent intent = new Intent(getActivity(), OrderInfoActivity.class);
//                                intent.putExtra("Id", info.getId());
//                                startActivity(intent);
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
                        TextView goods_oldprice = ViewHolder.get(convertView, R.id.goods_oldprice);
                        TextView goods_attr = ViewHolder.get(convertView, R.id.goods_attr);
                        //goods_name.setText(FontUtils.ToSBC(item.getGoodsName()));
                        goods_name.setText(item.getGoodsName());
                        goods_count.setText("X" + item.getGoodsNum());
                        goods_oldprice.setText("￥" + BigDecimalUtil.df.format(item.getOldPrice()));
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

                RelativeLayout rl_group = (RelativeLayout) helper.getItemView().findViewById(R.id.btn_group);
                View lines = helper.getItemView().findViewById(R.id.lines);
                TextView btn_cancelorder = (TextView) helper.getItemView().findViewById(R.id.btn_cancelorder);
                TextView btn_pingjia = (TextView) helper.getItemView().findViewById(R.id.btn_pingjia);
                TextView btn_payorder = (TextView) helper.getItemView().findViewById(R.id.btn_payorder);
                TextView btn_surereceive = (TextView) helper.getItemView().findViewById(R.id.btn_surereceive);
                TextView btn_wuliu = (TextView) helper.getItemView().findViewById(R.id.btn_searchwuliu);
                if (info.getOrderType() == 1) {
                    btn_cancelorder.setVisibility(View.VISIBLE);
                    btn_pingjia.setVisibility(View.GONE);
                    btn_surereceive.setVisibility(View.GONE);
                    btn_payorder.setVisibility(View.VISIBLE);
                    rl_group.setVisibility(View.VISIBLE);
                    btn_wuliu.setVisibility(View.GONE);
                    lines.setVisibility(View.VISIBLE);
                } else if (info.getOrderType() == 2) {
                    btn_cancelorder.setVisibility(View.GONE);
                    btn_surereceive.setVisibility(View.GONE);
                    btn_payorder.setVisibility(View.GONE);
                    btn_pingjia.setVisibility(View.GONE);
                    rl_group.setVisibility(View.GONE);
                    btn_wuliu.setVisibility(View.GONE);
                    lines.setVisibility(View.GONE);
                } else if (info.getOrderType() == 4) {
                    btn_cancelorder.setVisibility(View.GONE);
                    btn_payorder.setVisibility(View.GONE);
                    btn_pingjia.setVisibility(View.GONE);
                    btn_surereceive.setVisibility(View.VISIBLE);
                    rl_group.setVisibility(View.VISIBLE);
                    btn_wuliu.setVisibility(View.VISIBLE);

                    lines.setVisibility(View.VISIBLE);
                } else if (info.getOrderType() == 5) {
                    btn_cancelorder.setVisibility(View.GONE);
                    btn_pingjia.setVisibility(View.VISIBLE);
                    btn_surereceive.setVisibility(View.GONE);
                    btn_payorder.setVisibility(View.GONE);
                    rl_group.setVisibility(View.VISIBLE);
                    btn_wuliu.setVisibility(View.VISIBLE);
                    lines.setVisibility(View.VISIBLE);
                } else if (info.getOrderType() == 6) {
                    btn_pingjia.setVisibility(View.GONE);
                    btn_surereceive.setVisibility(View.GONE);
                    btn_payorder.setVisibility(View.GONE);
                    rl_group.setVisibility(View.VISIBLE);
                    btn_wuliu.setVisibility(View.GONE);
                    lines.setVisibility(View.VISIBLE);
                }

                helper.setOnClickListener(R.id.btn_cancelorder, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAlertDialog(getActivity(), position, info.getOrderId());
//                        List<Integer> array = new ArrayList<Integer>();
//                        array.add(info.getId());
//                        mPresenter.cancelOrder(user.getVerify(), user.getUserId(), array, position);
                    }
                });

                helper.setOnClickListener(R.id.btn_pingjia, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getActivity(), CommentActivity.class);
//                        intent.putExtra("Id", info.getId());
//                        startActivity(intent);
                    }
                });

                helper.setOnClickListener(R.id.btn_payorder, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Integer> array1 = new ArrayList<Integer>();
                        array1.add(info.getOrderId());
//                        mPresenter.repayOrderById(user.getVerify(), user.getUserId(), array1);
                    }
                });

                helper.setOnCheckedChangeListener(cb_select, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isChecked()) {
                            array.add(info);
                            rlBottom.setVisibility(View.VISIBLE);
                            tvPrice.setText(jisuanprice(array));
                        } else {
                            if (!array.isEmpty()) {
                                array.remove(info);
                                tvPrice.setText(jisuanprice(array));
                            }
                        }

                        if (array.isEmpty()) {
                            rlBottom.setVisibility(View.GONE);
                        }
                    }
                });

                helper.setOnClickListener(R.id.btn_surereceive, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Integer> intlist = new ArrayList<Integer>();
//                        mPresenter.receiptOrder(user.getVerify(), user.getUserId(), intlist, position);
                    }
                });

                helper.setOnClickListener(R.id.btn_searchwuliu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuilder builder = new StringBuilder("http://meiye.tonggo.net/ordercenter/indexExpressMain.aspx?OrderId=");
                        builder.append(info.getOrderId());
                        IntentUtils.startSchemeIntent(getActivity(), builder.toString());
                    }
                });
            }
        };
    }

    @Override
    public void setGetOrderListByOrderStateResult(List<OrderInfoDetaliResult> result) {
        try {
            if (result.size() != 0) {
                rvIcon.setVisibility(View.VISIBLE);
                ivEmpty.setVisibility(View.INVISIBLE);
                adapter.replaceAll(result);
//                adapter.addAll(result);
            }
            loadfalg = true;
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    public int getPagePosition() {
        Bundle bundle = getArguments();
        int temp = bundle.getInt(MyOrderActivity.INTENT_ORDERSTATE, 0);
        if (temp == 0) {
            return 0;
        } else {
            for (int i = 0; i < mIds.length; i++) {
                if (temp == mIds[i]) {
                    return i;
                }
            }
            return 0;
        }

    }

    public void showAlertDialog(Context context, final int position, final int id) {
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
                    List<Integer> array = new ArrayList<Integer>();
                    array.add(id);
//                    mPresenter.cancelOrder(user.getVerify(), user.getUserId(), array, position);
                }
            });

            builder.create();
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String jisuanprice(List<OrderInfoDetaliResult> list){
        BigDecimal bigDecimal = new BigDecimal("0.00");
        for (int i = 0; i < list.size(); i++) {
            bigDecimal = bigDecimal.add(list.get(i).getSubtotal());
        }
        return bigDecimal.toString();
    }

}
