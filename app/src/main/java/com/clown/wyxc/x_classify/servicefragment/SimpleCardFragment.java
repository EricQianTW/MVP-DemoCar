package com.clown.wyxc.x_classify.servicefragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.BottomScrollView;
import com.clown.wyxc.components.ImageViewEquals;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.GoodsTypeLevel;
import com.clown.wyxc.x_bean.GoodsTypeResult;
import com.clown.wyxc.x_bean.x_parambean.ByGoodsTypeQuery;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
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

public class SimpleCardFragment extends BaseFragment implements SimpleCardContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.rv_goods)
    RecyclerView rvGoods;
    @Bind(R.id.m_btS)
    BottomScrollView mBtS;
    @Bind(R.id.tv_more)
    TextView tvMore;

    private SimpleCardContract.Presenter mPresenter;

    private GoodsTypeResult mResult = new GoodsTypeResult();

    private RecyclerAdapter<GoodsTypeLevel> adapter;
    private RecyclerAdapter<GoodsResult> mMsgSaleInfoAdapter;

    private List<GoodsTypeLevel> mList = new ArrayList<>();

    private int pageIndex = 1;
    private Integer classType2 = null;
    private boolean loadfalg = false;
    private GridLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classifycard_frg, container, false);
        ButterKnife.bind(this, view);

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
        mList.clear();
        mList.add(new GoodsTypeLevel("全部", mResult.getImageUrl()));
        mList.addAll(mResult.getList());
        adapter.replaceAll(mList);

        mPresenter.getGoodsByGoodsType(GSONUtils.paramToJson(new ByGoodsTypeQuery(user.getId(),pageIndex,mResult.getId(),null,null)));

    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvGoods.setLayoutManager(mLayoutManager);
        rvGoods.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvGoods.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvGoods.setAdapter(mMsgSaleInfoAdapter);
    }

    public SimpleCardFragment() {

        new SimpleCardPresenter(this);
    }

    public static SimpleCardFragment newInstance(GoodsTypeResult result) {
        SimpleCardFragment mFrag = new SimpleCardFragment();
        mFrag.mResult = result;
        return mFrag;
    }

    private void initAction() throws Exception {
        mBtS.setOnScrollToBottomLintener(new BottomScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                try {
                    if (isBottom) {
                        if (!loadfalg) {
                            loadfalg = true;
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    try {
                                        mPresenter.getGoodsByGoodsType(GSONUtils.paramToJson(new ByGoodsTypeQuery(user.getId(),pageIndex,mResult.getId(),classType2,null)));
                                    } catch (Exception e) {
                                        Logger.e(e, TAG);
                                        e.printStackTrace();
                                    }
                                }
                            }, 1);
                        }
                    }
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setPresenter(@NonNull SimpleCardContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<GoodsTypeLevel>(getContext(), mList, R.layout.classifycard_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsTypeLevel info) {
                try {
                    try {
                        helper.setText(R.id.tv_servicename, info.getName());
                        if (info.getImageUrl() != null) {
                            ImageViewEquals iv_pic = helper.getView(R.id.iv_pic);
                            ImageLoader.getInstance().displayImage(info.getImageUrl(),iv_pic);
                        }
                        helper.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pageIndex = 1;
                                classType2 = info.getId();
                                mMsgSaleInfoAdapter.clear();

                                mPresenter.getGoodsByGoodsType(GSONUtils.paramToJson(new ByGoodsTypeQuery(user.getId(),pageIndex,mResult.getId(),classType2,null)));

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    helper.getItemView().setTag("hello world");
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        };

        mMsgSaleInfoAdapter = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.home_adp_goods) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, final GoodsResult info) {
                try {
                    TitleTextview goods_price = (TitleTextview) helper.getItemView().findViewById(R.id.goods_price);

                    goods_price.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(info.getMinPrice())));
                    helper.setText(R.id.goods_name, info.getName())
                            .setImageUrl(R.id.goods_image, info.getFirstPic());

                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void setGetGoodsByGoodsTypeResult(List<GoodsResult> result) {
        try {
            if (result.size() > 0) {
                mMsgSaleInfoAdapter.addAll(result);
                pageIndex++;
                loadfalg = false;
                tvMore.setText("上拉加载更多");
            } else {
                S.showShort(llMain, "没有更多记录了");
                tvMore.setText("已经全部加载完毕");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}