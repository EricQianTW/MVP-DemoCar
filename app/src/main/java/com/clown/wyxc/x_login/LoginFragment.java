package com.clown.wyxc.x_login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import com.clown.wyxc.tools.TextViewTool;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.KeyBoardUtils;
import com.clown.wyxc.utils.SPUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.UsersResult;
import com.clown.wyxc.x_bean.x_parambean.FindPasswordQuery;
import com.clown.wyxc.x_bean.x_parambean.LoginCodeQuery;
import com.clown.wyxc.x_bean.x_parambean.LoginQuery;
import com.clown.wyxc.x_bean.x_parambean.SendCodeQuery;
import com.clown.wyxc.x_html.HtmlActivity;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.bt_login;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class LoginFragment extends BaseFragment implements LoginContract.View {
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_passwd)
    EditText etPasswd;
    @Bind(R.id.et_identifying)
    EditText etIdentifying;
    @Bind(bt_login)
    Button btLogin;
    @Bind(R.id.tv_protocol)
    TextView tvProtocol;
    @Bind(R.id.tv_forgetpass)
    TextView tvForgetpass;
    @Bind(R.id.tv_cutmothed)
    TextView tvCutmothed;
    @Bind(R.id.email_login_form)
    RelativeLayout emailLoginForm;
    @Bind(R.id.login_form)
    NestedScrollView loginForm;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.bt_getIdentifying)
    Button btGetIdentifying;

    @Bind(R.id.iv_icon2)
    ImageView ivIcon2;
    @Bind(R.id.et_phone2)
    EditText etPhone2;
    @Bind(R.id.bt_getIdentifying2)
    Button btGetIdentifying2;
    @Bind(R.id.et_identifying2)
    EditText etIdentifying2;
    @Bind(R.id.et_passwd2)
    EditText etPasswd2;
    @Bind(R.id.bt_login2)
    Button btLogin2;
    @Bind(R.id.tv_cutmothed2)
    TextView tvCutmothed2;
    @Bind(R.id.email_login_form2)
    RelativeLayout emailLoginForm2;
    @Bind(R.id.findpasswd_form)
    NestedScrollView findpasswdForm;
    @Bind(R.id.webview)
    WebView webview;

    private final static int INDEX_PASSWDLOGIN = 0;
    private final static int INDEX_IDENTIFYLOGIN = 1;


    private LoginContract.Presenter mPresenter;
    private int currentMothed = INDEX_IDENTIFYLOGIN;
    private CountDownTimerTool countDownTimer, countDownTimer2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_frg, container, false);
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

        setMothed(currentMothed);
        initView();
        initAction();
        initAction2();
    }

    private void initAction2() {
        btGetIdentifying2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etPhone2.getText().toString())) {
                    T.showShort(getContext(), "请输入手机号");
                    return;
                }
                countDownTimer2.start();
                btGetIdentifying2.setEnabled(false);

                mPresenter.sendCode(GSONUtils.toJson(new SendCodeQuery(etPhone2.getText().toString())));
            }
        });

        btLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etPhone2.getText().toString())) {
                    T.showShort(getContext(), "请输入手机号");
                    return;
                }
                if ("".equals(etPasswd2.getText().toString())) {
                    T.showShort(getContext(), "请输入密码");
                    return;
                }
                if ("".equals(etIdentifying2.getText().toString())) {
                    T.showShort(getContext(), "请输入验证码");
                    return;
                }
                mPresenter.findPassword(GSONUtils.toJson(new FindPasswordQuery(etPhone2.getText().toString(), etPasswd2.getText().toString(), etIdentifying2.getText().toString())));
            }
        });

        countDownTimer2.setOnCountDownListening(new CountDownTimerTool.OnCountDownListening() {
            @Override
            public void onTickListening(long millisUntilFinished) {
                if (btGetIdentifying2 != null) {
                    btGetIdentifying2.setText(millisUntilFinished / 1000 + "秒后重发");
                }
            }

            @Override
            public void onFinishListening() {
                if (btGetIdentifying2 != null) {
                    btGetIdentifying2.setText("点击重发");
                    btGetIdentifying2.setEnabled(true);
                }
            }
        });

        tvCutmothed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findpasswdForm.setVisibility(View.GONE);
                loginForm.setVisibility(View.VISIBLE);
            }
        });
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
        TextViewTool.setTextViewPartColor(tvProtocol, Color.BLUE, 6, 17);
        TextViewTool.setTextViewPartColor(tvForgetpass, Color.BLUE, 5, 9);

        countDownTimer = new CountDownTimerTool(60000, 1000);
        countDownTimer2 = new CountDownTimerTool(60000, 1000);
    }

    public LoginFragment() {
        new LoginPresenter(this);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private void initAction() {
        tvCutmothed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentMothed == INDEX_IDENTIFYLOGIN) {
                    setMothed(INDEX_PASSWDLOGIN);
                    currentMothed = INDEX_PASSWDLOGIN;
                } else {
                    setMothed(INDEX_IDENTIFYLOGIN);
                    currentMothed = INDEX_IDENTIFYLOGIN;
                }
            }
        });

        tvProtocol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        tvForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findpasswdForm.setVisibility(View.VISIBLE);
                loginForm.setVisibility(View.GONE);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentMothed == INDEX_PASSWDLOGIN) {
                    if ("".equals(etPhone.getText().toString())) {
                        T.showShort(getContext(), "请输入手机号");
                        return;
                    }
                    if ("".equals(etPasswd.getText().toString())) {
                        T.showShort(getContext(), "请输入密码");
                        return;
                    }
                    LoginQuery query = new LoginQuery(etPhone.getText().toString(), etPasswd.getText().toString());
                    mPresenter.login(GSONUtils.toJson(query));
                } else {
                    if ("".equals(etPhone.getText().toString())) {
                        T.showShort(getContext(), "请输入手机号");
                        return;
                    }
                    if ("".equals(etIdentifying.getText().toString())) {
                        T.showShort(getContext(), "请输入验证码");
                        return;
                    }
                    LoginCodeQuery query = new LoginCodeQuery(etPhone.getText().toString(), etIdentifying.getText().toString());
                    mPresenter.loginCode(GSONUtils.toJson(query));
                }
            }
        });

        countDownTimer.setOnCountDownListening(new CountDownTimerTool.OnCountDownListening() {
            @Override
            public void onTickListening(long millisUntilFinished) {
                try {
                    if (btGetIdentifying != null) {
                        btGetIdentifying.setText(millisUntilFinished / 1000 + "秒后重发");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinishListening() {
                try {
                    btGetIdentifying.setText("点击重发");
                    btGetIdentifying.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setMothed(int mothedId) {
        if (mothedId == INDEX_PASSWDLOGIN) {
            etPasswd.setVisibility(View.VISIBLE);
            etIdentifying.setVisibility(View.GONE);
            tvCutmothed.setText("验证码登录");
            btGetIdentifying.setVisibility(View.GONE);
        } else if (mothedId == INDEX_IDENTIFYLOGIN) {
            etPasswd.setVisibility(View.GONE);
            etIdentifying.setVisibility(View.VISIBLE);
            tvCutmothed.setText("密码登录");
            btGetIdentifying.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setLoginResult(UsersResult result) {
        try {
            KeyBoardUtils.closeKeybord(getActivity());
            showProgress(false, loginProgress, loginForm);
            SPUtils.put(getActivity(), SPUtils.SP_STRING_USER, GSONUtils.toJson(result));
            SPUtils.put(getActivity(), SPUtils.SP_BOOLEAN_LOGIN, true);
            getActivity().finish();
            loginEventForShare(result);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setSendCodeResult(String result) {
        if ("SUCCESS".equals(result)) {
            T.showShort(getContext(), "短信已发出...");
        }
    }

    public void loginEventForShare(UsersResult info) {
        if (info != null) {
            String url = "http://api.ixiuc.com/getAppData/getAppData.aspx?userId=" + info.getId();
            Intent intent = new Intent(getActivity(), HtmlActivity.class);
            intent.putExtra(HtmlActivity.PARAM_STRING_TYPE, 999);
            intent.putExtra(HtmlActivity.PARAM_STRING_URL, url);
            startActivity(intent);
        }
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
