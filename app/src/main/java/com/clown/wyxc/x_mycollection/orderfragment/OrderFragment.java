package com.clown.wyxc.x_mycollection.orderfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.MerchantResult;
import com.clown.wyxc.x_bean.x_parambean.QueryUserIdPage;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.clown.wyxc.x_mycollection.MyCollectionsFragment;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class OrderFragment extends BaseFragment implements OrderContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_nodata)
    TextView tvNodata;
    @Bind(R.id.ll_main)
    FrameLayout llMain;
    private RecyclerAdapter<MerchantResult> adapter;
    private RecyclerAdapter<GoodsResult> adapterGoods;

    private String mResult;

    private int pageIndex = 1;
    private int pageGoodsIndex = 0;

    private OrderContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simplecard_frg, container, false);
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
            initMerchantAdapter();
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
        if (MyCollectionsFragment.newInstance().mTitles[0].equals(mResult)) {
            mPresenter.getMyCollectionMerchant(GSONUtils.paramToJson(new QueryUserIdPage(user.getId(), pageIndex)));
        } else {
            mPresenter.getMyCollectionGoods(GSONUtils.paramToJson(new QueryUserIdPage(user.getId(), pageIndex)));
        }

    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());

        if (MyCollectionsFragment.newInstance().mTitles[0].equals(mResult)) {
            rvIcon.setAdapter(adapter);
        } else {
            rvIcon.setAdapter(adapterGoods);
        }
    }

    public OrderFragment() {
        new OrderPresenter(this);
    }

    public static OrderFragment newInstance(String result) {
        OrderFragment fragment = new OrderFragment();
        fragment.mResult = result;
        return fragment;
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull OrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initMerchantAdapter() throws Exception {
        if (MyCollectionsFragment.newInstance().mTitles[0].equals(mResult)) {
            adapter = new RecyclerAdapter<MerchantResult>(getContext(), R.layout.commercial_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final MerchantResult info) {
                    try {
                        helper.setText(R.id.seller_distance, String.valueOf(info.getDistance()) + "m");

                        if (info.getTypeName() == null || "".equals(info.getTypeName())) {
                            helper.setVisible(R.id.seller_leixing, View.GONE);
                        } else {
                            helper.setVisible(R.id.seller_leixing, View.VISIBLE);
                            helper.setText(R.id.seller_leixing, info.getTypeName());
                        }

                        helper.setText(R.id.seller_city, info.getCityName())
                                .setText(R.id.seller_name, info.getName())
                                .setText(R.id.seller_address, info.getAddress())
                                .setText(R.id.seller_pingfen, String.valueOf(info.getPoint()))
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
        } else {
            adapterGoods = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.collectgoods_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final GoodsResult info) {
                    try {
                        int position = helper.getAdapterPosition();
                        helper.setText(R.id.tv_carName, info.getName())
                                .setText(R.id.tv_carInfo, info.getGoodsAddress())
                                .setText(R.id.tv_storename, info.getGoodsShopName())
                                .setText(R.id.tv_carTime, "￥" + BigDecimalUtil.df.format(info.getMinPrice()))
                                .setImageUrl(R.id.img_carPic, info.getFirstPic())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap<String, String> map = new HashMap();
                                map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(info.getId()));
                                IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                            }
                        });
                        helper.getItemView().setTag("hello world");
                    } catch (Exception e) {
                        Logger.e(e, TAG);
                        e.printStackTrace();
                    }
                }
            };
        }

    }

    @Override
    public void setGetMyCollectionMerchantResult(List<MerchantResult> result) {
        if (result.size() > 0) {
            adapter.addAll(result);
            pageIndex++;
            tvNodata.setVisibility(View.GONE);
        }
    }

    @Override
    public void setGetMyCollectionGoodsResult(List<GoodsResult> result) {
        if (result.size() > 0) {
            adapterGoods.addAll(result);
            pageGoodsIndex++;
            tvNodata.setVisibility(View.GONE);
        }
    }
}