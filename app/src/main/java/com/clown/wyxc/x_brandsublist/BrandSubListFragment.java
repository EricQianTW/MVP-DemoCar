package com.clown.wyxc.x_brandsublist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.CarInfoCResult;
import com.clown.wyxc.x_bean.CarInfoCX;
import com.clown.wyxc.x_bean.x_parambean.CarInfoQuery;
import com.clown.wyxc.x_brandlist.BrandListActivity;
import com.clown.wyxc.x_common.Constants;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class BrandSubListFragment extends BaseFragment implements BrandSubListContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private BrandSubListContract.Presenter mPresenter;

    private RecyclerAdapter<CarInfoCResult> adapter;

    private int brandId = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brandsublist_frg, container, false);
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
            intData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void intData() {
        Bundle data = getArguments();
        brandId = data.getInt(BrandListActivity.INTENTNAME_CARID);

        mPresenter.getCarInfoCXList(GSONUtils.paramToJson(new CarInfoQuery(brandId, user.getId())));
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

    public BrandSubListFragment() {
        new BrandSubListPresenter(this);
    }

    public static BrandSubListFragment newInstance() {
        return new BrandSubListFragment();
    }

    private void initAction() {

    }

    @Override
    public void setPresenter(@NonNull BrandSubListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<CarInfoCResult>(getContext(), R.layout.brandsub_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final CarInfoCResult info) {
                try {
                    try {
                        helper.setText(R.id.tv_brandname, info.getName());

                        BaseAdapter goodsAdapter = new BaseListAdapter<CarInfoCX>(getActivity(), info.getCxList()) {
                            @Override
                            public View bindView(int position, View convertView, ViewGroup parent) {
                                try {
                                    convertView = null;
                                    final CarInfoCX item = (CarInfoCX) list.get(position);
                                    if (convertView == null) {
                                        convertView = createViewByType();
                                    }
                                    setData(item, convertView, position);
                                    convertView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent mIntent = new Intent();
                                            List<CarInfoCX> arr = new ArrayList<CarInfoCX>();
                                            arr.add(item);
                                            info.setCxList(arr);
                                            mIntent.putExtra(BrandSubListActivity.INTENTNAME_CARINFOCX, GSONUtils.toJson(info));
                                            // 设置结果，并进行传送
                                            getActivity().setResult(Constants.RETURN_CODE_COMMON, mIntent);
                                            getActivity().finish();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return convertView;
                            }

                            private View createViewByType() {
                                return mInflater.inflate(R.layout.brandsub_adp_brand, null);
                            }

                            private void setData(CarInfoCX item, View convertView, int position) {
                                try {
                                    TextView goods_name = ViewHolder.get(convertView, R.id.brand_name);
                                    goods_name.setText(item.getName());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        helper.setAdapter(R.id.rv_item, goodsAdapter);
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
    public void setGetCarInfoCXListResult(List<CarInfoCResult> result) {
        try {
            if (result.size() > 0) {
                adapter.addAll(result);
            } else {
                T.showShort(getContext(), "没有更多的数据了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}