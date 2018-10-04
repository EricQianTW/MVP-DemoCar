package com.clown.wyxc.x_shopmallgoodsdetail.goodswebview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.webview.MyWebView;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_WebView extends BaseFragment implements GoodsDetailContract_WebView.View {

    @Bind(R.id.goods_webview)
    MyWebView goodsWebview;
    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private GoodsDetailContract_WebView.Presenter mPresenter;
    private int goodsid;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goodsdetail_frg_webview, container, false);
        ButterKnife.bind(this, view);

        try {
            getData();
            mPresenter.getGoodsDetailInfo("", 0, goodsid);
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

    public GoodsDetailFragment_WebView() {

    }

    public static GoodsDetailFragment_WebView newInstance() {
        return new GoodsDetailFragment_WebView();
    }

    @Override
    public void setPresenter(GoodsDetailContract_WebView.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void onClick() {
    }

    @Override
    public void setWebsite(String info) {
        goodsWebview.loadUrl(info);
    }

    public void getData()throws Exception {
        Bundle bundle = getArguments();
        goodsid = bundle.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);
    }
}
