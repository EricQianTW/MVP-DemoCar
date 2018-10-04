package com.clown.wyxc.x_shopmallgoodsdetail.goodsplaceorder;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseBottomSheetFragment;
import com.clown.wyxc.bean.MsgShoppingCart;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.ActualGoodsListResult;
import com.clown.wyxc.x_bean.GoodsAttrAttr;
import com.clown.wyxc.x_bean.GoodsAttrResult;
import com.clown.wyxc.x_bean.GoodsInfoResult;
import com.clown.wyxc.x_bean.x_parambean.AddShoppingCartQuery;
import com.clown.wyxc.x_bean.x_parambean.GoodsIdQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderGoodsDetailQuery;
import com.clown.wyxc.x_firmorder.FirmOrderActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_PlaceOrder extends BaseBottomSheetFragment implements GoodsDetailContract_PlaceOrder.View {

    @Bind(R.id.goods_photo)
    ImageView goodsPhoto;
    @Bind(R.id.listview)
    ListView mListView;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.goods_price)
    TextView goodsPrice;
    @Bind(R.id.goods_stock)
    TitleTextview goodsStock;
    @Bind(R.id.ll_taggroup)
    LinearLayout llTaggroup;
    @Bind(R.id.tv_stpper)
    TextView tvStpper;
    @Bind(R.id.stepper)
    SnappingStepper stepper;
    @Bind(R.id.goods_count)
    LinearLayout goodsCount;
    @Bind(R.id.btn_addcar)
    TextView btnAddcar;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.goods_oldprice)
    TextView goodsOldprice;
    @Bind(R.id.tv_title)
    TextView title;
    @Bind(R.id.tv_hour)
    TextView tvHour;
    @Bind(R.id.tv_minute)
    TextView tvMinute;
    @Bind(R.id.tv_second)
    TextView tvSecond;
    @Bind(R.id.daojishi)
    RelativeLayout daojishi;

    private GoodsDetailContract_PlaceOrder.Presenter mPresenter;

    private List<GoodsAttrResult> mDatas = new ArrayList<GoodsAttrResult>();
    private List<ActualGoodsListResult> mStock = new ArrayList<ActualGoodsListResult>();
    private Map<String, String> choiceGoods = new HashMap<String, String>();
    private String lastChoiceId = "";
    private CommonAdapter adapter;
    private int goodsId;
    //    private int fromcode;
    private int goodsStockId = -1;
    private Map<Integer, Set<Integer>> selectedMap = new HashMap<>();
    private MyCountDownTimer mc;
    private int count = 0;
//    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_placeorder, container, false);
        ButterKnife.bind(this, view);
        try {

            Bundle data = getArguments();

            goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);
//            fromcode = data.getInt(GoodsDetailActivity.INTENTNAME_FROMCODE, 0);
            goodsStockId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSSTOCKID, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
