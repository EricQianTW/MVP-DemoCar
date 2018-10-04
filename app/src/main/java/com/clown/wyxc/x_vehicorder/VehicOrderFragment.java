package com.clown.wyxc.x_vehicorder;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.VehiclListResult;
import com.clown.wyxc.x_bean.x_parambean.QueryUserIdPage;
import com.clown.wyxc.x_qrcode.QrCodeActivity;
import com.clown.wyxc.x_vehicletradedetail.VehicletradedetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class VehicOrderFragment extends BaseFragment implements VehicOrderContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private VehicOrderContract.Presenter mPresenter;
    private RecyclerAdapter<VehiclListResult> adapter;
    private int pageIndex = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicorder_frg, container, false);
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
        mPresenter.getVehiclOrderList(GSONUtils.paramToJson(new QueryUserIdPage(user.getId(), pageIndex)));
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .showLastDivider(true)
                .build());

        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<VehiclListResult>(getContext(), R.layout.vehicorder_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final VehiclListResult info) {
                try {
                    helper.setText(R.id.tv_carName, info.getName())
                            .setText(R.id.tv_price, info.getStartPrice())
                            .setImageUrl(R.id.img_carPic, info.getFirstPic())
                            .getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            map.put(VehicletradedetailActivity.INTANTNAME_VEHICLID,String.valueOf(info.getVehiclId()));
                            IntentUtils.startActivity(getContext(), VehicletradedetailActivity.class,map);
                        }
                    });

                    helper.setOnClickListener(R.id.tv_erweima, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap map = new HashMap();
                            map.put(QrCodeActivity.INTANTNAME_STRING, info.getErweima());
                            IntentUtils.startActivity(getActivity(), QrCodeActivity.class, map);
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
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public VehicOrderFragment() {
        new VehicOrderPresenter(this);
    }

    public static VehicOrderFragment newInstance() {
        return new VehicOrderFragment();
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull VehicOrderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void setGetVehiclOrderListResult(List<VehiclListResult> result) {
        if (result.size() > 0) {
            adapter.addAll(result);
            pageIndex++;
        }
    }
}