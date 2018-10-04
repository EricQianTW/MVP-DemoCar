package com.clown.wyxc.x_shopcar;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgShoppingCart;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.GoodsCart;
import com.clown.wyxc.x_bean.ShoppingCartResult;
import com.clown.wyxc.x_bean.x_parambean.DeleteShoppingCartQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderShoppingCartId;
import com.clown.wyxc.x_bean.x_parambean.OrderShoppingCartQuery;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
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
public class ShopCarFragment extends BaseFragment implements ShopCarContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.cb_choice)
    CheckBox cbChoice;
    @Bind(R.id.goods_salvenum)
    TitleTextview goodsSalvenum;
    @Bind(R.id.rl_button)
    RelativeLayout rlButton;
    @Bind(R.id.sf_home)
    SwipeRefreshLayout sfHome;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;

    private ShopCarContract.Presenter mPresenter;
    private RecyclerAdapter<ShoppingCartResult> adapter;
    private List<ShoppingCartResult> mData = new ArrayList<>();

    private BigDecimal totalChoicedMoney = new BigDecimal("0");

    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopcar_frg, container, false);
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        initAction();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() throws Exception {
        dialog.show();
        totalChoicedMoney = new BigDecimal("0");
        goodsSalvenum.setTt_text_back("0.00");

        cbChoice.setChecked(false);

        mPresenter.getShoppingCart(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    public ShopCarFragment() {
        new ShopCarPresenter(this);
    }

    public static ShopCarFragment newInstance() {
        return new ShopCarFragment();
    }

    private void initViews() throws Exception {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("加载中...");

        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<ShoppingCartResult>(getContext(), mData, R.layout.shopcar_adp_stores) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final ShoppingCartResult info) {

                helper.setText(R.id.store_name, info.getGoodsShopName());

                final BaseAdapter goodsAdapter = new BaseListAdapter<GoodsCart>(getActivity(), info.getGoodsCartList()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        try {
                            GoodsCart item = list.get(position);
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(list.get(position), convertView, position);
                        } catch (Exception e) {
                            Logger.e(e, TAG);
                            e.printStackTrace();
                        }
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.shopcar_adp_goods, null);
                    }

                    private void setData(final GoodsCart item, View convertView, final int position) throws Exception {
                        CheckBox cb_choice = ViewHolder.get(convertView, R.id.cb_choice);
                        TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                        TextView goods_price = ViewHolder.get(convertView, R.id.goods_price);
                        TextView goods_oldprice = ViewHolder.get(convertView, R.id.goods_oldprice);
                        TextView goods_comment = ViewHolder.get(convertView, R.id.goods_comment);
                        TextView goods_count = ViewHolder.get(convertView, R.id.goods_count);
                        ImageView goods_image = ViewHolder.get(convertView, R.id.goods_image);
                        ImageView iv_delete = ViewHolder.get(convertView, R.id.iv_delete);
                        SnappingStepper stepper = ViewHolder.get(convertView, R.id.stepper);

                        RelativeLayout status1 = ViewHolder.get(convertView, R.id.goods_status1);
                        RelativeLayout status2 = ViewHolder.get(convertView, R.id.goods_status2);

                        if (info.getStatusCode() == 0) {
                            status1.setVisibility(View.VISIBLE);
                        } else {
                            status2.setVisibility(View.VISIBLE);
                        }

                        goods_name.setText(item.getGoodsName());
                        goods_price.setText("￥" + String.valueOf(BigDecimalUtil.df.format(item.getActualGoodsPrice())));
                        ImageLoader.getInstance().displayImage(item.getActualGoodsPic(), goods_image);
                        cb_choice.setChecked(item.isChecked());
                        stepper.setValue(item.getGoodsNum());
                        stepper.setMaxValue(item.getCanbuyNum());
                        goods_comment.setText(item.getAttrValue());
                        goods_count.setText("X" + String.valueOf(item.getGoodsNum()));
                        goods_oldprice.setText("￥" + String.valueOf(BigDecimalUtil.df.format(item.getOldPrice())));
                        goods_oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                        cb_choice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (!buttonView.isPressed()) {
                                    return;
                                }
                                item.setChecked(isChecked);

                                goodsSalvenum.setTt_text_back(getSum());
                            }
                        });

                        iv_delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<Integer> array = new ArrayList<Integer>();
                                array.add(item.getId());
                                mPresenter.deleteShoppingCart(GSONUtils.paramToJson(new DeleteShoppingCartQuery(user.getId(), array)));
                            }
                        });

                        stepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                            @Override
                            public void onValueChange(View view, int value) {
                                item.setGoodsNum(value);
                                goodsSalvenum.setTt_text_back(getSum());
                            }
                        });
                    }
                };

                helper.setAdapter(R.id.rv_item, goodsAdapter);

                helper.setChecked(R.id.cb_choice, info.isChecked());

                helper.setOnClickListener(R.id.edit_stores, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (info.getStatusCode() == 0) {
                            info.setStatusCode(1);
                            helper.setText(R.id.edit_stores, "完成");
                        } else {
                            info.setStatusCode(0);
                            helper.setText(R.id.edit_stores, "编辑");
                        }
                        goodsAdapter.notifyDataSetChanged();
                    }
                });

                helper.setOnCheckedChangeListener(R.id.cb_choice, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!buttonView.isPressed()) {
                            return;
                        }
                        if (isChecked) {
                            info.setChecked(isChecked);
                        }
                        for (GoodsCart msgGoodsCart : info.getGoodsCartList()) {
                            msgGoodsCart.setChecked(isChecked);
                        }
                        goodsAdapter.notifyDataSetChanged();
                        goodsSalvenum.setTt_text_back(getSum());
                    }
                });
            }
        };
    }

    private String getSum() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (ShoppingCartResult msgShoppingCartOut : mData) {
            for (GoodsCart msgGoodsCart : msgShoppingCartOut.getGoodsCartList()) {
                if (msgGoodsCart.isChecked()) {
                    BigDecimal single = msgGoodsCart.getActualGoodsPrice().multiply(new BigDecimal(msgGoodsCart.getGoodsNum()));
                    bigDecimal = bigDecimal.add(single);
                }
            }
        }
        return String.valueOf(bigDecimal);
    }

    private void initAction() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MsgShoppingCart> msgShoppingCart = new ArrayList<MsgShoppingCart>();
                for (ShoppingCartResult out : mData) {
                    for (GoodsCart cart : out.getGoodsCartList()) {
                        if (cart.isChecked()) {
                            msgShoppingCart.add(new MsgShoppingCart(cart.getActualGoodsId(), cart.getGoodsNum()));
                        }
                    }
                }
                if (msgShoppingCart.size() != 0) {
                    List<OrderShoppingCartId> array = new ArrayList<OrderShoppingCartId>();
                    for (ShoppingCartResult shoppingCartResult : mData) {
                        for (GoodsCart goodsCart : shoppingCartResult.getGoodsCartList()) {
                            if (goodsCart.isChecked()) {
                                OrderShoppingCartId temp = new OrderShoppingCartId();
                                temp.setGoodsNum(goodsCart.getGoodsNum());
                                temp.setShoppingCartId(goodsCart.getId());
                                array.add(temp);
                            }
                        }
                    }
                    mPresenter.addOrderByShoppingCar(GSONUtils.paramToJson(new OrderShoppingCartQuery(user.getId(), array)));
                } else {
                    S.showShort(rlMain, "请选择需要购买的商品");
                }
            }
        });

        cbChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                totalChoicedMoney = new BigDecimal("0");

                for (ShoppingCartResult temp : mData) {
                    temp.setChecked(isChecked);
                    for (GoodsCart goods : temp.getGoodsCartList()) {
                        goods.setChecked(isChecked);
                        if (isChecked) {
                            totalChoicedMoney = totalChoicedMoney.add(goods.getActualGoodsPrice().multiply(new BigDecimal(goods.getGoodsNum())));
                        }
                    }
                }

                goodsSalvenum.setTt_text_back(String.valueOf(totalChoicedMoney));
                adapter.replaceAll(mData);
                adapter.notifyDataSetChanged();
            }
        });

        sfHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    initViews();
                    initData();
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setPresenter(@NonNull ShopCarContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setDeleteShoppingCartResult(int result) {
        S.showShort(rlMain, "删除成功");
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGetShoppingCartResult(List<ShoppingCartResult> result) {
        if (result.size() == 0) {
            adapter.clear();
        } else {
            ivEmpty.setVisibility(View.INVISIBLE);
            mData.clear();
            mData.addAll(result);
            adapter.replaceAll(mData);
        }
        sfHome.setRefreshing(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.hide();
            }
        }, 1000);
    }

    @Override
    public void setAddOrderByShoppingCarResult(String result) {
        HashMap map = new HashMap();
        map.put(FirmOrderActivity.INTENTNAME_ORDERNO, result);
        IntentUtils.startActivity(getContext(), FirmOrderActivity.class, map);
    }
}