//            dialog = new ProgressDialog(getContext());
//            dialog.setMessage("加载中...");

            initListView();
            initAction();
            initData();


        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 继承 CountDownTimer 防范
     * <p/>
     * 重写 父类的方法 onTick() 、 onFinish()
     */
    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture    表示以毫秒为单位 倒计时的总数
         *                          <p/>
         *                          例如 millisInFuture=1000 表示1秒
         * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
         *                          <p/>
         *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            tvHour.setText("00");
            tvMinute.setText("00");
            tvSecond.setText("00");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            try {
                long sss = millisUntilFinished / 1000;
                String hour = String.valueOf(sss / (60 * 60));
                if ("0".equals(hour)) {
                    tvHour.setText("00");
                } else {
                    tvHour.setText(hour);
                }
                long temp1 = sss % (60 * 60);
                String minute = String.valueOf(temp1 / 60);
                if ("0".equals(minute)) {
                    tvMinute.setText("00");
                } else {
                    tvMinute.setText(minute);
                }

                tvSecond.setText(String.valueOf(temp1 % 60));
            } catch (Exception e) {
                Logger.e(e, TAG);
                e.printStackTrace();
            }
        }
    }

    private void initAction() throws Exception {
        btnAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkValue()) {
                        return;
                    }

                    mPresenter.addShoppingCart(GSONUtils.paramToJson(new AddShoppingCartQuery(user.getId(), goodsStockId, stepper.getValue())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<MsgShoppingCart> list = new ArrayList<MsgShoppingCart>();
                    MsgShoppingCart info = new MsgShoppingCart(goodsStockId, stepper.getValue());
                    list.add(info);

                    // TODO 调用添加订单接口
                    mPresenter.addOrderByGoodsDetail(GSONUtils.paramToJson(new OrderGoodsDetailQuery(user.getId(), goodsStockId, stepper.getValue())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkValue() {
        if (goodsStockId == -1) {
            S.showShort(rlMain, "请选择属性");
            return true;
        }
        if (stepper.getValue() == 0) {
            S.showShort(rlMain, "请选择数量");
            return true;
        }
        return false;
    }

    private void initListView() throws Exception {
        adapter = new CommonAdapter<GoodsAttrResult>(getActivity(), R.layout.goodsdetail_adp_listview, mDatas) {
            @Override
            public void convert(final ViewHolder viewHolder, final GoodsAttrResult strings) {
                TagFlowLayout tagFlowLayout = viewHolder.getView(R.id.id_tagflowlayout);
                TextView textview = viewHolder.getView(R.id.textview);
                textview.setText(strings.getAttrName());

                TagAdapter<GoodsAttrAttr> tagAdapter = new TagAdapter<GoodsAttrAttr>(strings.getAttrList()) {
                    @Override
                    public View getView(FlowLayout parent, int position, GoodsAttrAttr o) {
                        //can use viewholder
                        TextView tv = (TextView) mInflater.inflate(R.layout.common_zhy_tv,
                                parent, false);
                        if (o.isNone()) {
                            tv.setClickable(true);
                            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.gray_ce));
                        }

                        tv.setText(o.getAttrName());
                        tv.setTag(strings.getId() + "," + o.getId());
                        return tv;
                    }

                };
                tagFlowLayout.setAdapter(tagAdapter);
                tagAdapter.setSelectedList(selectedMap.get(viewHolder.getItemPosition()));

                tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        selectedMap.put(viewHolder.getItemPosition(), selectPosSet);
                    }
                });

                tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        try {
                            TextView tv = (TextView) view;

                            String classId = tv.getTag().toString().split(",")[0];
                            String itemId = tv.getTag().toString().split(",")[1];

                            if (!itemId.equals(choiceGoods.get(classId))) {
                                choiceGoods.put(classId, itemId);
                                lastChoiceId = classId;
                            } else {
                                choiceGoods.remove(classId);
                                lastChoiceId = "";
                            }

                            List<ActualGoodsListResult> tempStock = new ArrayList<ActualGoodsListResult>();

                            for (int i = 0; i < mStock.size(); i++) {
                                boolean flag = true;
                                Iterator iter = choiceGoods.entrySet().iterator();
                                if (iter.hasNext()) {
                                    while (iter.hasNext()) {
                                        Map.Entry entry = (Map.Entry) iter.next();
                                        Object key = entry.getKey();
                                        Object val = entry.getValue();
                                        if (Integer.parseInt(String.valueOf(val)) == mStock.get(i).getGoodsAttrDictionary().get(key)) {

                                        } else {
                                            flag = false;
                                        }
                                    }
                                    if (flag) {
                                        tempStock.add(mStock.get(i));
                                    }
                                } else {
                                    tempStock.add(mStock.get(i));
                                }

                            }

                            if (tempStock.size() == 1) {
                                ActualGoodsListResult onlyone = tempStock.get(0);
                                setGoodsInfo(onlyone);
                            }
                            createIcons(tempStock);
                        } catch (Exception e) {
                            Logger.e(e, TAG);
                            e.printStackTrace();
                        }

                        return false;
                    }
                });
            }
        };
        mListView.setAdapter(adapter);
    }

    private void setGoodsInfo(ActualGoodsListResult onlyone) throws Exception {
        goodsStockId = onlyone.getId();
        if (onlyone.getPrice() != null) {
            goodsPrice.setText(String.valueOf(onlyone.getPrice()));
        }
        if (onlyone.getCanBuyNum() != null) {
            goodsStock.setTt_text_back(String.valueOf(onlyone.getCanBuyNum()));
            stepper.setMaxValue(onlyone.getCanBuyNum());
        }
        if (onlyone.getOldPrice() != null) {
            goodsOldprice.setText(String.valueOf(BigDecimalUtil.df.format(onlyone.getOldPrice())));
            goodsOldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            goodsOldprice.setVisibility(View.VISIBLE);
        }
        if (goodsPhoto != null) {
            ImageLoader.getInstance().displayImage(onlyone.getPic(), goodsPhoto);
        }
    }

    private void initData() throws Exception {

//        dialog.show();

        mPresenter.getGoodsAttrByGoodsId(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(), goodsId, aMapLocation.getAdCode())));

