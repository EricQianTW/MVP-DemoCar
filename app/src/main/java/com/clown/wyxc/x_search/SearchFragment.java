package com.clown.wyxc.x_search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.realm.RSearch;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.GoodsResult;
import com.clown.wyxc.x_bean.ScreenConditionsResult;
import com.clown.wyxc.x_bean.ScreenValue;
import com.clown.wyxc.x_bean.x_parambean.GoodsScreenConditionsQuery;
import com.clown.wyxc.x_bean.x_parambean.ScreenConditionsQuery;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/27.
 */
public class SearchFragment extends BaseFragment implements SearchContract.View {
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.rv_iconhidden)
    RecyclerView rvIconhidden;
    @Bind(R.id.tv_choose)
    TextView tvChoose;
    @Bind(R.id.et_keyword)
    EditText etKeyword;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.btn_clear)
    TextView btnClear;
    @Bind(R.id.btn_comnit)
    TextView btnComnit;
    @Bind(R.id.ll_btn)
    LinearLayout llBtn;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;
    @Bind(R.id.vg_tag)
    TagFlowLayout vgTag;
    @Bind(R.id.rl_history)
    RelativeLayout rlHistory;
    @Bind(R.id.tv_cleardata)
    TextView tvCleardata;
    @Bind(R.id.changeimage)
    ImageView mChangeimage;
    @Bind(R.id.tv_zonghe)
    TextView tvZonghe;
    @Bind(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @Bind(R.id.tv_jiage)
    TextView tvJiage;
    @Bind(R.id.tv_historysearch)
    TextView tvHistorysearch;

    private SearchContract.Presenter mPresenter;
    private RecyclerAdapter<GoodsResult> adapter;
    private List<GoodsResult> temp = new ArrayList<>();
    private TagAdapter<String> adapterHistory;
    private List<String> strlist = new ArrayList<>();
    private RecyclerAdapter<ScreenConditionsResult> adapterHidden;
    private int mPagenum = 1;
    private boolean loadfalg = false;
    private int orderCriteria = 1;
    private int ispositive = 0;
    private List<ScreenConditionsResult> screenConditions = new ArrayList<>();
    private List<ScreenConditionsResult> screenConditionsNoChange = new ArrayList<>();
    private int lastVisibleItem = 0;
    private GridLayoutManager mLayoutManager;
    private boolean isChanged = false;
    private String name;
    private boolean jiageFlag = false;

    private Integer goodsTypeId;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            initBack(toolbar);
            initAdapter();
            initViews();
            initAction();
            initData();
            setSearchWord();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initAction() throws Exception {
        tvZonghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZonghe.setTextColor(getResources().getColor(R.color.red));
                tvJiage.setTextColor(getResources().getColor(R.color.black));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.black));
                orderCriteria = 1;
                ispositive = 0;
                mPagenum = 1;
                adapter.clear();
                getData();
            }
        });
        tvJiage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZonghe.setTextColor(getResources().getColor(R.color.black));
                tvJiage.setTextColor(getResources().getColor(R.color.red));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.black));
                if (jiageFlag) {
                    orderCriteria = 2;
                    ispositive = 1;
                    mPagenum = 1;
                    adapter.clear();
                    getData();
                    tvJiage.setBackgroundResource(R.drawable.choosebar_press2_up);
                }else {
                    orderCriteria = 2;
                    ispositive = 0;
                    mPagenum = 1;
                    adapter.clear();
                    getData();
                    tvJiage.setBackgroundResource(R.drawable.choosebar_press2_down);
                }
                jiageFlag = !jiageFlag;
            }
        });
        tvXiaoliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZonghe.setTextColor(getResources().getColor(R.color.black));
                tvJiage.setTextColor(getResources().getColor(R.color.black));
                tvXiaoliang.setTextColor(getResources().getColor(R.color.red));
                orderCriteria = 3;
                ispositive = 0;
                mPagenum = 1;
                adapter.clear();
                getData();
            }
        });
        etKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                try {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        /*隐藏软键盘*/
                        InputMethodManager imm = (InputMethodManager) v
                                .getContext().getSystemService(
                                        Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            imm.hideSoftInputFromWindow(
                                    v.getApplicationWindowToken(), 0);
                        }

                        // 保存检索条件到Realm中
                        if (!"".equals(etKeyword.getText().toString())) {
                            RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
                            Realm.setDefaultConfiguration(realmConfig);
                            Realm realm = Realm.getDefaultInstance();

                            realm.beginTransaction();

                            final RealmResults<RSearch> puppiesSearch = realm.where(RSearch.class).findAll();

                            List<String> array = new ArrayList<>();
                            for (int i = 0; i < puppiesSearch.size(); i++) {
                                array.add(puppiesSearch.get(i).getSearchWord());
                            }

                            if (!array.contains(etKeyword.getText().toString())) {
                                RSearch search = realm.createObject(RSearch.class);
                                search.setSearchWord(etKeyword.getText().toString());
                                search.setSearchTime(new Date());
                            }

                            realm.commitTransaction();
                        }

                        mPagenum = 1;
                        adapter.clear();
                        loadfalg = false;
                        getData();

                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
                return false;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPagenum = 1;
                adapterHidden.clear();
                for (ScreenConditionsResult msgScreenConditions : screenConditionsNoChange) {
                    for (ScreenValue msgScreenValue : msgScreenConditions.getScreenValueList()) {
                        msgScreenValue.setIsSelected(0);
                    }
                }
                adapterHidden.addAll(screenConditionsNoChange);
            }
        });
        btnComnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPagenum = 1;
                adapter.clear();
                mPresenter.getGoodsByScreenConditions(GSONUtils.paramToJson(new GoodsScreenConditionsQuery(user.getId(),
                        mPagenum, goodsTypeId, "".equals(etKeyword.getText().toString()) ? null : etKeyword.getText().toString(), ispositive, orderCriteria, screenConditions)));
            }
        });
        tvCleardata.setOnClickListener(new View.OnClickListener() {
            @Override
            //删除历史数据
            public void onClick(View v) {
                RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
                Realm.setDefaultConfiguration(realmConfig);
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();
                final RealmResults<RSearch> removeList = realm.where(RSearch.class).findAll();
                removeList.deleteAllFromRealm();
                realm.commitTransaction();

                strlist.clear();
                adapterHistory.notifyDataChanged();
            }
        });
        swipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPagenum = 1;
                adapter.clear();
                loadfalg = false;

                getData();
                swipeRefreshWidget.setRefreshing(false);
            }
        });

        mChangeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChanged = !isChanged;
                if (isChanged) {
                    mChangeimage.setImageResource(R.drawable.verticalarrangex3);
                    temp.clear();
                    temp.addAll(adapter.getAll());
                    updateHorizontalAdapter();
                    adapter.addAll(temp);
                    mLayoutManager = new GridLayoutManager(getActivity(), 1);
                    rvIcon.setLayoutManager(mLayoutManager);
                    rvIcon.addItemDecoration(new HorizontalItemDecoration
                            .Builder(getContext())
                            .colorResId(R.color.gray_theme)
                            .sizeResId(R.dimen.height_explore_divider_1)
                            .showLastDivider(false)
                            .build());
                    rvIcon.setAdapter(adapter);
                } else {
                    mChangeimage.setImageResource(R.drawable.rowarrangex3);
                    mLayoutManager = new GridLayoutManager(getActivity(), 2);

                    temp.clear();
                    temp.addAll(adapter.getAll());
                    updateRowAdapter();
                    adapter.addAll(temp);
                    rvIcon.setLayoutManager(mLayoutManager);
                    rvIcon.addItemDecoration(new HorizontalItemDecoration
                            .Builder(getContext())
                            .colorResId(R.color.gray_theme)
                            .sizeResId(R.dimen.height_explore_divider_1)
                            .showLastDivider(false)
                            .build());
                    rvIcon.addItemDecoration(new VerticalItemDecoration
                            .Builder(getContext())
                            .colorResId(R.color.gray_theme)
                            .sizeResId(R.dimen.height_explore_divider_1)
                            .showLastDivider(false)
                            .build());
                    rvIcon.setAdapter(adapter);
                }
            }
        });

    }

    private void getData() {
        mPresenter.getGoodsByScreenConditions(GSONUtils.paramToJson(new GoodsScreenConditionsQuery(user.getId(),
                mPagenum, goodsTypeId, "".equals(etKeyword.getText().toString()) ? null : etKeyword.getText().toString(), ispositive, orderCriteria, screenConditions)));
        mPresenter.getScreenConditions(GSONUtils.paramToJson(new ScreenConditionsQuery(user.getId(), "".equals(etKeyword.getText().toString()) ? null : etKeyword.getText().toString(), goodsTypeId)));
    }

    private void initViews() throws Exception {
        Bundle data = getArguments();
        name = data.getString(SearchActivity.INTENTNAME_KEYVALUE);

        if (!(data.getString(SearchActivity.INTENTNAME_TYPE1) == null)) {
            goodsTypeId = Integer.parseInt(data.getString(SearchActivity.INTENTNAME_TYPE1));
        } else {
            goodsTypeId = null;
        }


        rvIconhidden.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIconhidden.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.white)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIconhidden.setAdapter(adapterHidden);

        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvIcon.setLayoutManager(mLayoutManager);
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .showLastDivider(false)
                .build());
        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .showLastDivider(false)
                .build());
        rvIcon.setAdapter(adapter);

        rvIcon.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView,
                                             int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE
                            && lastVisibleItem + 1 == adapter.getItemCount()) {
                        if (!loadfalg) {
                            if (!swipeRefreshWidget.isRefreshing()) {
                                swipeRefreshWidget.setRefreshing(true);
                            } else {
                                swipeRefreshWidget.setRefreshing(false);
                            }
                            loadfalg = true;
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    try {
                                        mPresenter.getGoodsByScreenConditions(GSONUtils.paramToJson(new GoodsScreenConditionsQuery(user.getId(),
                                                mPagenum, goodsTypeId, "".equals(etKeyword.getText().toString()) ? null : etKeyword.getText().toString(), ispositive, orderCriteria, screenConditions)));
                                    } catch (Exception e) {
                                        Logger.e(e, TAG);
                                        e.printStackTrace();
                                    }
                                }
                            }, 2000);
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
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }

        });
        rvIcon.setHasFixedSize(true);
        rvIcon.setItemAnimator(new DefaultItemAnimator());

        tvChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(drawerLayout.getWindowToken(), 0);
                    drawerLayout.openDrawer(Gravity.RIGHT);
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });

        if (name != null) {
            etKeyword.setText(name);
            etKeyword.requestFocus();
            getData();
        }
    }

    private void initAdapter() {
        adapter = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.search_adp_goods) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsResult info) {
                try {
                    final int position = helper.getAdapterPosition();
                    helper.setText(R.id.goods_name, info.getName())
                            .setText(R.id.goods_price, "￥" + String.valueOf(BigDecimalUtil.df.format(info.getMinPrice())))
                            .setImageUrl(R.id.goods_image, info.getFirstPic())
                    ;
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

        adapterHidden = new RecyclerAdapter<ScreenConditionsResult>(getContext(), screenConditions, R.layout.search_adp_hidden) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, ScreenConditionsResult info) {
                try {
                    final int positions = helper.getAdapterPosition();
                    helper.setText(R.id.tv_title, info.getName());

                    final LayoutInflater mInflater = LayoutInflater.from(getActivity());
                    final TagFlowLayout mFlowLayout = (TagFlowLayout) helper.getItemView().findViewById(R.id.id_flowlayout);

                    if (info.getIsMultiple() == 0) {
                        mFlowLayout.setMaxSelectCount(1);
                    }

                    final String[] arr = new String[info.getScreenValueList().size()];
                    for (int i = 0; i < info.getScreenValueList().size(); i++) {
                        arr[i] = info.getScreenValueList().get(i).getScreenName();
                    }
                    mFlowLayout.setAdapter(new TagAdapter<String>(arr) {

                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            TextView tv = (TextView) mInflater.inflate(R.layout.common_zhy_tv,
                                    mFlowLayout, false);
                            tv.setText(s);
                            return tv;
                        }

                        @Override
                        public boolean setSelected(int position, String s) {
                            return s.equals("Android");
                        }
                    });

                    mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            try {
                                if (screenConditions.get(positions).getIsMultiple() == 0) {
                                    for (ScreenValue msgScreenValue : screenConditions.get(positions).getScreenValueList()) {
                                        msgScreenValue.setIsSelected(0);
                                    }
                                }
                                if (screenConditions.get(positions).getScreenValueList().get(position).getIsSelected() == 0) {
                                    screenConditions.get(positions).getScreenValueList().get(position).setIsSelected(1);
                                } else {
                                    screenConditions.get(positions).getScreenValueList().get(position).setIsSelected(0);
                                }
                                if (positions == screenConditions.size() - 2) {
                                    orderCriteria = screenConditions.get(positions).getScreenValueList().get(position).getScreenValue();
                                } else if (positions == screenConditions.size() - 1) {
                                    ispositive = screenConditions.get(positions).getScreenValueList().get(position).getScreenValue();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return true;
                        }
                    });


                    mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                        @Override
                        public void onSelected(Set<Integer> selectPosSet) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        adapterHistory = new TagAdapter(strlist) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.common_zhy_tv
                        , vgTag, false);
                tv.setText(strlist.get(position));
                return tv;
            }
        };
        vgTag.setMaxSelectCount(1);
        vgTag.setAdapter(adapterHistory);
        vgTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                etKeyword.setText(strlist.get(position));

                mPresenter.getGoodsByScreenConditions(GSONUtils.paramToJson(new GoodsScreenConditionsQuery(user.getId(),
                        1, null, strlist.get(position), ispositive, orderCriteria, null)));
                return true;
            }
        });

        vgTag.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });
    }

    private void initData() throws Exception {
        mPresenter.getScreenConditions(GSONUtils.paramToJson(new ScreenConditionsQuery(user.getId(), "".equals(etKeyword.getText().toString()) ? null : etKeyword.getText().toString(), goodsTypeId)));
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setGetScreenConditionsResult(List<ScreenConditionsResult> result) {

        screenConditions.clear();
        screenConditions.addAll(result);
        screenConditionsNoChange.clear();
        screenConditionsNoChange.addAll(result);
        adapterHidden.clear();
        adapterHidden.addAll(screenConditions);
    }

    @Override
    public void setGetGoodsByScreenConditionsResult(List<GoodsResult> result) {
        try {
            if (result.size() != 0) {
                adapter.addAll(result);
                drawerLayout.closeDrawer(GravityCompat.END);
                mPagenum++;
                rlHistory.setVisibility(View.GONE);
            } else {
                S.showShort(drawerLayout, "未检索到数据");
            }
            loadfalg = false;
            swipeRefreshWidget.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSearchWord() {
        strlist.clear();
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<RSearch> puppiesSearch = realm.where(RSearch.class).findAll().sort("searchTime", Sort.DESCENDING);

        for (int i = 0; i < puppiesSearch.size(); i++) {
            strlist.add(puppiesSearch.get(i).getSearchWord());
        }

        adapterHistory.notifyDataChanged();
    }


    public void updateRowAdapter() {
        adapter = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.search_adp_goods) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsResult info) {
                try {
                    final int position = helper.getAdapterPosition();
                    helper.setText(R.id.goods_name, info.getName())
                            .setText(R.id.goods_price, "￥" + String.valueOf(BigDecimalUtil.df.format(info.getMinPrice())))
                            .setImageUrl(R.id.goods_image, info.getFirstPic())
                    ;
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


    public void updateHorizontalAdapter() {

        adapter = new RecyclerAdapter<GoodsResult>(getContext(), R.layout.search_adp_itemgoods) {
            @Override
            protected void convert(RecyclerAdapterHelper helper, final GoodsResult item) {
                helper.setText(R.id.goods_name, item.getName());
                helper.setText(R.id.goods_price, "￥" + String.valueOf(BigDecimalUtil.df.format(item.getMinPrice())));
                helper.setImageUrl(R.id.goods_image, item.getFirstPic());
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap();
                        map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(item.getId()));
                        IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);
                    }
                });


            }
        };
    }
}
