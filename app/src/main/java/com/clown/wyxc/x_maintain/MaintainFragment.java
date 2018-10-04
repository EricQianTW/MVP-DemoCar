package com.clown.wyxc.x_maintain;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.baselibray.material.app.Dialog;
import com.clown.baselibray.material.app.DialogFragment;
import com.clown.baselibray.material.app.SimpleDialog;
import com.clown.baselibray.material.app.ThemeManager;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.AmountView;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.MaintainItemGoodsResult;
import com.clown.wyxc.x_bean.MaintainItemsResult;
import com.clown.wyxc.x_bean.MyCarsDefaultResult;
import com.clown.wyxc.x_bean.MyCarsResult;
import com.clown.wyxc.x_bean.x_parambean.ChangeGoodsQuery;
import com.clown.wyxc.x_bean.x_parambean.MyCarsIdQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderMaintainQuery;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
import com.clown.wyxc.x_maintainall.MaintainAllActivity;
import com.clown.wyxc.x_maintainitems.MaintainItemsActivity;
import com.clown.wyxc.x_mycar.MyCarActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.rv_item;
import static com.clown.wyxc.x_common.Constants.RETURN_CODE_COMMON;
import static com.clown.wyxc.x_common.Constants.RETURN_CODE_COMMON2;
import static com.clown.wyxc.x_common.Constants.RETURN_CODE_COMMON3;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MaintainFragment extends BaseFragment implements MaintainContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.cb_choice)
    CheckBox cbChoice;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.rl_action)
    RelativeLayout rlAction;
    @Bind(R.id.tv_carName)
    TextView tvCarName;
    @Bind(R.id.tv_changeCar)
    TextView tvChangeCar;
    @Bind(R.id.tv_tujian)
    TextView tvTujian;
    @Bind(R.id.tv_shengyu)
    TextView tvShengyu;
    @Bind(R.id.tv_sum)
    TextView tvSum;
    @Bind(R.id.ll_gotoAll)
    LinearLayout llGotoAll;

    private MaintainContract.Presenter mPresenter;

    private RecyclerAdapter<MaintainItemsResult> adapter;
    private List<MaintainItemsResult> mData = new ArrayList<>();
    private List<MaintainItemsResult> mAllData = new ArrayList<>();

    private MaintainItemsResult temp_MaintainItemsResult;
    private MaintainItemGoodsResult temp_MaintainItemGoodsResult;
    private int carsResultId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.maintain_frg, container, false);
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
            initView();
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
        mPresenter.getMyCarsDefaultByUsersId(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MaintainItemsResult>(getContext(), mData, R.layout.maintain_apt) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MaintainItemsResult info) {
                try {
                    final CheckBox checkBox = helper.getView(R.id.cb_choice);
                    checkBox.setText(info.getName());
                    if (info.getIsCheck() == 1) {
                        helper.setVisible(R.id.rv_item, View.VISIBLE);
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                        helper.setVisible(R.id.rv_item, View.GONE);
                    }
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (!buttonView.isPressed()) {
                                return;
                            }
                            if (!isChecked) {
                                cbChoice.setChecked(false);
                                info.setIsCheck(0);
                            } else {
                                boolean flag = true;
                                info.setIsCheck(1);
                                for (MaintainItemsResult maintainItemsResult : mData) {
                                    if (maintainItemsResult.getIsCheck() == 0) {
                                        flag = false;
                                    }
                                }
                                if (flag) {
                                    cbChoice.setChecked(true);
                                }
                            }
                            getSum();
                            setCheckedNumStr(mData);
                            notifyDataSetChanged();
                        }
                    });

                    final BaseAdapter goodsAdapter = new BaseListAdapter<MaintainItemGoodsResult>(getActivity(), info.getList()) {
                        @Override
                        public View bindView(int position, View convertView, ViewGroup parent) {
                            convertView = null;
                            try {
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
                            return mInflater.inflate(R.layout.maintain_apt_sub, null);
                        }

                        private void setData(final MaintainItemGoodsResult item, View convertView, final int position) throws Exception {
                            TextView tv_count = ViewHolder.get(convertView, R.id.tv_count);
                            TextView tv_name = ViewHolder.get(convertView, R.id.tv_name);
                            TextView tv_price = ViewHolder.get(convertView, R.id.tv_price);
                            LinearLayout ll_action = ViewHolder.get(convertView, R.id.ll_action);
                            RelativeLayout rl_main = ViewHolder.get(convertView, R.id.rl_main);
                            TextView tv_modify = ViewHolder.get(convertView, R.id.tv_modify);
                            TextView tv_delete = ViewHolder.get(convertView, R.id.tv_delete);
                            AmountView av_num = ViewHolder.get(convertView, R.id.av_num);

                            tv_price.setText(BigDecimalUtil.df.format(item.getPrice()));
                            tv_count.setText("X " + item.getGoodsNum());
                            tv_name.setText(item.getGoodsName());
                            av_num.setGoods_storage(item.getCanBuyNum());
                            av_num.setAmount(item.getGoodsNum());

                            if (info.isEditer()) {
                                ll_action.setVisibility(View.VISIBLE);
                                tv_count.setVisibility(View.GONE);
                            } else {
                                ll_action.setVisibility(View.GONE);
                                tv_count.setVisibility(View.VISIBLE);
                            }

                            tv_modify.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    HashMap map = new HashMap();
                                    temp_MaintainItemsResult = info;
                                    temp_MaintainItemGoodsResult = item;
                                    map.put(MaintainItemsActivity.INTENTNAME_MAINTAINITEMGOODSID, String.valueOf(item.getMaintainItemGoodsId()));
                                    map.put(MaintainItemsActivity.INTENTNAME_MAINTAINITEMID, String.valueOf(info.getId()));
                                    IntentUtils.startFragmentForResult(MaintainFragment.this, getContext(), MaintainItemsActivity.class, map);
                                }
                            });

                            av_num.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
                                @Override
                                public void onAmountChange(View view, int amount) {
                                    item.setGoodsNum(amount);
                                    getSum();
                                }
                            });

                            rl_main.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    HashMap<String, String> map = new HashMap();
                                    map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(item.getGoodsId()));
                                    IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                                }
                            });

                            tv_delete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Dialog.Builder builder = null;
                                    boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                                    builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog){
                                        @Override
                                        public void onPositiveActionClicked(DialogFragment fragment) {

                                            mData.get(helper.getAdapterPosition()).getList().remove(position);
                                            adapter.notifyDataSetChanged();
                                            super.onPositiveActionClicked(fragment);
                                        }

                                        @Override
                                        public void onNegativeActionClicked(DialogFragment fragment) {
                                            super.onNegativeActionClicked(fragment);
                                        }
                                    };

                                    builder.title("确认删除?")
                                            .positiveAction("确认")
                                            .negativeAction("取消");

                                    DialogFragment fragment = DialogFragment.newInstance(builder);
                                    fragment.show(getFragmentManager(), null);
                                }
                            });
                        }
                    };

                    helper.setAdapter(rv_item, goodsAdapter);

                    helper.setOnClickListener(R.id.tv_action, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (info.getStatusCode() == 0) {
                                info.setStatusCode(1);
                                helper.setText(R.id.tv_action, "完成");

                                info.setEditer(true);
                                notifyDataSetChanged();
                            } else {
                                info.setStatusCode(0);
                                helper.setText(R.id.tv_action, "编辑");

                                info.setEditer(false);
                                notifyDataSetChanged();
                            }
                            goodsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void getSum() {
        BigDecimal sum = new BigDecimal("0");
        for (MaintainItemsResult maintainItemsResult : mData) {
            if (maintainItemsResult.getIsCheck() == 1) {
                for (MaintainItemGoodsResult maintainItemGoodsResult : maintainItemsResult.getList()) {
                    sum = sum.add(maintainItemGoodsResult.getPrice().multiply(new BigDecimal(maintainItemGoodsResult.getGoodsNum())));
                }
            }
        }
        tvSum.setText(BigDecimalUtil.df.format(sum));
    }

    public MaintainFragment() {
        new MaintainPresenter(this);
    }

    public static MaintainFragment newInstance() {
        return new MaintainFragment();
    }

    private void initAction() throws Exception {
        cbChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed()) {
                    return;
                }
                if (isChecked) {
                    for (MaintainItemsResult maintainItemsResult : mData) {
                        maintainItemsResult.setIsCheck(1);
                    }
                } else {
                    for (MaintainItemsResult maintainItemsResult : mData) {
                        maintainItemsResult.setIsCheck(0);
                    }
                }
                setCheckedNumStr(mData);
                adapter.notifyDataSetChanged();
                getSum();
            }
        });
        tvChangeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(MyCarActivity.INTENTNAME_SOURCE, MyCarActivity.INTENTNAME_SOURCE_SELECT);
                IntentUtils.startFragmentForResult(MaintainFragment.this, getContext(), MyCarActivity.class, map);
            }
        });
        llGotoAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();

                for (MaintainItemsResult maintainItemsResult : mData) {
                    if (maintainItemsResult.getIsCheck() == 1) {
                        for (MaintainItemsResult itemsResult : mAllData) {
                            if (itemsResult.getId().equals(maintainItemsResult.getId())) {
                                itemsResult.setIsCheck(1);
                            }
                        }
                    }
                }
                map.put(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL, GSONUtils.toJson(mAllData));

                IntentUtils.startFragmentForResult(MaintainFragment.this, getContext(), MaintainAllActivity.class, map);
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MaintainItemsResult> temp = new ArrayList<MaintainItemsResult>();
                for (MaintainItemsResult maintainItemsResult : mData) {
                    if (maintainItemsResult.getIsCheck() == 1) {
                        temp.add(maintainItemsResult);
                    }
                }
                mPresenter.addOrderByMaintain(GSONUtils.paramToJson(new OrderMaintainQuery(user.getId(),carsResultId,temp)));
            }
        });
    }

    @Override
    public void setPresenter(@NonNull MaintainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetMaintainItemsResult(ArrayList<MaintainItemsResult> result) {
        try {
            setCheckedNumStr(result);

            mData.clear();
            mData.addAll(result);

            TypeToken<List<MaintainItemsResult>> token = new TypeToken<List<MaintainItemsResult>>() {
            };
            List<MaintainItemsResult> dataPackage = GSONUtils.fromJson(GSONUtils.toJson(result), token);
            mAllData.addAll(dataPackage);

            adapter.replaceAll(mData);

            getSum();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCheckedNumStr(List<MaintainItemsResult> result) {
        int checkedNum = 0;
        for (MaintainItemsResult maintainItemsResult : result) {
            if (maintainItemsResult.getIsCheck() == 1) {
                checkedNum++;
            }
        }

        tvTujian.setText("推荐保养 X " + checkedNum);
        tvShengyu.setText("剩余可选项目 X " + (result.size() - checkedNum));
    }

    @Override
    public void setGetMyCarsDefaultByUsersIdResult(MyCarsDefaultResult result) {
        carsResultId = result.getCarId();
        setCarInfo(result.getPpName(), result.getCarId());
    }

    @Override
    public void setMaintainChangeGoodsResult(MaintainItemsResult result) {
        result.setStatusCode(1);
        result.setEditer(true);

        int index = mData.indexOf(temp_MaintainItemsResult);
        mData.remove(temp_MaintainItemsResult);
        mData.add(index,result);
        adapter.replaceAll(mData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setAddOrderByMaintainResult(String result) {
        HashMap map = new HashMap();
        map.put(FirmOrderActivity.INTENTNAME_ORDERNO,result);
        IntentUtils.startActivity(getContext(), FirmOrderActivity.class,map);
    }

    private void setCarInfo(String ppName, int carId) {
        tvCarName.setText(ppName);

        mPresenter.getMaintainItems(GSONUtils.paramToJson(new MyCarsIdQuery(carId, user.getId())));
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        try {
            switch (requestCode) {
                case Activity.RESULT_FIRST_USER:
                    if (resultCode == RETURN_CODE_COMMON) {
                        String carInfoStr = data.getStringExtra(MyCarActivity.INTENTNAME_CARID);
                        MyCarsResult myCarsResult = GSONUtils.fromJson(carInfoStr, MyCarsResult.class);
                        carsResultId = myCarsResult.getCarId();
                        setCarInfo(myCarsResult.getPpName(), myCarsResult.getCarId());
                    } else if (resultCode == RETURN_CODE_COMMON2) {
                        cbChoice.setChecked(false);
                        String maintainAll = data.getStringExtra(MaintainActivity.INTENT_ARGUMENT_MAINTAINALL);
                        TypeToken<List<MaintainItemsResult>> token = new TypeToken<List<MaintainItemsResult>>() {
                        };
                        mData = GSONUtils.fromJson(maintainAll, token);
                        adapter.replaceAll(mData);

                        setCheckedNumStr(mData);

                        getSum();
                    } else if (resultCode == RETURN_CODE_COMMON3) {
                        int goodsId = Integer.parseInt(data.getStringExtra(MaintainActivity.INTENT_ARGUMENT_GOODSID));
                        mPresenter.maintainChangeGoods(GSONUtils.paramToJson(new ChangeGoodsQuery(user.getId(), temp_MaintainItemsResult, temp_MaintainItemGoodsResult.getMaintainItemGoodsId(), goodsId)));
                    }

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}