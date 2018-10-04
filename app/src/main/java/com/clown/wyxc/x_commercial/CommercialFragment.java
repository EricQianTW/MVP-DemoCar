package com.clown.wyxc.x_commercial;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.clown.wyxc.MainActivity;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.realm.RSearch;
import com.clown.wyxc.components.expandtabview.ExpandTabView;
import com.clown.wyxc.components.expandtabview.ViewMiddle;
import com.clown.wyxc.components.expandtabview.ViewRight;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.DensityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.MerchantResult;
import com.clown.wyxc.x_bean.ServiceItems;
import com.clown.wyxc.x_bean.ServiceItemsResult;
import com.clown.wyxc.x_bean.x_parambean.MerchantQuery;
import com.clown.wyxc.x_citychoose.CityChooseActivity;
import com.clown.wyxc.x_companydetail.CompanyDetailActivity;
import com.clown.wyxc.x_message.MessageActivity;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.clown.wyxc.R.id.rl_history;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class CommercialFragment extends BaseFragment implements CommercialContract.View {

    @Bind(R.id.location_tv)
    TextView locationTv;
    @Bind(R.id.message_img)
    ImageView messageImg;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.expandtab_view)
    ExpandTabView expandtabView;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.search_img)
    ImageView searchImg;
    @Bind(R.id.center_layout)
    LinearLayout centerLayout;
    @Bind(R.id.tv_historysearch)
    TextView tvHistorysearch;
    @Bind(R.id.tv_cleardata)
    TextView tvCleardata;
    @Bind(R.id.vg_tag)
    TagFlowLayout vgTag;
    @Bind(rl_history)
    RelativeLayout rlHistory;
    @Bind(R.id.et_keyword)
    EditText etKeyword;

    private ViewMiddle viewMiddle;
    private ViewRight viewRight;
    private ArrayList<View> mViewArray = new ArrayList<View>();

    private CommercialContract.Presenter mPresenter;
    private RecyclerAdapter<MerchantResult> adapter;
    private int pageindex = 1;
    private boolean loading = false;

    private List<String> strlist = new ArrayList<>();
    private TagAdapter<String> adapterHistory;

    private Integer serviceItemId = null;
    private int searchType = 1;
    public static String keyword = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commmercial_frg, container, false);
        ButterKnife.bind(this, view);

        try {
            pageindex = 1;

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

    public CommercialFragment() {
        new CommercialPresenter(this);
    }

    public static CommercialFragment newInstance() {
        return new CommercialFragment();
    }

    @Override
    public void setPresenter(CommercialContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initViews() throws Exception {
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
                                .setText(R.id.seller_pingfen, String.valueOf(info.getPoint().intValue())+"分")
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                HashMap map = new HashMap();
                                map.put(CompanyDetailActivity.INTENTNAME_COMPANYID,String.valueOf(info.getId()));
                                IntentUtils.startActivity(getContext(), CompanyDetailActivity.class,map);
                            }
                        });

                        if (info.getMerchantPics() != null && info.getMerchantPics().size() > 0 && info.getMerchantPics().get(0).getPic() != null) {
                            helper.setImageUrl(R.id.seller_img, info.getMerchantPics().get(0).getPic());
                        }
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
    }

    private void initData() throws Exception {
        Bundle data = getArguments();

        if (data != null) {
            keyword = data.getString(MainActivity.PARAM_TAB2_KEYWORD);
        }

        locationTv.setText(aMapLocation.getCity());
        mPresenter.getServiceItemsListByQuery();

        if (keyword != null && !"".equals(keyword)) {
            etKeyword.setText(keyword);
        }else{
            setSearchWord();
        }
        searchData();
    }

    private void searchData() {
        if (!loading) {
            loading = true;
            mPresenter.getMerchantListByQuery(GSONUtils.paramToJson(new MerchantQuery(searchType
                    , BigDecimalUtil.round2BigDecimal(aMapLocation.getLongitude(), 2)
                    , BigDecimalUtil.round2BigDecimal(aMapLocation.getLatitude(), 2)
                    , serviceItemId
                    , aMapLocation.getAdCode()
                    , pageindex
                    , user.getId()
                    , etKeyword.getText().toString())));
        }
    }

    private void initAction() throws Exception {
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
                rlHistory.setVisibility(View.GONE);
            }
        });
        vgTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {

            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                etKeyword.setText(strlist.get(position));
                searchData();
                return true;
            }
        });

        locationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(CityChooseActivity.INTETNNAME_FROM,"1");
                IntentUtils.startActivityForResult(getContext(), CityChooseActivity.class,map);
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

                        pageindex = 1;
                        adapter.clear();
                        loading = false;

                        searchData();

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
        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getActivity(), MessageActivity.class);
            }
        });
    }

    @Override
    public void setGetServiceItemsListByQueryResult(List<ServiceItemsResult> result) {
        try {
            ServiceItemsResult allResult = new ServiceItemsResult(new ArrayList<ServiceItems>(),null,"全部",null,null,null,null,null,null,null);
            List<ServiceItems> temp1 = new ArrayList<>();
            temp1.add(new ServiceItems(null,"全部",null,null,null,null,null,null,null));
            allResult.setList(temp1);
            result.add(0,allResult);

            for (int i = 1; i < result.size(); i++) {
                List<ServiceItems> temp = result.get(i).getList();
                if (temp.size() > 0) {
                    temp.add(0,new ServiceItems(result.get(i).getId(),"全部",null,null,null,null,null,null,null));
                }
            }

            viewMiddle = new ViewMiddle(getContext(), result);
            viewRight = new ViewRight(getContext());

            mViewArray.clear();
            mViewArray.add(viewMiddle);
            mViewArray.add(viewRight);
            ArrayList<String> mTextArray = new ArrayList<String>();
            mTextArray.add("服务项目");
            mTextArray.add("智能排序");
            expandtabView.setValue(mTextArray, mViewArray,rlTitle.getHeight()+ DensityUtils.dip2px(getContext(),40));
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {
            @Override
            public void getValue(String showText,Integer id) {
                expandtabView.setTitle(showText, 0);
                serviceItemId = id;
                adapter.clear();
                pageindex = 1;
                searchData();
                expandtabView.onPressBack();
            }
        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {
            @Override
            public void getValue(String distance, String showText) {
                try {
                    expandtabView.setTitle(showText, 1);

                    expandtabView.onPressBack();
                    searchType = Integer.parseInt(distance);
                    adapter.clear();
                    pageindex = 1;
                    searchData();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setGetMerchantListByQueryResult(List<MerchantResult> result) {
        try {
            if (result.size() > 0) {
                adapter.addAll(result);
                pageindex++;
                rlHistory.setVisibility(View.GONE);
            } else {
                T.showShort(getContext(), "没有更多的数据了");
            }

            loading = false;
        } catch (Exception e) {
            Logger.e(e, TAG);
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

        if (strlist.size() == 0) {
            rlHistory.setVisibility(View.GONE);
        }else{
            rlHistory.setVisibility(View.VISIBLE);
        }
        adapterHistory.notifyDataChanged();
    }

}
