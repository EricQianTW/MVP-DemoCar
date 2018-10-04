package com.clown.wyxc.x_companydetail.goodsinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.GoodsDetailInfo;
import com.clown.wyxc.components.convenientbanner.NetworkImageHolderView;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Banner;
import com.clown.wyxc.x_bean.MerchantResult;
import com.clown.wyxc.x_bean.x_parambean.MerchantQueryId;
import com.clown.wyxc.x_callphone.CallPhoneActivity;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.clown.wyxc.x_map.activity.ReGeocoderActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_Info extends BaseFragment implements GoodsDetailContract_Info.View {
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.goods_share)
    ImageView goodsShare;
    @Bind(R.id.goods_name)
    TextView goodsName;
    @Bind(R.id.line1)
    View line1;
    @Bind(R.id.tv_leixing)
    TextView tvLeixing;
    @Bind(R.id.tv_pinfen)
    TextView tvPinfen;
    @Bind(R.id.tv_turnOver)
    TextView tvTurnOver;
    @Bind(R.id.line2)
    View line2;
    @Bind(R.id.iv_shoucang)
    ImageView ivShoucang;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.tv_address)
    TextView tvAddress;

    private GoodsDetailContract_Info.Presenter mPresenter;
    private GoodsDetailInfo info = new GoodsDetailInfo();
    private int goodsId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_info, container, false);
        ButterKnife.bind(this, view);

        try {
            initView();

            initAction();

            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initAction() {
        ivShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cilckCollection(GSONUtils.paramToJson(new MerchantQueryId(goodsId, null, user.getId())));
            }
        });

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                String totalAddress = tvAddress.getText().toString();
                map.put(ReGeocoderActivity.INTANTNAME_ADDRESS, totalAddress);
                IntentUtils.startActivity(getActivity(), ReGeocoderActivity.class, map);
            }
        });
    }

    private void initView() throws Exception {

    }

    private void initData() throws Exception {
        Bundle data = getArguments();
        goodsId = data.getInt(CompanyDetailActivity.INTENTNAME_COMPANYID);

        mPresenter.getMerchantById(GSONUtils.paramToJson(new MerchantQueryId(goodsId, null, user.getId())));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width);
        convenientBanner.setLayoutParams(layoutParams);
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

    public GoodsDetailFragment_Info() {

    }

    public static GoodsDetailFragment_Info newInstance() {
        return new GoodsDetailFragment_Info();
    }

    @Override
    public void setPresenter(GoodsDetailContract_Info.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetMerchantByIdResult(MerchantResult result) {
        try {
            setBannerInfo(result);

            setCompanyInfo(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCilckCollectionResult(int result) {
        if (result == 0) {
            ivShoucang.setImageResource(R.drawable.icon_start_gray);
            T.showShort(getContext(), "取消收藏");
        } else if (result == 1) {
            ivShoucang.setImageResource(R.drawable.icon_start_red);
            T.showShort(getContext(), "收藏成功");
        }
    }

    private void setCompanyInfo(final MerchantResult result) throws Exception {
        if (result.getName() != null) {
            goodsName.setText(result.getName());
        } else {
            goodsName.setText("");
        }
        if (result.getPoint() != null) {
            tvPinfen.setText(String.valueOf(result.getPoint().intValue()) + "分");
        } else {
            tvPinfen.setText("");
        }
        if (result.getTypeName() != null) {
            tvLeixing.setText(result.getTypeName());
        } else {
            tvLeixing.setText("未分类");
        }
        if (result.getDealCount() != null) {
            tvTurnOver.setText(String.valueOf(result.getDealCount()) + "笔");
        } else {
            tvTurnOver.setText("成交0笔");
        }
        if (result.getAddress() != null) {
            tvAddress.setText(result.getProvName() + result.getCityName() + result.getRegionName() + result.getAddress());
        } else {
            tvAddress.setText("");
        }
        if (result.getIsCollection() == null || result.getIsCollection() == 0) {
            ivShoucang.setImageResource(R.drawable.icon_start_gray);
        } else {
            ivShoucang.setImageResource(R.drawable.icon_start_red);
        }

        goodsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(CallPhoneActivity.INTENTNAME_PHONENUM, result.getTel());
                IntentUtils.startActivity(getActivity(), CallPhoneActivity.class, map);
            }
        });
    }

    private void setBannerInfo(MerchantResult result) throws Exception {
        List<String> strArray = new ArrayList<>();
        if (result != null && result.getMerchantPics() != null) {
            for (Banner temp : result.getMerchantPics()) {
                strArray.add(temp.getPic());
            }
            convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
                @Override
                public NetworkImageHolderView createHolder() {
                    return new NetworkImageHolderView();
                }
            }, strArray)
                    .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                        }
                    });
        }
    }
}
