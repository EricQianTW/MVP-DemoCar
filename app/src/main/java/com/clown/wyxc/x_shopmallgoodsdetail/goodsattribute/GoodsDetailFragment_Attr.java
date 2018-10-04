package com.clown.wyxc.x_shopmallgoodsdetail.goodsattribute;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.CommonPopupWindows;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.DensityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.GoodsService;
import com.clown.wyxc.x_bean.x_parambean.GoodsIdQuery;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_Attr extends BaseFragment implements GoodsDetailContract_Attr.View {
    @Bind(R.id.ll_attr_root)
    LinearLayout llAttrRoot;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;

    private GoodsDetailContract_Attr.Presenter mPresenter;

    private RecyclerAdapter<GoodsService> adapter;
    private RecyclerView recyclerView;
    private List<GoodsService> mArray = new ArrayList<>();
    private int goodsId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_attr, container, false);
        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);

            initData();

            initAction();
        } catch (Exception e) {
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

    private void initData() {
        mPresenter.getGoodsServiceGoodsId(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(),goodsId,aMapLocation.getAdCode())));
    }

    private void initAction() {
        llAttrRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    openBottomSheet();
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
    }

    private void openBottomSheet() throws Exception {
        showPopupWindow(mArray);
    }

    private void initBottomSheet() throws Exception {
        recyclerView = (RecyclerView) LayoutInflater.from(getActivity())
                .inflate(R.layout.common_recyclerview_list, null, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_88)
                .sizeResId(R.dimen.height_explore_divider_1)
                .build());
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<GoodsService>(getContext(), R.layout.common_adp_pictext) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsService info) {
                helper.setText(R.id.textview, info.getServiceTitle()).setImageResource(R.id.imageview, R.drawable.serviceprored_x3);
                helper.getItemView().setTag(TAG);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() throws Exception {
        // 底部弹出画面的初始化
        initBottomSheet();
        initAdapter();

        int size = mArray.size();
        if (mArray.size() > 3) {
            size = 3;
        }
        for (int i = 0; i < size; i++) {
            TextView tv = new TextView(getActivity());
            tv.setText(mArray.get(i).getServiceTitle());
            Drawable nav = ContextCompat.getDrawable(getActivity(), R.drawable.serviceprored_x3);
            nav.setBounds(0, 0, DensityUtils.dip2px(getContext(),20), DensityUtils.dip2px(getContext(),20));
            tv.setCompoundDrawables(null, null, nav, null);
            tv.setCompoundDrawablePadding(5);
            llAttrRoot.addView(tv);
        }
    }

    public GoodsDetailFragment_Attr() {

    }

    public static GoodsDetailFragment_Attr newInstance() {
        return new GoodsDetailFragment_Attr();
    }

    @Override
    public void setPresenter(GoodsDetailContract_Attr.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void showPopupWindow(List<GoodsService> array) {

        RecyclerAdapter adapter = new RecyclerAdapter<GoodsService>(getContext(), R.layout.goodsdetail_adp_attr) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsService info) {
                if(info.getServiceTitle() != null && !"".equals(info.getServiceTitle())){
                    helper.setText(R.id.title, info.getServiceTitle());
                }
                if(info.getServiceContent() != null && !"".equals(info.getServiceContent())){
                    helper.setText(R.id.comment,info.getServiceContent());
                }
                if(info.getServiceImage() != null && !"".equals(info.getServiceImage())){
                    helper.setImageUrl(R.id.image,info.getServiceImage());
                }
            }
        };

        CommonPopupWindows windows = new CommonPopupWindows(getActivity(), llAttrRoot, array,adapter);
        windows.setBottomText("返回");
        windows.setHeaderText("服务说明");
    }

    @Override
    public void setGetGoodsServiceGoodsIdResult(List<GoodsService> result) {
        try {
            if(result.size() > 0){
                mArray.addAll(result);
                initView();
            }else{
                rlMain.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}
