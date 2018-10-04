package com.clown.wyxc.x_citychoose;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.clown.wyxc.R;
import com.clown.wyxc.base.ApplicationManager;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.AreaList;
import com.clown.wyxc.components.SideBar;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.AMapUtils;
import com.clown.wyxc.utils.AreaListComparator;
import com.clown.wyxc.utils.CharacterParser;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.tv_char;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class CityChooseFragment extends BaseFragment implements CityChooseContract.View, SectionIndexer {

    @Bind(R.id.boardsent_linearlayout)
    RelativeLayout boardsentLinearlayout;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.sidrbar)
    SideBar sidrbar;
    @Bind(R.id.tv_auto)
    TextView tvAuto;
    private CityChooseContract.Presenter mPresenter;

    private RecyclerAdapter<AreaList> adapter;
    private List<AreaList> strlist = new ArrayList<>();
    private AreaListComparator pinyinComparator = new AreaListComparator();
    private AMapUtils locaUtils;
    private ProgressDialog dialog;
    public static String INTETNNAME_FROM = "fromIndex";

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser = CharacterParser.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.citychoose_frg, container, false);
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
            initAdapter();
            initViews();
            setSearchWord();
            initAction();
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

    private void initAction() throws Exception {
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = getPositionForSection(s.charAt(0));
                if (position != -1) {
                    rvIcon.scrollToPosition(position);
                }
            }
        });

        etSearch.addTextChangedListener(new EditChangedListener());
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                /* 判断是否是“GO”键 */
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    /* 隐藏软键盘 */
                    InputMethodManager imm = (InputMethodManager) v
                            .getContext().getSystemService(
                                    Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(
                                v.getApplicationWindowToken(), 0);
                    }

                    return true;
                }
                return false;
            }
        });
        tvAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                locaUtils.init();
            }
        });
        locaUtils.setOnGetLocationListen(new AMapUtils.OnGetLocationListening() {
            @Override
            public void GetLocationListening(AMapLocation aMapLocation) {
                try {
                    dialog.dismiss();
                    closeWindows(locaUtils.getAMapLocation());
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
    }

    private void closeWindows(AMapLocation mapLocation) {
        ApplicationManager.getInstance().setaMapLocation(mapLocation);

        String from = getArguments().getString(CityChooseActivity.INTETNNAME_FROM);
        int fromInt = 0;
        if (from != null) {
            fromInt = Integer.parseInt(from);
        }
        getActivity().setResult(fromInt, new Intent());
        getActivity().finish();
    }

    class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            adapter.replaceAll(strlist);
            for (int i = 0; i < strlist.size(); i++) {
                String searchStr = etSearch.getText().toString().toLowerCase();
                if (!strlist.get(i).getName().contains(searchStr)) {
                    adapter.remove(strlist.get(i));
                }
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    private void initViews() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);

        locaUtils = new AMapUtils(getContext(), true, false, true);
        dialog = new ProgressDialog(getContext());
    }

    public CityChooseFragment() {
        new CityChoosePresenter(this);
    }

    public static CityChooseFragment newInstance() {
        return new CityChooseFragment();
    }

    @Override
    public void setPresenter(@NonNull CityChooseContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<AreaList>(getContext(), R.layout.brandlsit_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final AreaList info) {
                try {
                    try {
                        int position = helper.getAdapterPosition();
                        // 根据position获取分类的首字母的Char ascii值
                        int section = getSectionForPosition(position);

                        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                        if (position == getPositionForSection(section)) {
                            helper.setText(tv_char, info.getSortLetters());
                            helper.setVisible(tv_char, View.VISIBLE);
                        } else {
                            helper.setVisible(tv_char, View.GONE);
                        }
                        helper.setText(R.id.tv_brandname, info.getName())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    AMapLocation amap = new AMapLocation("");
                                    amap.setAdCode(info.getAdcode());
                                    amap.setCityCode(info.getCitycode());
                                    amap.setCity(info.getName());
                                    amap.setLongitude(new Double(info.getCenter().split(",")[0]));
                                    amap.setLatitude(new Double(info.getCenter().split(",")[1]));

                                    closeWindows(amap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < strlist.size(); i++) {
            String sortStr = strlist.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return strlist.get(position).getSortLetters().charAt(0);
    }

    public void setSearchWord() throws Exception {
        InputStreamReader isr = new InputStreamReader(getActivity().getAssets().open("citylist.json"), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            builder.append(line);
        }
        br.close();
        isr.close();
        JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
        //直接传入JSONObject来构造一个实例
        JSONArray puppiesSearch = testjson.getJSONArray("body");         //从JSONObject中取出数组对象

        for (int i = 0; i < puppiesSearch.length(); i++) {
            AreaList temp = new AreaList();
            temp.setPrefix(puppiesSearch.getJSONObject(i).getString("prefix"));
            temp.setLevel(puppiesSearch.getJSONObject(i).getString("level"));
            temp.setCitycode(puppiesSearch.getJSONObject(i).getString("citycode"));
            temp.setCenter(puppiesSearch.getJSONObject(i).getString("center"));
            temp.setAdcode(puppiesSearch.getJSONObject(i).getString("adcode"));
            temp.setName(puppiesSearch.getJSONObject(i).getString("name"));

            String pinyin = characterParser.getSelling(temp.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                temp.setSortLetters(sortString.toUpperCase());
            } else {
                temp.setSortLetters("#");
            }
            strlist.add(temp);
        }
        Collections.sort(strlist, pinyinComparator);
        adapter.addAll(strlist);
    }

}
