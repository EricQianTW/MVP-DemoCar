package com.clown.wyxc.x_home.icon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.Banner;
import com.clown.wyxc.x_bean.x_parambean.BannerQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_utils.Router;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_Icon extends BaseFragment implements HomeContract_Icon.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.cvMain)
    LinearLayout cvMain;
    private HomeContract_Icon.Presenter mPresenter;
    public final static String INTENTNAME_TYPE = "intentname_type";
    private GridLayoutManager mLayoutManager;
    private RecyclerAdapter<Banner> adapter;
    private int typeId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_icon, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            Bundle data = getArguments();
            typeId = data.getInt(INTENTNAME_TYPE);

            initAdapter();
            initViews();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void initViews() throws Exception {
        if (typeId == Constants.BANNER_TYPE_SHANGCHENGICON) {
            mLayoutManager = new GridLayoutManager(getActivity(), 5);
        }else{
            mLayoutManager = new GridLayoutManager(getActivity(), 4);
        }
        rvIcon.setLayoutManager(mLayoutManager);
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.white)
                .sizeResId(R.dimen.height_explore_divider_10)
                .build());
        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.white)
                .sizeResId(R.dimen.height_explore_divider_10)
                .build());
        rvIcon.setAdapter(adapter);
        rvIcon.setHasFixedSize(true);
        rvIcon.setItemAnimator(new DefaultItemAnimator());
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<Banner>(getContext(), R.layout.home_adp_icon) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final Banner info) {
                helper.setText(R.id.tv_title, info.getTitle())
                        .setImageUrl(R.id.iv_pic, info.getPic())
                        .getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Router.startActivty(getActivity(), info.getUrl(), false);
                    }
                });
                helper.getItemView().setTag("hello world");
            }
        };
    }

    private void initData() throws Exception {
        mPresenter.getBannerListByQuery(GSONUtils.toJson(new BannerQuery(typeId, aMapLocation.getAdCode(), user.getId())));
    }

    public static HomeFragment_Icon newInstance() {
        return new HomeFragment_Icon();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(HomeContract_Icon.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetBannerListByQueryResult(List<Banner> result) {
        adapter.replaceAll(result);
    }
}
