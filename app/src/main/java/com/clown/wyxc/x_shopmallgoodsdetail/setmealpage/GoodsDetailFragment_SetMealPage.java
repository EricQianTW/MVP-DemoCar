package com.clown.wyxc.x_shopmallgoodsdetail.setmealpage;

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
import com.clown.wyxc.bean.MsgGoodsPackage;
import com.clown.wyxc.bean.MsgSaleInfo;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_SetMealPage extends BaseFragment implements GoodsDetailContract_SetMealPage.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_setmealpage)
    LinearLayout llSetmealpage;
    @Bind(R.id.goods_salvenum)
    TitleTextview goodsSalvenum;
    @Bind(R.id.taocanjia)
    TitleTextview taocanjia;



    private RecyclerAdapter<MsgSaleInfo> adapter;
    private GoodsDetailContract_SetMealPage.Presenter mPresenter;

    public GoodsDetailFragment_SetMealPage() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_setmealpage, container, false);
        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            mPackage = GSONUtils.fromJson(data.getString("position", "").toString(), MsgGoodsPackage.class);
            initView();
            initAdapter();
            initViews(view);
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initAction() {
        llSetmealpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, String> map = new HashMap();
//                map.put(SetMealActivity.INTENTNAME_SETMEALID, String.valueOf(mPackage.getId()));
//                IntentUtils.startActivity(getActivity(), SetMealActivity.class, map);
            }
        });
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

    private void initViews(View view) throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_1)
                .showLastDivider(false)
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initData() {
        adapter.addAll(mPackage.getPackageGoodsList());
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<MsgSaleInfo>(getContext(), R.layout.common_adp_siglepic) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final MsgSaleInfo info) {
                final int position = helper.getAdapterPosition();
                helper.setImageUrl(R.id.iv_pic, info.getGoodsImage());
                helper.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = new HashMap();
                        map.put(GoodsDetailActivity.INTENTNAME_GOODSID, String.valueOf(info.getId()));
                        IntentUtils.startActivity(getActivity(), GoodsDetailActivity.class, map);

                    }
                });
                helper.getItemView().setTag("hello world");
            }
        };
    }

    private MsgGoodsPackage mPackage;

    public static GoodsDetailFragment_SetMealPage newInstance() {
        return new GoodsDetailFragment_SetMealPage();
    }

    @Override
    public void setPresenter(GoodsDetailContract_SetMealPage.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void initView(){
        taocanjia.setTt_text_back(String.valueOf(BigDecimalUtil.df.format(mPackage.getPackagePrice())));
        goodsSalvenum.setTt_text_back(mPackage.getPackageName());
    }
}
