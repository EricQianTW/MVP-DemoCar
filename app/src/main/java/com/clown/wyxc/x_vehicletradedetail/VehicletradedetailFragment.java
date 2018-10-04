package com.clown.wyxc.x_vehicletradedetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.convenientbanner.NetworkImageHolderView;
import com.clown.wyxc.photo.PhotoActivity;
import com.clown.wyxc.scheme.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.VehiclPic;
import com.clown.wyxc.x_bean.VehiclResult;
import com.clown.wyxc.x_bean.x_parambean.VehiclQuery;
import com.clown.wyxc.x_share.ShareActivity;
import com.clown.wyxc.x_share.ShareForSchemeActivity;
import com.clown.wyxc.x_utils.Router;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class VehicletradedetailFragment extends BaseFragment implements VehicletradedetailContract.View {

    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.goods_share)
    TextView goodsShare;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.car_name)
    TextView carName;
    @Bind(R.id.car_price)
    TextView carPrice;
    @Bind(R.id.rl_carinfo)
    RelativeLayout rlCarinfo;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.ll_btns)
    LinearLayout llBtns;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.line1)
    View line1;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.tv_endprice)
    TextView tvEndprice;

    private VehicletradedetailContract.Presenter mPresenter;

    private boolean flagTurning = false;
    private RecyclerAdapter<VehiclPic> adapter;
    private Integer vehiclId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicletradedetail_frg, container, false);
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
        vehiclId = Integer.parseInt(getArguments().getString(VehicletradedetailActivity.INTANTNAME_VEHICLID) == null ? null : getArguments().getString(VehicletradedetailActivity.INTANTNAME_VEHICLID));
        mPresenter.getVehiclById(GSONUtils.paramToJson(new VehiclQuery(user.getId(), vehiclId)));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        //开始自动翻页
        if (flagTurning) {
            convenientBanner.startTurning(5000);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public VehicletradedetailFragment() {
        new VehicletradedetailPresenter(this);
    }

    public static VehicletradedetailFragment newInstance() {
        return new VehicletradedetailFragment();
    }

    private void initAction() {
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.createVehiclOrder(GSONUtils.paramToJson(new VehiclQuery(user.getId(), vehiclId)));
            }
        });
    }

    @Override
    public void setPresenter(@NonNull VehicletradedetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetVehiclByIdResult(final VehiclResult result) {
        List<VehiclPic> array = result.getBannerPicList();
        if (array == null || array.size() == 1) {
            flagTurning = false;
        } else {
            flagTurning = true;
        }

        List<String> strArray = new ArrayList<>();
        for (VehiclPic temp : array) {
            strArray.add(temp.getPic());
        }
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, strArray)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
        if (flagTurning) {
            convenientBanner.startTurning(5000);
        }

        if (result.getDetailPicList().size() > 0) {
            adapter.addAll(result.getDetailPicList());
        }

        carName.setText(result.getName());
        carPrice.setText(result.getStartPrice());

        if (result.getEndPrice() != null && !"".equals(result.getEndPrice())) {
            tvEndprice.setText("指导价"+result.getEndPrice());
            tvEndprice.setVisibility(View.VISIBLE);

            tvEndprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            tvEndprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        }else {
            tvEndprice.setVisibility(View.GONE);
        }

        goodsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(ShareActivity.INTENTNAME_TITLE, result.getGoodsShare().getTitle());
                map.put(ShareActivity.INTENTNAME_CONTENT, result.getGoodsShare().getContents());
                map.put(ShareActivity.INTENTNAME_THUMBIMAGEURL, result.getGoodsShare().getSharePic());
                map.put(ShareActivity.INTENTNAME_CLICKURL, result.getGoodsShare().getShareUrl());
                map.put(ShareActivity.INTENTNAME_TYPE, String.valueOf(result.getGoodsShare().getShareType()));
                map.put(ShareActivity.INTENTNAME_DETAILIMAGEURL, result.getGoodsShare().getDetailPic());
                IntentUtils.startActivity(getContext(), ShareForSchemeActivity.class, map);
            }
        });
    }

    @Override
    public void setCreateVehiclOrderResult(String result) {
        Router.startActivty(getActivity(), result, true);
    }

    private void initView() throws Exception {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        convenientBanner.setLayoutParams(layoutParams);

        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
//        rvIcon.addItemDecoration(new HorizontalItemDecoration
//                .Builder(getContext())
//                .colorResId(R.color.gray_theme)
//                .showLastDivider(true)
//                .build());

        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<VehiclPic>(getContext(), R.layout.common_adp_siglepic_matchwrap) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final VehiclPic info) {
                try {
                    helper.setImageUrl(R.id.iv_pic, info.getBigPic());
                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), PhotoActivity.class);
                            ArrayList<String> mstr = new ArrayList<String>();
                            for (VehiclPic a : adapter.getAll()) {
                                mstr.add(a.getBigPic());
                            }
                            intent.putStringArrayListExtra(Constants.JUMP_STRING, mstr);
                            intent.putExtra(Constants.JUMP_STRING1, helper.getAdapterPosition());
                            startActivity(intent);
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
}
