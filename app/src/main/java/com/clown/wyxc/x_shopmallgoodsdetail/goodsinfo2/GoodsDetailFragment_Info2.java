package com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.GoodsDetailInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_Info2 extends BaseFragment implements GoodsDetailContract_Info2.View {

    @Bind(R.id.goods_paizi)
    TextView goodsPaizi;
    @Bind(R.id.goods_xinghao)
    TextView goodsXinghao;
    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private GoodsDetailContract_Info2.Presenter mPresenter;
    private GoodsDetailInfo info = new GoodsDetailInfo();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_info2, container, false);
        ButterKnife.bind(this, view);

        try {
            mPresenter.getGoodsDetailInfo();
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
//        mPresenter.start();
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

    public GoodsDetailFragment_Info2() {

    }

    public static GoodsDetailFragment_Info2 newInstance() {
        return new GoodsDetailFragment_Info2();
    }

    @Override
    public void setPresenter(GoodsDetailContract_Info2.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void onClick() {
    }

    @Override
    public void initView(GoodsDetailInfo info) {
        goodsPaizi.setText("品牌：" + info.getPaizi());
        goodsXinghao.setText("型号：" + info.getXinghao());
    }

}
