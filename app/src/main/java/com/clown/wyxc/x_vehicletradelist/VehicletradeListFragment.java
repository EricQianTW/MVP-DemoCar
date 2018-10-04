package com.clown.wyxc.x_vehicletradelist;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.expandtabview.ExpandTabView;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.DensityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.VehiclBrand;
import com.clown.wyxc.x_bean.VehiclListResult;
import com.clown.wyxc.x_bean.VehiclPriceRangeResult;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_bean.x_parambean.VehiclShaiXuanQuery;
import com.clown.wyxc.x_vehicletradedetail.VehicletradedetailActivity;
import com.clown.wyxc.x_vehicletradelist.tabview.ColligateView;
import com.clown.wyxc.x_vehicletradelist.tabview.VehiclBrandView;
import com.clown.wyxc.x_vehicletradelist.tabview.VehiclPriceView;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class VehicletradeListFragment extends BaseFragment implements VehicletradeListContract.View {

    @Bind(R.id.expandtab_view)
    ExpandTabView expandtabView;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.iv_qiehuan)
    ImageView ivQiehuan;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
//    @Bind(R.id.bs_scroll)
//    BottomScrollView bsScroll;

    private VehicletradeListContract.Presenter mPresenter;
    private List<VehiclBrand> vehiclBrandArr = new ArrayList<>();
    private List<VehiclPriceRangeResult> vehiclPriceArr = new ArrayList<>();

    private VehiclBrandView vehiclBrand;
    private VehiclPriceView vehiclPrice;
    private ColligateView colligate;

    private ArrayList<View> mViewArray = new ArrayList<View>();
    private VehiclShaiXuanQuery singleQuery;
    private int pageIndex = 1;
    private boolean qiehuanFlag = true;
    private RecyclerView.ItemDecoration hItemDecoration;
    private RecyclerView.ItemDecoration vItemDecoration;
    private int lastVisibleItem = 0;
    private int lastVisibleItem2 = 0;
    private boolean loadfalg = false;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    private RecyclerAdapter<VehiclListResult> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicletradelist_frg, container, false);
        ButterKnife.bind(this, view);

        try {
            initAdapter();

            initViews();

            initData();

            initAction();
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public VehicletradeListFragment() {
        new VehicletradeListPresenter(this);
    }

    public static VehicletradeListFragment newInstance() {
        return new VehicletradeListFragment();
    }

    @Override
    public void setPresenter(@NonNull VehicletradeListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initViews() throws Exception {
        hItemDecoration = new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build();
        vItemDecoration = new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build();

        rvIcon.addItemDecoration(hItemDecoration);
        rvIcon.addItemDecoration(vItemDecoration);
        if (qiehuanFlag) {
            rvIcon.setLayoutManager(gridLayoutManager);
            rvIcon.setAdapter(adapter);
        } else {
            rvIcon.setLayoutManager(linearLayoutManager);
            rvIcon.setAdapter(adapter);
        }
    }

    private void initAdapter() throws Exception {
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        lastVisibleItem = 0;
        lastVisibleItem2 = 0;
        if (qiehuanFlag) {
            adapter = new RecyclerAdapter<VehiclListResult>(getContext(), R.layout.vehicletradelist2_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final VehiclListResult info) {
                    try {
                        helper.setText(R.id.tv_carName, info.getName())
                                .setText(R.id.tv_price, info.getStartPrice())
                                .setImageUrl(R.id.img_carPic, info.getFirstPic())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goNext(info);
                            }
                        });
                        if (info.getEndPrice() != null && !"".equals(info.getEndPrice())) {
                            helper.setText(R.id.tv_endprice, "指导价" + info.getEndPrice());
                            TextView tv = (TextView) helper.getItemView().findViewById(R.id.tv_endprice);
                            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                            helper.setVisible(R.id.tv_endprice, View.VISIBLE);
                        } else {
                            helper.setVisible(R.id.tv_endprice, View.GONE);
                        }
                        helper.getItemView().setTag("hello world");
                    } catch (Exception e) {
                        Logger.e(e, TAG);
                        e.printStackTrace();
                    }
                }
            };

            rvIcon.setOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrollStateChanged(RecyclerView recyclerView,
                                                 int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    try {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE
                                && lastVisibleItem2 + 1 == adapter.getItemCount()) {
                            if (!loadfalg) {
                                rollMoreInfo();
                            }
                        }
                    } catch (Exception e) {
                        Logger.e(e, TAG);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem2 = gridLayoutManager.findLastVisibleItemPosition();
                }

            });
        } else {
            adapter = new RecyclerAdapter<VehiclListResult>(getContext(), R.layout.vehicletradelist_adp) {
                @Override
                protected void convert(final RecyclerAdapterHelper helper, final VehiclListResult info) {
                    try {
                        helper.setText(R.id.tv_carName, info.getName())
                                .setText(R.id.tv_price, info.getStartPrice())
                                .setImageUrl(R.id.img_carPic, info.getFirstPic())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goNext(info);
                            }
                        });
                        if (info.getEndPrice() != null && !"".equals(info.getEndPrice())) {
                            helper.setText(R.id.tv_endprice, "指导价" + info.getEndPrice());
                            TextView tv = (TextView) helper.getItemView().findViewById(R.id.tv_endprice);
                            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                            tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
                            helper.setVisible(R.id.tv_endprice, View.VISIBLE);
                        } else {
                            helper.setVisible(R.id.tv_endprice, View.GONE);
                        }
                        helper.getItemView().setTag("hello world");
                    } catch (Exception e) {
                        Logger.e(e, TAG);
                        e.printStackTrace();
                    }
                }

            };
            rvIcon.setOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView,
                                                 int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    try {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE
                                && lastVisibleItem + 1 == adapter.getItemCount()) {
                            if (!loadfalg) {
                                rollMoreInfo();
                            }
                        }
                    } catch (Exception e) {
                        Logger.e(e, TAG);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }

            });
        }
    }

    private void rollMoreInfo() {
        loadfalg = true;
        singleQuery.setPageIndex(pageIndex);
        tvMore.setVisibility(View.VISIBLE);
        getVehiclByQuery();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvMore.setVisibility(GONE);
            }
        }, 1000);
    }

    private void goNext(VehiclListResult info) {
        HashMap map = new HashMap();
        map.put(VehicletradedetailActivity.INTANTNAME_VEHICLID, String.valueOf(info.getVehiclId()));
        IntentUtils.startActivity(getContext(), VehicletradedetailActivity.class, map);
    }

    private void initData() throws Exception {
        mPresenter.getVehiclBrandList(GSONUtils.paramToJson(new QueryUserId(user.getId())));

        singleQuery = new VehiclShaiXuanQuery(null, null, 1, 0, pageIndex);
        getVehiclByQuery();
    }

    private void getVehiclByQuery() {
        mPresenter.getVehiclByQuery(GSONUtils.paramToJson(singleQuery));
    }

    private void initAction() throws Exception {
        ivQiehuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<VehiclListResult> arr = adapter.getAll();
                    if (qiehuanFlag) {
                        qiehuanFlag = false;
                        ivQiehuan.setImageResource(R.drawable.verticalarrangex3);
                    } else {
                        qiehuanFlag = true;
                        ivQiehuan.setImageResource(R.drawable.rowarrangex3);
                    }
                    initAdapter();

                    if (qiehuanFlag) {
                        rvIcon.setLayoutManager(gridLayoutManager);
                        rvIcon.setAdapter(adapter);
                    } else {
                        rvIcon.setLayoutManager(linearLayoutManager);
                        rvIcon.setAdapter(adapter);
                    }

                    adapter.addAll(arr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                singleQuery.setPageIndex(1);
                ivEmpty.setVisibility(View.VISIBLE);
                adapter.clear();
                getVehiclByQuery();
            }
        });

    }

    @Override
    public void setGetVehiclBrandListResult(List<VehiclBrand> result) {
        VehiclBrand allResult = new VehiclBrand(null, null, "全部", null, null, null);
        vehiclBrandArr.add(allResult);
        vehiclBrandArr.addAll(result);
        mPresenter.getVehiclPriceRangeList(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    @Override
    public void setGetVehiclPriceRangeListResult(List<VehiclPriceRangeResult> result) {
        try {
            VehiclPriceRangeResult allResult = new VehiclPriceRangeResult("全部", null);
            vehiclPriceArr.add(allResult);
            vehiclPriceArr.addAll(result);

            vehiclBrand = new VehiclBrandView(getContext(), vehiclBrandArr);
            vehiclPrice = new VehiclPriceView(getContext(), vehiclPriceArr);
            colligate = new ColligateView(getContext());

            mViewArray.clear();
            mViewArray.add(vehiclBrand);
            mViewArray.add(vehiclPrice);
            mViewArray.add(colligate);
            ArrayList<String> mTextArray = new ArrayList<String>();
            mTextArray.add("品牌");
            mTextArray.add("价格区间");
            mTextArray.add("综合排序");
            expandtabView.setValue(mTextArray, mViewArray, rlTitle.getHeight() + DensityUtils.dip2px(getContext(), 60));
        } catch (Exception e) {
            e.printStackTrace();
        }

        vehiclBrand.setOnSelectListener(new VehiclBrandView.OnSelectListener() {
            @Override
            public void getValue(VehiclBrand brand) {
                expandtabView.setTitle(brand.getName(), 0);
                adapter.clear();

                singleQuery.setPageIndex(1);
                if (!"全部".equals(brand.getName())) {
                    singleQuery.setVehiclBrand(brand);
                } else {
                    singleQuery.setVehiclBrand(null);
                }

                adapter.clear();
                ivEmpty.setVisibility(View.VISIBLE);
                getVehiclByQuery();
                expandtabView.onPressBack();
            }
        });

        vehiclPrice.setOnSelectListener(new VehiclPriceView.OnSelectListener() {
            @Override
            public void getValue(VehiclPriceRangeResult price) {
                expandtabView.setTitle(price.getName(), 1);

                singleQuery.setPageIndex(1);
                if (!"全部".equals(price.getName())) {
                    singleQuery.setVehiclPriceRangeResult(price);
                } else {
                    singleQuery.setVehiclPriceRangeResult(null);
                }

                adapter.clear();
                ivEmpty.setVisibility(View.VISIBLE);
                getVehiclByQuery();
                expandtabView.onPressBack();
            }
        });

        colligate.setOnSelectListener(new ColligateView.OnSelectListener() {
            @Override
            public void getValue(String id, String showText) {
                expandtabView.setTitle(showText, 2);

                singleQuery.setPageIndex(1);
                singleQuery.setOrderCriteria(Integer.parseInt(id.split("_")[0]));
                singleQuery.setIsPositive(Integer.parseInt(id.split("_")[1]));

                adapter.clear();
                ivEmpty.setVisibility(View.VISIBLE);
                getVehiclByQuery();
                expandtabView.onPressBack();
            }
        });
    }

    @Override
    public void setGetVehiclByQueryResult(List<VehiclListResult> result) {
//        tvMore.setVisibility(View.VISIBLE);
        if (result.size() > 0) {
            adapter.addAll(result);
            ivEmpty.setVisibility(View.INVISIBLE);
            pageIndex++;
            tvMore.setText("上拉加载更多");
        } else {
            tvMore.setText("已经全部加载完毕");
        }
        swipeRefresh.setRefreshing(false);
        loadfalg = false;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                tvMore.setVisibility(View.GONE);
//            }
//        },1000);
    }
}
