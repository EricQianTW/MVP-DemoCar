package com.clown.wyxc.x_shopmallgoodsdetail;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.GoodsInfoResult;
import com.clown.wyxc.x_bean.x_parambean.GoodsIdQuery;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_shopcar.ShopCarActivity;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsassess.GoodsDetailFragment_Assess;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsassess.GoodsDetailPresenter_Assess;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsattribute.GoodsDetailFragment_Attr;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsattribute.GoodsDetailPresenter_Attr;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo.GoodsDetailFragment_Info;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsinfo.GoodsDetailPresenter_Info;
import com.clown.wyxc.x_shopmallgoodsdetail.goodspictureshow.GoodsPictureShowFragment;
import com.clown.wyxc.x_shopmallgoodsdetail.goodspictureshow.GoodsPictureShowPresenter;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsplaceorder.GoodsDetailFragment_PlaceOrder;
import com.clown.wyxc.x_shopmallgoodsdetail.goodsplaceorder.GoodsDetailPresenter_PlaceOrder;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by eric_qiantw on 16/4/21.
 */
public class GoodsDetailActivity extends BaseAppCompatActivity implements GoodsDetailContract.View {
    @Bind(R.id.infoFrame)
    FrameLayout infoFrame;
    @Bind(R.id.tab_comment)
    SegmentTabLayout tabComment;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomsheet;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.attrFrame)
    FrameLayout attrFrame;
    @Bind(R.id.setMealFrame)
    CardView setMealFrame;
    @Bind(R.id.webViewFrame)
    FrameLayout webViewFrame;
    @Bind(R.id.ns_home)
    NestedScrollView nsHome;

    public final static String INTENTNAME_GOODSID = "goodsid";
    public final static String INTENTNAME_GOODSSTOCKID = "goodsStockId";
    // fromcode:普通0;fromcode:活动1;
    public final static String INTENTNAME_FROMCODE = "fromCode";
    @Bind(R.id.collect)
    TextView collect;
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.tv_addtocart)
    TextView tvAddtocart;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.tv_kefu)
    TextView tvKefu;
    @Bind(R.id.tv_gouwuche)
    TextView mTvGouwuche;
    @Bind(R.id.wuliuFrame)
    FrameLayout mWuliuFrame;

    private String[] mTitles = {"商品详情", "评价列表"};
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private List<Integer> fragmentIds = new ArrayList<Integer>();

    private GoodsDetailFragment_Info infoFragment;
    private GoodsDetailFragment_PlaceOrder placeOrderFragment;
    private GoodsPictureShowFragment pictureShowFragment;
    private GoodsDetailFragment_Assess assessFragment;
    private GoodsDetailFragment_Attr attrFragment;

    private GoodsDetailContract.Present mPresent;
    private int goodsId;
    private GoodsInfoResult msgGoodsInfoByMtApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopmallgoodsdetail_act);
        ButterKnife.bind(this);
        mPresent = new GoodsDetailPresent(this);
        try {
            initFragment();
            initView();
            initAction();
            initData();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    private void initData() {
        mPresent.getGoodsById(GSONUtils.paramToJson(new GoodsIdQuery(user.getId(), goodsId, aMapLocation.getAdCode())));
    }

    private void initFragment() throws Exception {
        Bundle data = new Bundle();
        goodsId = Integer.parseInt(getIntent().getStringExtra(INTENTNAME_GOODSID) == null ? "-1" : getIntent().getStringExtra(INTENTNAME_GOODSID));
        data.putInt(INTENTNAME_GOODSID, goodsId);
        data.putInt(INTENTNAME_GOODSSTOCKID, Integer.parseInt(getIntent().getStringExtra(INTENTNAME_GOODSSTOCKID) == null ? "-1" : getIntent().getStringExtra(INTENTNAME_GOODSSTOCKID)));
        if (getIntent().getStringExtra(INTENTNAME_FROMCODE) != null) {
            data.putInt(INTENTNAME_FROMCODE, Integer.parseInt(getIntent().getStringExtra(INTENTNAME_FROMCODE)));
        } else {
            data.putInt(INTENTNAME_FROMCODE, -1);
        }

        infoFragment = initInfoFragment();
        placeOrderFragment = initPlaceOrderFragment();
        pictureShowFragment = initPictureShowFragment();
        assessFragment = initAssessFragment();
        attrFragment = initAttrFragment();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragments, fragmentIds);

        infoFragment.setArguments(data);
        placeOrderFragment.setArguments(data);
        pictureShowFragment.setArguments(data);
        assessFragment.setArguments(data);
        attrFragment.setArguments(data);

        new GoodsDetailPresenter_Info(infoFragment);
        new GoodsDetailPresenter_PlaceOrder(placeOrderFragment);
        new GoodsPictureShowPresenter(pictureShowFragment);
        new GoodsDetailPresenter_Assess(assessFragment);
        new GoodsDetailPresenter_Attr(attrFragment);

        // 先加载页面数据
        placeOrderFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
        placeOrderFragment.dismiss();
    }

    public void removeFragment(Fragment fragment) throws Exception {
        ActivityUtils.removeFragmentFromActivity(getSupportFragmentManager(), fragment);
    }

    public void removeSetMealFrame() {
        setMealFrame.setVisibility(View.GONE);
    }

    @NonNull
    private GoodsDetailFragment_Info initInfoFragment() throws Exception {
        GoodsDetailFragment_Info infoFragment = (GoodsDetailFragment_Info) getSupportFragmentManager().findFragmentById(R.id.infoFrame);

        if (infoFragment == null) {
            infoFragment = GoodsDetailFragment_Info.newInstance();
        }

        // 工具栏fragment
        fragments.add(infoFragment);
        fragmentIds.add(R.id.infoFrame);
        return infoFragment;
    }

    @NonNull
    private GoodsDetailFragment_PlaceOrder initPlaceOrderFragment() throws Exception {
        GoodsDetailFragment_PlaceOrder webViewFragment = (GoodsDetailFragment_PlaceOrder) getSupportFragmentManager().findFragmentById(R.id.bottomsheet);

        if (webViewFragment == null) {
            webViewFragment = GoodsDetailFragment_PlaceOrder.newInstance();
        }

        return webViewFragment;
    }

    @NonNull
    private GoodsPictureShowFragment initPictureShowFragment() throws Exception {
        GoodsPictureShowFragment webViewFragment = (GoodsPictureShowFragment) getSupportFragmentManager().findFragmentById(R.id.webViewFrame);

        if (webViewFragment == null) {
            webViewFragment = GoodsPictureShowFragment.newInstance();
        }
        fragments.add(webViewFragment);
        fragmentIds.add(R.id.webViewFrame);
        return webViewFragment;
    }

    @NonNull
    private GoodsDetailFragment_Attr initAttrFragment() throws Exception {
        GoodsDetailFragment_Attr webViewFragment = (GoodsDetailFragment_Attr) getSupportFragmentManager().findFragmentById(R.id.webViewFrame);

        if (webViewFragment == null) {
            webViewFragment = GoodsDetailFragment_Attr.newInstance();
        }
        fragments.add(webViewFragment);
        fragmentIds.add(R.id.attrFrame);
        return webViewFragment;
    }

    @NonNull
    private GoodsDetailFragment_Assess initAssessFragment() throws Exception {
        GoodsDetailFragment_Assess webViewFragment = (GoodsDetailFragment_Assess) getSupportFragmentManager().findFragmentById(R.id.webViewFrame);

        if (webViewFragment == null) {
            webViewFragment = GoodsDetailFragment_Assess.newInstance();
        }

        return webViewFragment;
    }

    private void initView() throws Exception {
        setSupportActionBar(toolbar);
        initBack();

        tabComment.setTabData(mTitles);
    }

    private void initAction() throws Exception {
        tabComment.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    ActivityUtils.replaceFragmentFromActivity(getSupportFragmentManager(), R.id.webViewFrame, pictureShowFragment);
                } else if (position == 1) {
                    ActivityUtils.replaceFragmentFromActivity(getSupportFragmentManager(), R.id.webViewFrame, assessFragment);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        tvBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrderFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
            }
        });

        tvAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrderFragment.show(getSupportFragmentManager(), R.id.bottomsheet);
            }
        });

        mTvGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(GoodsDetailActivity.this, ShopCarActivity.class);
            }
        });

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.cilckCollection(GSONUtils.paramToJson(new QueryId(goodsId, user.getId())));
            }
        });
    }

    @Override
    public void setPresenter(GoodsDetailContract.Present presenter) {

    }

    @Override
    public void setGetGoodsByIdResult(GoodsInfoResult result) {
        msgGoodsInfoByMtApp = result;
        tvKefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(GoodsDetailActivity.this);
            }
        });
    }

    @Override
    public void setCilckCollectionResult(int result) {
        try {
            if (result == 1) {
                Drawable top = getResources().getDrawable(R.drawable.collectproredclick_x3);
                collect.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                T.showShort(getApplicationContext(), "收藏成功");
            } else {
                Drawable top = getResources().getDrawable(R.drawable.collectproblack_x3);
                collect.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
                T.showShort(getApplicationContext(), "取消收藏");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCollected(int i) {
        if (i > 0) {
            Drawable top = getResources().getDrawable(R.drawable.collectproredclick_x3);
            collect.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        } else {
            Drawable top = getResources().getDrawable(R.drawable.collectproblack_x3);
            collect.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        }
    }

    public void showAlertDialog(Context context) {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("客服热线");
            builder.setMessage("是否拨打" + Constants.HOTLINE);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + Constants.HOTLINE));
                    startActivity(intent);

                }
            });

            builder.create();
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
