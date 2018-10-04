package com.clown.wyxc.x_shopmallgoodsdetail.goodspictureshow;

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

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.photo.PhotoActivity;
import com.clown.wyxc.scheme.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.GoodsInfoResult;
import com.clown.wyxc.x_bean.GoodsPic;
import com.clown.wyxc.x_bean.x_parambean.GoodsIdQuery;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class GoodsPictureShowFragment extends BaseFragment implements GoodsPictureShowContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;

    private GoodsPictureShowContract.Presenter mPresenter;

    private RecyclerAdapter<GoodsPic> adapter;
    private int goodsId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodspictureshow_frg, container, false);
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
        Bundle data = getArguments();
        goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);
        mPresenter.getGoodsById(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(),goodsId,aMapLocation.getAdCode())));
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    public GoodsPictureShowFragment() {
        new GoodsPictureShowPresenter(this);
    }

    public static GoodsPictureShowFragment newInstance() {
        return new GoodsPictureShowFragment();
    }

    private void initAction() throws Exception {

    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<GoodsPic>(getContext(), R.layout.common_adp_siglepic_matchwrap) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final GoodsPic info) {
                try {
                    try {
                        helper.setImageUrl(R.id.iv_pic, info.getBigPic());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    helper.getItemView().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), PhotoActivity.class);
                            ArrayList<String> mstr = new ArrayList<String>();
                            for (GoodsPic a : adapter.getAll()) {
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

    @Override
    public void setPresenter(@NonNull GoodsPictureShowContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetGoodsByIdResult(GoodsInfoResult result) {
        try {
            if (result.getGoodsDetailPic().size() >= 0) {
                adapter.addAll(result.getGoodsDetailPic());
            } else {
                S.showShort(llMain, "没有更多记录了");
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}