//        mPresenter.getGoodsById(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(), goodsId, aMapLocation.getAdCode())));
    }

    public GoodsDetailFragment_PlaceOrder() {

    }

    public static GoodsDetailFragment_PlaceOrder newInstance() {
        return new GoodsDetailFragment_PlaceOrder();
    }

    @Override
    public void setPresenter(GoodsDetailContract_PlaceOrder.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void createIcons(List<ActualGoodsListResult> attrList) {
        for (int i = 0; i < mDatas.size(); i++) {
            String key = String.valueOf(mDatas.get(i).getId());
            if (!key.equals(lastChoiceId)) {
                for (int j = 0; j < mDatas.get(i).getAttrList().size(); j++) {
                    int value = mDatas.get(i).getAttrList().get(j).getId();
                    if (attrList.size() > 0) {
                        for (int k = 0; k < attrList.size(); k++) {
                            if (value == attrList.get(k).getGoodsAttrDictionary().get(key)) {
                                mDatas.get(i).getAttrList().get(j).setNone(false);
                                break;
                            } else {
                                mDatas.get(i).getAttrList().get(j).setNone(true);
                                if (count == 2 && selectedMap.containsKey(i)) {
                                    selectedMap.remove(i);
                                }
                            }
                        }
                    } else {
                        mDatas.get(i).getAttrList().get(j).setNone(true);
                        if (count == 2 && selectedMap.containsKey(i)) {
                            selectedMap.remove(i);
                        }
                    }
                }
            }
        }

        count++;

        adapter.notifyDataSetChanged();
    }

    private void setChoice(List<ActualGoodsListResult> attrList) {
        if (attrList.size() > 0 && count == 1) {
            ActualGoodsListResult temp = attrList.get(0);
            HashMap<String, Integer> hash = temp.getGoodsAttrDictionary();
            for (int i = 0; i < mDatas.size(); i++) {
                String key = String.valueOf(mDatas.get(i).getId());
                if (!key.equals(lastChoiceId)) {
                    for (int j = 0; j < mDatas.get(i).getAttrList().size(); j++) {
                        int value = mDatas.get(i).getAttrList().get(j).getId();

                        if (value == hash.get(key)) {
                            Set<Integer> set = new HashSet<Integer>();
                            set.add(j);
                            selectedMap.put(i, set);
                            break;
                        } else {

                        }
                    }
                }
            }
        }
    }


    @Override
    public void setGetActualGoodsByGoodsIdResult(List<ActualGoodsListResult> result) {
        try {
//            dialog.dismiss();
            if (mDatas.size() == 0 && result.size() == 1) {
                setGoodsInfo(result.get(0));
            } else if (mDatas.size() > 0 && result.size() > 0) {
                mStock = result;
                createIcons(result);
                setChoice(result);
                setGoodsInfo(result.get(0));
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setGetGoodsAttrByGoodsIdResult(List<GoodsAttrResult> result) {
        mDatas.clear();
        mDatas.addAll(result);
        adapter.notifyDataSetChanged();


        mPresenter.getActualGoodsByGoodsId(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(), goodsId, aMapLocation.getAdCode())));
    }

    @Override
    public void setGetGoodsByIdResult(GoodsInfoResult result) {
    }

    @Override
    public void setAddShoppingCartResult(int result) {
        S.showShort(rlMain, "已加入购物车");
    }

    @Override
    public void setAddOrderByGoodsDetailResult(String result) {
        HashMap map = new HashMap();
        map.put(FirmOrderActivity.INTENTNAME_ORDERNO, result);
        IntentUtils.startActivity(getContext(), FirmOrderActivity.class, map);
    }
}
