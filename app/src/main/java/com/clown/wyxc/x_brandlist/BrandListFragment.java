package com.clown.wyxc.x_brandlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.SideBar;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.CharacterParser;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.SDCard.BrandListComparator;
import com.clown.wyxc.x_bean.CarInfo;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_brandsublist.BrandSubListActivity;
import com.clown.wyxc.x_common.Constants;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.tv_char;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class BrandListFragment extends BaseFragment implements BrandListContract.View, SectionIndexer {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.sidrbar)
    SideBar sidrbar;
    @Bind(R.id.boardsent_linearlayout)
    RelativeLayout boardsentLinearlayout;

    private BrandListContract.Presenter mPresenter;

    private RecyclerAdapter<CarInfo> adapter;

    private List<CarInfo> strlist = new ArrayList<>();

    private BrandListComparator pinyinComparator = new BrandListComparator();

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser = CharacterParser.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brandlist_frg, container, false);
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
            initView();
            initAction();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initData() {
        mPresenter.getCarInfoPPList(GSONUtils.paramToJson(new QueryUserId(user.getId())));
    }

    private void initView() {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
        rvIcon.setAdapter(adapter);
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

    public BrandListFragment() {
        new BrandListPresenter(this);
    }

    public static BrandListFragment newInstance() {
        return new BrandListFragment();
    }

    private void initAction() {
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
    }

    @Override
    public void setPresenter(@NonNull BrandListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<CarInfo>(getContext(), R.layout.brandlsit_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final CarInfo info) {
                try {
                    int position = helper.getAdapterPosition();
                    // 根据position获取分类的首字母的Char ascii值
                    int section = getSectionForPosition(position);

                    // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
                    if (position == getPositionForSection(section)) {
                        helper.setText(tv_char, info.getInitial());
                        helper.setVisible(tv_char, View.VISIBLE);
                    } else {
                        helper.setVisible(tv_char, View.GONE);
                    }
                    helper.setText(R.id.tv_brandname, info.getName())
                            .getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            map.put(BrandListActivity.INTENTNAME_CARID, String.valueOf(info.getId()));
                            IntentUtils.startFragmentForResult(BrandListFragment.this, getContext(), BrandSubListActivity.class, map);
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
    public void setGetCarInfoPPListResult(List<CarInfo> result) {
        try {
            strlist.addAll(result);
            Collections.sort(strlist, pinyinComparator);
            adapter.addAll(strlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String sortStr = strlist.get(i).getInitial();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return strlist.get(position).getInitial().charAt(0);
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        switch (requestCode) {
            case Activity.RESULT_FIRST_USER:
                try {
                    if (data.getStringExtra(BrandSubListActivity.INTENTNAME_CARINFOCX) == null) {
                        return;
                    }

                    Intent mIntent = new Intent();
                    mIntent.putExtra(BrandSubListActivity.INTENTNAME_CARINFOCX, data.getStringExtra(BrandSubListActivity.INTENTNAME_CARINFOCX));
                    // 设置结果，并进行传送
                    getActivity().setResult(Constants.RETURN_CODE_COMMON, mIntent);
                    getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}