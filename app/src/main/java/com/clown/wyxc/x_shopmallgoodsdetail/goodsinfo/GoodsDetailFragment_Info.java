package com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.GoodsDetailInfo;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.components.convenientbanner.NetworkImageHolderView;
import com.clown.wyxc.photo.PhotoActivity;
import com.clown.wyxc.scheme.Constants;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.GoodsInfoResult;
import com.clown.wyxc.x_bean.GoodsPic;
import com.clown.wyxc.x_bean.x_parambean.GoodsIdQuery;
import com.clown.wyxc.x_share.ShareActivity;
import com.clown.wyxc.x_share.ShareForSchemeActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class GoodsDetailFragment_Info extends BaseFragment implements GoodsDetailContract_Info.View {
    @Bind(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @Bind(R.id.goods_name)
    TextView goodsName;
    @Bind(R.id.goods_price)
    TitleTextview goodsPrice;
    @Bind(R.id.goods_share)
    TextView goodsShare;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.goods_fright)
    TitleTextview mGoodsFright;
    @Bind(R.id.goods_salesvolume)
    TitleTextview mGoodsSalesvolume;
    @Bind(R.id.tv_address)
    TextView mTvAddress;


    private GoodsDetailContract_Info.Presenter mPresenter;
    private GoodsDetailInfo info = new GoodsDetailInfo();
    private int goodsId;
    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopdetail_frg_info, container, false);
        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);

            dialog = new ProgressDialog(getContext());
            dialog.setMessage("加载中...");

            dialog.show();
            mPresenter.getGoodsById(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(),goodsId,aMapLocation.getAdCode())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, width);
        convenientBanner.setLayoutParams(layoutParams);
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

    public GoodsDetailFragment_Info() {

    }

    public static GoodsDetailFragment_Info newInstance() {
        return new GoodsDetailFragment_Info();
    }

    @Override
    public void setPresenter(GoodsDetailContract_Info.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.convenientBanner)
    public void onClick() {
    }

    @Override
    public void setGetGoodsByIdResult(final GoodsInfoResult result) {
        try {
            List<String> strArray = new ArrayList<>();
            for (GoodsPic temp : result.getGoodsBanner()) {
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
                            Intent intent = new Intent(getActivity(), PhotoActivity.class);
                            ArrayList<String> mstr = new ArrayList<String>();
                            for (GoodsPic a : result.getGoodsBanner()) {
                                mstr.add(a.getBigPic());
                            }
                            intent.putStringArrayListExtra(Constants.JUMP_STRING, mstr);
                            intent.putExtra(Constants.JUMP_STRING1, position);
                            startActivity(intent);
                        }
                    });

            try {
                if (result.getName() != null) {
                    goodsName.setText(result.getName());
                }
                if (result.getPriceRange() != null) {
                    goodsPrice.setTt_text_back("￥"+BigDecimalUtil.df.format(new BigDecimal(result.getPriceRange())));
                }
                if (result.getSellStock() != null) {
                    mGoodsSalesvolume.setTt_text_back(String.valueOf(result.getSellStock()));
                }
                if (result.getPostage() != null) {
                    mGoodsFright.setTt_text_back("￥"+ BigDecimalUtil.df.format(new BigDecimal(result.getPostage())));
                }else{
                    mGoodsFright.setTt_text_back("￥0.00");
                }
                if (result.getGoodsAddress() != null) {
                    mTvAddress.setText(result.getGoodsAddress());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            GoodsDetailActivity activity = (GoodsDetailActivity) getActivity();
            activity.setCollected(result.getIsCollection());

            goodsShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HashMap map = new HashMap();
                    map.put(ShareActivity.INTENTNAME_TITLE,result.getGoodsShare().getTitle());
                    map.put(ShareActivity.INTENTNAME_CONTENT,result.getGoodsShare().getContents());
                    map.put(ShareActivity.INTENTNAME_THUMBIMAGEURL,result.getGoodsShare().getSharePic());
                    map.put(ShareActivity.INTENTNAME_CLICKURL,result.getGoodsShare().getShareUrl());
                    map.put(ShareActivity.INTENTNAME_TYPE,result.getGoodsShare().getShareType());
                    map.put(ShareActivity.INTENTNAME_DETAILIMAGEURL,result.getGoodsShare().getDetailPic());
                    IntentUtils.startActivity(getContext(), ShareForSchemeActivity.class,map);
                }
            });
            dialog.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
