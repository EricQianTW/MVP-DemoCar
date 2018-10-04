package com.clown.wyxc.x_mine.items;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.HorizontalItem;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.SPUtils;
import com.clown.wyxc.x_address.address_list.AddressListActivity;
import com.clown.wyxc.x_feedback.FeedbackActivity;
import com.clown.wyxc.x_html.HtmlActivity;
import com.clown.wyxc.x_mycollection.MyCollectionsActivity;
import com.clown.wyxc.x_setup.SetUpActivity;
import com.clown.wyxc.x_shopcar.ShopCarActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class MineFragment_Items extends BaseFragment implements MineContract_Items.View {

    @Bind(R.id.wodenianka)
    HorizontalItem wodenianka;
    @Bind(R.id.gouwuche)
    HorizontalItem gouwuche;
    @Bind(R.id.dizhiguanli)
    HorizontalItem dizhiguanli;
    @Bind(R.id.shezhi)
    HorizontalItem shezhi;
    @Bind(R.id.wodepingjai)
    HorizontalItem wodepingjai;
    @Bind(R.id.wodeshoucang)
    HorizontalItem wodeshoucang;
    @Bind(R.id.yijianfankui)
    HorizontalItem yijianfankui;
    @Bind(R.id.guanyuwomen)
    HorizontalItem guanyuwomen;
    @Bind(R.id.kefudianhua)
    HorizontalItem kefudianhua;
    @Bind(R.id.fuwutiaokuan)
    HorizontalItem fuwutiaokuan;
    @Bind(R.id.logout)
    RelativeLayout logout;
    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private MineContract_Items.Presenter mPresenter;

    public MineFragment_Items() {
        new MinePresenter_Items(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_frg_items, container, false);

        ButterKnife.bind(this, view);
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
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static MineFragment_Items newInstance() {
        return new MineFragment_Items();
    }


    private void initAction() {
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), SetUpActivity.class);
            }
        });

        gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), ShopCarActivity.class);
            }
        });

        yijianfankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), FeedbackActivity.class);
            }
        });

        dizhiguanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), AddressListActivity.class);
            }
        });

        wodeshoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), MyCollectionsActivity.class);
            }
        });

        fuwutiaokuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(HtmlActivity.PARAM_STRING_TITLE,"用户协议");
                map.put(HtmlActivity.PARAM_STRING_URL,"http://api.ixiuc.com/admin/haha.html");
                IntentUtils.startActivity(getContext(), HtmlActivity.class);
            }
        });

        guanyuwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(HtmlActivity.PARAM_STRING_TITLE,"关于我们");
                map.put(HtmlActivity.PARAM_STRING_URL,"http://api.ixiuc.com/admin/haha.html");
                IntentUtils.startActivity(getContext(), HtmlActivity.class);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    @Override
    public void setPresenter(@NonNull MineContract_Items.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    public void showAlertDialog() {
        try {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("是否退出");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SPUtils.put(getActivity(), SPUtils.SP_BOOLEAN_LOGIN, false);
                    SPUtils.remove(getActivity(), SPUtils.SP_STRING_USER);
                    Intent intent = getActivity().getIntent();
                    getActivity().finish();
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
