package com.clown.wyxc.x_mine.header;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgAdsInpage;
import com.clown.wyxc.components.CircleImageView;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Users;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_login.LoginActivity;
import com.clown.wyxc.x_setup.SetUpActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MineFragment_Header extends BaseFragment implements MineContract_Header.View {

    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.tv_defaultcar)
    TextView tvDefaultcar;
    @Bind(R.id.userheader)
    CircleImageView userheader;
    @Bind(R.id.ll_main)
    FrameLayout llMain;
    @Bind(R.id.tv_nologin)
    TextView tvNologin;

    private MineContract_Header.Presenter mPresenter;
    private List<MsgAdsInpage> msgList;
    private String phone_close;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_frg_header, container, false);
        ButterKnife.bind(this, view);

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
        try {
            initAction();
            initView();
            if (islogin) {
                initData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initData() throws Exception {
        mPresenter.getUsersById(GSONUtils.paramToJson(new QueryId(user.getId(),null)));
    }

    public MineFragment_Header() {
        new MinePresenter_Header(this);
    }

    private void initView() {
        if (islogin) {
            tvNologin.setVisibility(View.GONE);
            tvDefaultcar.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);
        } else {
            tvNologin.setVisibility(View.VISIBLE);
            tvDefaultcar.setVisibility(View.GONE);
            phone.setVisibility(View.GONE);

        }
        tvNologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!islogin) {
                    IntentUtils.startActivity(getContext(), LoginActivity.class);
                }
            }
        });

        SpannableStringBuilder builder = new SpannableStringBuilder(tvNologin.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.gray_33));
        builder.setSpan(redSpan, 6, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvNologin.setText(builder);
    }

    public static MineFragment_Header newInstance() {
        return new MineFragment_Header();
    }

    private void initAction() {
        userheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (islogin) {
                    IntentUtils.startActivity(getContext(), SetUpActivity.class);
                }else{
                    T.showShort(getContext(),"请先登录...");
                }
            }
        });
    }

    @Override
    public void setPresenter(MineContract_Header.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetUsersByIdResult(Users info) {
        try {
            if (userheader != null) {
                ImageLoader.getInstance().displayImage(info.getHeadPic(), userheader);
            }
            tvDefaultcar.setText(info.getName());
            phone_close = info.getPhone().substring(0, 3) + "****" + info.getPhone().substring(7);
            phone.setText(phone_close);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
