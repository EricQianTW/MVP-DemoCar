package com.clown.wyxc.x_setup;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.HorizontalItem;
import com.clown.wyxc.components.update.LoadAnsyReadVersionXML;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Users;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_bean.x_parambean.QueryUserId;
import com.clown.wyxc.x_changeheader.ChangeHeaderActivity;
import com.clown.wyxc.x_findloginpasswd.FindLoginPasswdActivity;
import com.clown.wyxc.x_findpaypassword.FindPayPasswdActivity;
import com.suke.widget.SwitchButton;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class SetUpFragment extends BaseFragment implements SetUpContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.changeheader)
    HorizontalItem changeheader;
    @Bind(R.id.switch_button)
    SwitchButton switchButton;
    @Bind(R.id.clearcache)
    HorizontalItem clearcache;
    @Bind(R.id.checkupdate)
    HorizontalItem checkupdate;
    @Bind(R.id.changeloginpassword)
    HorizontalItem changeloginpassword;
    @Bind(R.id.changepaypassword)
    HorizontalItem changepaypassword;
    @Bind(R.id.changename)
    HorizontalItem changename;


    private SetUpContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup_frg
                , container, false);
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

        initView();
        initAction();
        initData();
    }

    private void initData() {
        mPresenter.getUsersById(GSONUtils.paramToJson(new QueryId(user.getId(), null)));
    }

    private void initView() {
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

    public SetUpFragment() {
        new SetUpPresenter(this);
    }

    public static SetUpFragment newInstance() {
        return new SetUpFragment();
    }

    private void initAction() {
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                mPresenter.updateReceiveNotice(GSONUtils.paramToJson(new QueryUserId(user.getId())));
            }
        });

        clearcache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(getContext());
                dialog.setMessage("清除缓存中...");
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 2000);
            }
        });

        changeheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(ChangeHeaderActivity.TYPE, 1);
                IntentUtils.startActivity(getContext(), ChangeHeaderActivity.class, map);
            }
        });

        changename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(ChangeHeaderActivity.TYPE, 2);
                IntentUtils.startActivity(getContext(), ChangeHeaderActivity.class, map);
            }
        });

        changeloginpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), FindLoginPasswdActivity.class);
            }
        });

        changepaypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getContext(), FindPayPasswdActivity.class);
            }
        });

        checkupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadAnsyReadVersionXML loadXml = new LoadAnsyReadVersionXML(getContext());
                loadXml.execute();

                loadXml.setOnPassUpdateListening(new LoadAnsyReadVersionXML.OnPassUpdateListening() {
                    @Override
                    public void PassUpdateListening() {
                        T.showShort(getContext(), "您已是最新版本");
                    }
                });
            }
        });
    }

    @Override
    public void setPresenter(@NonNull SetUpContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setUpdateReceiveNoticeResult(int result) {
        T.showShort(getContext(), "设置更改成功");
    }

    @Override
    public void setGetUsersByIdResult(Users result) {
        switchButton.setChecked(result.getIsReceiveNotice() == 1 ? true : false);
    }
}
