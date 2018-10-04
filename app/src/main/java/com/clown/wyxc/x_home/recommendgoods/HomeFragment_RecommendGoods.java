package com.clown.wyxc.x_home.recommendgoods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.Coupon;
import com.clown.wyxc.x_bean.x_parambean.CouponQuery;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_utils.Router;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

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

    private HomeContract_RecommendGoods.Presenter mPresenter;
    private RecyclerAdapter<Coupon> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_recgoods, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initAdapter();

            initViews();
        } catch (Exception e) {
            Logger.e(e, TAG);
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
            Logger.e(e, TAG);
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
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<Coupon>(getContext(), R.layout.home_adp_activity) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final Coupon info) {
                try {
                    try {
                        helper.setText(R.id.activitytime_tv, "活动时间:"+info.getBeginDateTime()+"-"+info.getEndDateTime())
                                .setImageUrl(R.id.activity_img, info.getPic())
                                .getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.startActivty(getActivity(),info.getClickUrl(),false);
                            }
                        });
                        ;
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
    public void setGetCouponListByQueryResult(List<Coupon> result) {
        try {
            if (result.size() > 0) {
                adapter.replaceAll(result);
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    private void initData() throws Exception {
        mPresenter.getCouponListByQuery(GSONUtils.toJson(new CouponQuery(Constants.FAVOURABLE_TYPE_YOUHUIHUODONG,aMapLocation.getAdCode(),user.getId())));
    }

}
