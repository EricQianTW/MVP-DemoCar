package com.clown.wyxc.x_mine.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.CenterMenu;
import com.clown.wyxc.x_bean.CenterMenuResult;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_utils.Router;
import com.nostra13.universalimageloader.core.ImageLoader;
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
public class MineFragment_List extends BaseFragment implements MineContract_List.View {
    @Bind(R.id.recycler)
    RecyclerView rvIcon;

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    private MineContract_List.Presenter mPresenter;
    private RecyclerAdapter<CenterMenuResult> adapter;
    private List<CenterMenuResult> mData = new ArrayList<>();

    public MineFragment_List() {
        new MinePresenter_List(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_frg_list, container, false);

        ButterKnife.bind(this, view);
        mPresenter.start();
        try {
            initAdapter();
            initView();
            mPresenter.getCarInfoPPList(GSONUtils.paramToJson(new QueryUserId(user.getId())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAction();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static MineFragment_List newInstance() {
        return new MineFragment_List();
    }


    private void initAction() {

    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_10)
                .showLastDivider()
                .build());
        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<CenterMenuResult>(getContext(), mData, R.layout.mine_frg_list_title) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final CenterMenuResult info) {
                final BaseAdapter itemAdapter = new BaseListAdapter<CenterMenu>(getActivity(), info.getList()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        try {
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(list.get(position), convertView, position);
                        } catch (Exception e) {
                            Logger.e(e, TAG);
                            e.printStackTrace();
                        }
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.mine_frg_list_recy, null);
                    }

                    private void setData(final CenterMenu item, View convertView, final int position) throws Exception {
                        ImageView iv_image = ViewHolder.get(convertView, R.id.iv_image);
                        TextView tv_text = ViewHolder.get(convertView, R.id.tv_text);
                        RelativeLayout relat = ViewHolder.get(convertView, R.id.relat);
                        tv_text.setText(item.getName());
                        ImageLoader.getInstance().displayImage(item.getPic(), iv_image);
                        relat.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.startActivty(getActivity(),item.getUrl(),false);
                            }
                        });
                    }
                };
                helper.setAdapter(R.id.recycler, itemAdapter);
            }
        };
    }

    @Override
    public void setPresenter(@NonNull MineContract_List.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetCarInfoPPListResult(List<CenterMenuResult> result) {
        adapter.addAll(result);
    }
}
