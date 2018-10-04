package com.clown.wyxc.x_findpaypassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.tools.CountDownTimerTool;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.x_parambean.SendCodeQuery;
import com.clown.wyxc.x_bean.x_parambean.UpdatePayPasswordQuery;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class FindPayPasswdFragment extends BaseFragment implements FindPayPasswdContract.View {
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.bt_getIdentifying)
    Button btGetIdentifying;
    @Bind(R.id.et_identifying)
    EditText etIdentifying;
    @Bind(R.id.et_passwd)
    EditText etPasswd;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.tv_cutmothed)
    TextView tvCutmothed;
    @Bind(R.id.email_login_form)
    RelativeLayout emailLoginForm;
    @Bind(R.id.login_form)
    NestedScrollView loginForm;
    @Bind(R.id.ll_main)
    LinearLayout llMain;

    private FindPayPasswdContract.Presenter mPresenter;
    private CountDownTimerTool countDownTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findloginpasswd_frg, container, false);
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

    private void initView() {
        countDownTimer = new CountDownTimerTool(60000, 1000);
    }

    public FindPayPasswdFragment() {
        new FindPayPasswdPresenter(this);
    }

    public static FindPayPasswdFragment newInstance() {
        return new FindPayPasswdFragment();
    }

    private void initAction() {
        tvCutmothed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        btGetIdentifying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etPhone.getText().toString())) {
                    T.showShort(getContext(), "请输入手机号");
                    return;
                }
                countDownTimer.start();
                btGetIdentifying.setEnabled(false);

                mPresenter.sendCode(GSONUtils.toJson(new SendCodeQuery(etPhone.getText().toString())));
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etPhone.getText().toString())) {
                    T.showShort(getContext(), "请输入手机号");
                    return;
                }
                if ("".equals(etPasswd.getText().toString())) {
                    T.showShort(getContext(), "请输入密码");
                    return;
                }
                if ("".equals(etIdentifying.getText().toString())) {
                    T.showShort(getContext(), "请输入验证码");
                    return;
                }
                mPresenter.updatePayPassword(GSONUtils.paramToJson(new UpdatePayPasswordQuery(etPhone.getText().toString(), etPasswd.getText().toString(), etIdentifying.getText().toString())));
            }
        });

        countDownTimer.setOnCountDownListening(new CountDownTimerTool.OnCountDownListening() {
            @Override
            public void onTickListening(long millisUntilFinished) {
                if (btGetIdentifying != null) {
                    btGetIdentifying.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinishListening() {
                if (btGetIdentifying != null) {
                    btGetIdentifying.setText("点击重发");
                    btGetIdentifying.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void setUpdatePayPasswordResult(int result) {
        T.showShort(getContext(),"密码修改成功");
        getActivity().finish();
    }

    @Override
    public void setSendCodeResult(String codeResult) {
        if ("SUCCESS".equals(codeResult)) {
            T.showShort(getContext(), "短信已发出...");
        }
    }

    @Override
    public void setPresenter(@NonNull FindPayPasswdContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
