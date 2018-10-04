package com.clown.wyxc.x_companyservices.servicefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.ServiceItems;
import com.clown.wyxc.x_bean.ServiceItemsResult;
import com.clown.wyxc.x_bean.x_parambean.ServiceItemsId;
import com.clown.wyxc.x_bean.x_parambean.ServiceOrderQuery;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleCardFragment extends BaseFragment implements SimpleCardContract.View {

    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_nodata)
    TextView tvNodata;
    @Bind(R.id.ll_main)
    FrameLayout llMain;
    private RecyclerAdapter<ServiceItems> adapter;

    private ServiceItemsResult mResult;
    private int mMerchantId;

    private SimpleCardContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simplecard_frg, container, false);
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

    private void initData() {
        try {
            if (mResult.getList().size() > 0) {
                adapter.addAll(mResult.getList());
                tvNodata.setVisibility(View.GONE);
            } else {
                T.showShort(getContext(), "没有更多的数据了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
//        mPresenter.getMerchantServiceItemsListByQuery(GSONUtils.paramToJson(new ServiceItemsQuery(mResult.getId(),user.getId())));
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvIcon.setAdapter(adapter);
    }

    public SimpleCardFragment() {
        new SimpleCardPresenter(this);
    }

    public static SimpleCardFragment newInstance(ServiceItemsResult result, int merchantId) {
        SimpleCardFragment simpleCardFragment = new SimpleCardFragment();
        simpleCardFragment.mResult = result;
        simpleCardFragment.mMerchantId = merchantId;
        return simpleCardFragment;
    }

    private void initAction() throws Exception {

    }

    @Override
    public void setPresenter(@NonNull SimpleCardContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<ServiceItems>(getContext(), R.layout.simplecard_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final ServiceItems info) {
                try {
                    try {
                        helper.setText(R.id.tv_servicename, info.getName());
                        helper.setText(R.id.tv_price, String.valueOf(info.getPrice()));
                        helper.setText(R.id.tv_content, info.getDetail());

                        helper.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (info.getCanPay() != null && info.getCanPay() == 1) {
                                    List<ServiceItemsId> ids = new ArrayList<ServiceItemsId>();
                                    ServiceItemsId id = new ServiceItemsId(info.getId());
                                    ids.add(id);
                                    mPresenter.addOrderByService(GSONUtils.paramToJson(new ServiceOrderQuery(user.getId(), mMerchantId, ids)));
                                } else {
                                    T.showShort(getContext(), "该项目暂不提供预约服务");
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
    public void setGetMerchantServiceItemsListByQueryResult(List<ServiceItemsResult> result) {

    }

    @Override
    public void setAddOrderByServiceResult(String result) {
        HashMap<String, String> map = new HashMap<>();
        map.put(PayOrderActivity.INTENTNAME_ORDERNO, String.valueOf(result));
        IntentUtils.startActivity(getActivity(), PayOrderActivity.class, map);
    }

}