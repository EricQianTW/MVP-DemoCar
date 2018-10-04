package com.clown.wyxc.x_shopmall.campaingoods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgActivtiySale;
import com.clown.wyxc.bean.MsgSaleInfo;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.html.HtmlActivity;
import com.clown.wyxc.utils.DateUtils;
import com.clown.wyxc.utils.DensityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_CampaignGoods extends BaseFragment implements HomeContract_CampaignGoods.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_hour)
    TextView tvHour;
    @Bind(R.id.tv_minute)
    TextView tvMinute;
    @Bind(R.id.tv_second)
    TextView tvSecond;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.rl_act)
    RelativeLayout rlAct;

    private HomeContract_CampaignGoods.Presenter mPresenter;
    private RecyclerAdapter<MsgSaleInfo> adapter;
    private MsgActivtiySale mInfo;
    private MyCountDownTimer mc;
    private int width;

    public final static String INTENTNAME_MSGACTIVTIYSALE = "msgactivtiysale";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_campaigngoods, container, false);
        ButterKnife.bind(this, view);
        getWidth();
        try {
            Bundle data = getArguments();
            mInfo = GSONUtils.fromJson(data.getString(INTENTNAME_MSGACTIVTIYSALE, "").toString(), MsgActivtiySale.class);
            initAdapter();
            initViews();
            initData();
            //initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public void getWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        //mPresenter.start();
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

    public HomeFragment_CampaignGoods() {
    }

    private void initViews() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_6)
                .build());
        rvIcon.setAdapter(adapter);

        if (mInfo.getStartTime() != null && !"".equals(mInfo.getStartTime())) {
            Date startDate = DateUtils.parseDate(mInfo.getStartTime());
            Date endDate = DateUtils.parseDate(mInfo.getEndTime());
            Date now = new Date();
            if (startDate.getTime() > now.getTime()) {
                mc = new MyCountDownTimer(startDate.getTime() - now.getTime(), 1000);
                mc.start();
                //title.setText("整点限时抢购");
            } else if (startDate.getTime() <= now.getTime() && now.getTime() <= endDate.getTime()) {
                mc = new MyCountDownTimer(endDate.getTime() - now.getTime(), 1000);
                mc.start();
                //title.setText("距离活动结束");
            } else if (now.getTime() > endDate.getTime()) {
                //title.setText("活动已结束");
            }
            title.setText(mInfo.getTitle());
            initAction();

        }
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

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MsgSaleInfo>(getContext(), R.layout.home_adp_campaigngoods) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MsgSaleInfo info) {
                final int position = helper.getAdapterPosition();
                try {
                    helper.setText(R.id.goods_name, info.getGoodsName()).setText(R.id.goods_price, "￥" + info.getGoodsPrice());
                    if (info.getGoodsImage() != null) {
                        helper.setImageUrl(R.id.goods_image, info.getGoodsImage());
                    }
                    ImageView iv_pic = (ImageView) helper.getItemView().findViewById(R.id.goods_image);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(width / 2 - DensityUtils.dp2px(getActivity(), 8), width / 2 - DensityUtils.dp2px(getActivity(), 8));
                    iv_pic.setLayoutParams(layout);

                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            HashMap<String, String> map = new HashMap();
//                            map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(info.getId()));
//                            map.put(GoodsDetailActivity.INTENTNAME_GOODSSTOCKID, String.valueOf(info.getGoodsStockId()));
//                            map.put(GoodsDetailActivity.INTENTNAME_FROMCODE, "1");
//                            IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void initData() throws Exception {
        adapter.clear();
        adapter.addAll(mInfo.getSaleList());
    }

    public static HomeFragment_CampaignGoods newInstance() {
        return new HomeFragment_CampaignGoods();
    }

    @Override
    public void setPresenter(HomeContract_CampaignGoods.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGoods(List<MsgSaleInfo> array) {
    }


    public void initAction() {

        rlAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("title", mInfo.getTitle());
                intent.putExtra("url", mInfo.getWebLink());
                startActivity(intent);
            }
        });
    }
}
