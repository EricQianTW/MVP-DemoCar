package com.clown.wyxc.x_shopmallgoodsdetail.goodswuliu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.DensityUtils;
import com.clown.wyxc.x_bean.MsgInGoodsExpress;
import com.clown.wyxc.x_bean.MsgInGoodsExpressDesc;
import com.clown.wyxc.x_shopmallgoodsdetail.GoodsDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cc on 2017/5/28.
 */

public class GoodsWuliuFragment extends BaseFragment implements GoodsWuliuContract.View {

    @Bind(R.id.ll_attr_root)
    LinearLayout llAttrRoot;
    @Bind(R.id.main_view)
    LinearLayout mainView;
    @Bind(R.id.tv_songdaoriqi)
    TextView tvSongdaoriqi;
    @Bind(R.id.tv_jinkoushui)
    TextView tvJinkoushui;

    private int goodsId;

    private GoodsWuliuContract.Presenter mPresenter;

    @Override
    public void setPresenter(GoodsWuliuContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goodswuliu, container, false);
        ButterKnife.bind(this, view);

        Bundle data = getArguments();
        goodsId = data.getInt(GoodsDetailActivity.INTENTNAME_GOODSID);

        mPresenter.getGoodsDetailInfo(user.getId(), goodsId, "");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static GoodsWuliuFragment newInstance() {
        return new GoodsWuliuFragment();
    }

    @Override
    public void getGoodsDetailInfoResult(MsgInGoodsExpress msgInGoodsExpress) {
        tvSongdaoriqi.setText(msgInGoodsExpress.getTitle());
        tvJinkoushui.setText("进口税："+ BigDecimalUtil.df.format(msgInGoodsExpress.getIngoods().getGoodstax().getTaxAmt()));
        int size = msgInGoodsExpress.getIngoods().getLiGoodsExpressDesc().size();
        for (int i = 0; i < msgInGoodsExpress.getIngoods().getLiGoodsExpressDesc().size(); i++) {
            MsgInGoodsExpressDesc msgInGoodsExpressDesc = msgInGoodsExpress.getIngoods().getLiGoodsExpressDesc().get(i);
            if (i != 0) {
                View view = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        DensityUtils.dip2px(getContext(),50),
                        DensityUtils.dip2px(getContext(),2) //创建保存布局参数的对象
                );
                params.leftMargin = 30;
                params.rightMargin = 30;
                view.setBackgroundColor(getResources().getColor(R.color.green_85ba1a));
                view.setLayoutParams(params);
                llAttrRoot.addView(view);
            }

            TextView tv = new TextView(getActivity());
            LinearLayout.LayoutParams paramsTv = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT //创建保存布局参数的对象
            );
            paramsTv.gravity = Gravity.CENTER_HORIZONTAL;
            tv.setLayoutParams(paramsTv);
            tv.setText(msgInGoodsExpressDesc.getTitle());
            int pic = -1;
            if (size == 2) {
                if (i == 0) {
                    pic = R.drawable.mapgreen;
                } else {
                    pic = R.drawable.destinationgrew;
                }
            } else {
                if (i == 0) {
                    pic = R.drawable.mapgreen;
                } else if (i == 1) {
                    pic = R.drawable.bondedgreen;
                } else {
                    pic = R.drawable.destinationgrew;
                }
            }
            Drawable drawable = ContextCompat.getDrawable(getActivity(), pic);
            drawable.setBounds(0, 0, DensityUtils.dip2px(getContext(),50), DensityUtils.dip2px(getContext(),50));
            tv.setCompoundDrawables(null, drawable, null, null);

            llAttrRoot.addView(tv);
        }

    }
}
