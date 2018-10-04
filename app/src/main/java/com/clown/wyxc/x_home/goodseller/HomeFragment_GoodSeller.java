package com.clown.wyxc.x_home.goodseller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.MerchantResult;
import com.clown.wyxc.x_bean.x_parambean.MerchantQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_GoodSeller extends BaseFragment implements HomeContract_GoodSeller.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_more)
    TextView tvMore;

    private HomeContract_GoodSeller.Presenter mPresenter;
    private RecyclerAdapter<MerchantResult> adapter;
    private int pageindex = 1;

    private boolean loading = false;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_goodseller, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            pageindex = 1;

            initAdapter();

            initViews();

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

    public HomeFragment_GoodSeller() {

    }

    public static HomeFragment_GoodSeller newInstance() {
        return new HomeFragment_GoodSeller();
    }

    @Override
    public void setPresenter(HomeContract_GoodSeller.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initViews() throws Exception {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("加载中...");
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
        adapter = new RecyclerAdapter<MerchantResult>(getContext(), R.layout.commercial_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MerchantResult info) {
                try {
                    helper.setText(R.id.seller_distance, String.valueOf(info.getDistance()) + "km");

                    if (info.getTypeName() == null || "".equals(info.getTypeName())) {
                        helper.setVisible(R.id.seller_leixing, View.GONE);
                    } else {
                        helper.setVisible(R.id.seller_leixing, View.VISIBLE);
                        helper.setText(R.id.seller_leixing, info.getTypeName());
                    }

                    helper.setText(R.id.seller_city, info.getCityName())
                            .setText(R.id.seller_name, info.getName())
                            .setText(R.id.seller_address, info.getAddress())
                            .setText(R.id.seller_pingfen, String.valueOf(info.getPoint().intValue()) + "分")
                            .getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            map.put(CompanyDetailActivity.INTENTNAME_COMPANYID, String.valueOf(info.getId()));
                            IntentUtils.startActivity(getContext(), CompanyDetailActivity.class, map);
                        }
                    });

                    if (info.getMerchantPics() != null && info.getMerchantPics().size() > 0 && info.getMerchantPics().get(0).getPic() != null) {
                        helper.setImageUrl(R.id.seller_img, info.getMerchantPics().get(0).getPic());
                    }
                    helper.getItemView().setTag("hello world");
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        };
    }

    public void initData() throws Exception {
        if (!loading) {
            loading = true;
            progressDialog.show();
            mPresenter.getMerchantListByQuery(GSONUtils.toJson(new MerchantQuery(Constants.PAIXU_TYPE_ZHINENG,
                    BigDecimal.valueOf(aMapLocation.getLongitude())
                    , BigDecimal.valueOf(aMapLocation.getLatitude())
                    , null, aMapLocation.getAdCode()
                    , pageindex, user.getId(), null)));
        }
    }

    @Override
    public void setGetMerchantListByQueryResult(List<MerchantResult> result) {
        try {
            tvMore.setVisibility(View.VISIBLE);
            if (result.size() > 0) {
                adapter.addAll(result);
                pageindex++;
                tvMore.setText("上拉加载更多");
            } else {
                tvMore.setText("已经全部加载完毕");
                T.showShort(getContext(), "没有更多的数据了");
            }
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            loading = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvMore.setVisibility(View.GONE);
                }
            },2000);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}
