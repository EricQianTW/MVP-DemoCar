package com.clown.wyxc.x_shopmall.recommendgoods;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.x_parambean.GoodsForIndexQuery;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_RecommendGoods extends BaseFragment implements HomeContract_RecommendGoods.View {
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_more)
    TextView tvMore;

    private HomeContract_RecommendGoods.Presenter mPresenter;
    private RecyclerAdapter<GoodsResult> adapter;

    private int pagenum = 1;
    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_recommendgoods, container, false);
        ButterKnife.bind(this, view);

        try {
            pagenum = 1;

            initAdapter();

            initViews();

            initAction();

            initData();
        } catch (Exception e) {
            Logger.e(e, TAG);
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

    public HomeFragment_RecommendGoods() {

    }

    public static HomeFragment_RecommendGoods newInstance() {
        return new HomeFragment_RecommendGoods();
    }

    @Override
    public void setPresenter(HomeContract_RecommendGoods.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initViews() throws Exception {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("加载中...");
        rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .showLastDivider(true)
                .build());

        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .showLastDivider(true)
                .build());

        rvIcon.setAdapter(adapter);

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

                }
            }

            protected boolean isSlideToBottom(RecyclerView recyclerView, int dy) {
                if (recyclerView == null) return false;
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange() && dy > 0) {
                    return true;
                }
                return false;
            }

        });
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.home_adp_goods) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsResult info) {
                try {
                    try {
                        helper.setImageUrl(R.id.goods_image, info.getFirstPic())
                                .setText(R.id.goods_name, info.getName());
                        TitleTextview goods_price = (TitleTextview) helper.getItemView().findViewById(R.id.goods_price);
                        goods_price.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(info.getMinPrice())));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    private void initAction() throws Exception {
    }

    public void initData() throws Exception {
        dialog.show();
        mPresenter.getGoodsForIndex(GSONUtils.paramToJson(new GoodsForIndexQuery(user.getId(), pagenum)));
    }

    @Override
    public void setGetGoodsForIndexResult(List<GoodsResult> result) {
        try {
            if (result.size() > 0) {
                adapter.addAll(result);
                pagenum++;
                tvMore.setText("上拉加载更多");
            } else {
                S.showShort(llMain, "没有更多记录了");
                tvMore.setText("已经全部加载完毕");
            }
            dialog.hide();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}
