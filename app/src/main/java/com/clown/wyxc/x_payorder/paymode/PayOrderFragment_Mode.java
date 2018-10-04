package com.clown.wyxc.x_payorder.paymode;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.baselibray.material.widget.Switch;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.utils.BigDecimalUtil;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.PayPathResult;
import com.clown.wyxc.x_bean.x_parambean.GetOrderSumAmtQuery;
import com.clown.wyxc.x_bean.x_parambean.OrderPayQuery;
import com.clown.wyxc.x_findpaypassword.FindPayPasswdActivity;
import com.clown.wyxc.x_payorder.PayOrderActivity;
import com.clown.wyxc.x_settleorder.SettleOrderActivity;
import com.clown.wyxc.x_utils.Router;
import com.flipboard.bottomsheet.BottomSheetLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.jifen_title;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PayOrderFragment_Mode extends BaseFragment implements PayOrderContract_Mode.View {

    @Bind(R.id.tv_zongjiatitle)
    TextView tvZongjiatitle;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.tv_zhifujiagetitle)
    TextView tvZhifujiagetitle;
    @Bind(R.id.zhifujiage)
    TextView zhifujiage;
    @Bind(R.id.rl_huizong)
    RelativeLayout rlHuizong;
    @Bind(jifen_title)
    TextView jifenTitle;
    @Bind(R.id.jifen_zongjine)
    TitleTextview jifenZongjine;
    @Bind(R.id.jifen_kedikou)
    TitleTextview jifenKedikou;
    @Bind(R.id.cb_jifenchoice)
    Switch cbJifenchoice;
    @Bind(R.id.rl_jifen)
    RelativeLayout rlJifen;
    @Bind(R.id.jinecomment)
    TextView jinecomment;
    @Bind(R.id.bt_comitpay)
    Button btComitpay;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomsheet;
    @Bind(R.id.zongjia)
    TextView zongjia;
    @Bind(R.id.jifen_yidikou)
    TitleTextview jifenYidikou;
    @Bind(R.id.rl_nianka)
    RelativeLayout rlNianka;
    @Bind(R.id.ll_nianka)
    LinearLayout llNianka;
    @Bind(R.id.et_yidikou)
    EditText etYidikou;

    private PayOrderContract_Mode.Presenter mPresenter;

    private String orderNo;

    private List<PayPathResult> pathResults = new ArrayList<>();

    private String yidikouStr = "";

    private final static int PAYTYPE_WEIXIN = 1;
    private final static int PAYTYPE_ALIPAY = 2;
    private final static int PAYTYPE_NIANKA = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paymode_frg_stores, container, false);

        ButterKnife.bind(this, view);

        try {
            Bundle data = getArguments();
            orderNo = data.getString(PayOrderActivity.INTENTNAME_ORDERNO);
            initViews();
            initData();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initViews() {
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public PayOrderFragment_Mode() {
        new PayOrderPresenter_Mode(this);
    }

    public static PayOrderFragment_Mode newInstance() {
        return new PayOrderFragment_Mode();
    }

    private void initAction() throws Exception {

        btComitpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
                    View view = LayoutInflater.from(getActivity()).inflate(R.layout.payorder_frg_payorder, null);
                    mBottomSheetDialog.setContentView(view);

                    final TitleTextview tt_money = (TitleTextview) view.findViewById(R.id.tt_money);
                    Button bt_weixin = (Button) view.findViewById(R.id.bt_weixin);
                    Button bt_alipay = (Button) view.findViewById(R.id.bt_alipay);
                    Button bt_commit = (Button) view.findViewById(R.id.bt_commit);
                    TextView tv_forgetpass = (TextView) view.findViewById(R.id.tv_forgetpass);
                    final EditText et_paypass = (EditText) view.findViewById(R.id.et_paypass);
                    tt_money.setTt_text_back("");

                    BigDecimal total = new BigDecimal(zongjia.getText().toString().substring(1));
                    if (cbJifenchoice.isChecked()) {

                        BigDecimal yidikou = null;
                        if ("".equals(etYidikou.getText().toString())) {
                            yidikou = new BigDecimal("0");
                        } else {
                            yidikou = new BigDecimal(etYidikou.getText().toString());
                        }

                        tt_money.setTt_text_back(BigDecimalUtil.df.format(total.subtract(yidikou)));
                    }else{
                        tt_money.setTt_text_back(BigDecimalUtil.df.format(total));
                    }

                    if ("0.00".equals(tt_money.getTt_text_back())) {
                        bt_commit.setVisibility(View.VISIBLE);
                    } else {
                        bt_alipay.setVisibility(View.VISIBLE);
                        bt_weixin.setVisibility(View.VISIBLE);
                    }

                    bt_commit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkInsert(et_paypass)) {
                                return;
                            }
                            List<PayPathResult> tempArr = new ArrayList<>();
                            for (PayPathResult pathResult : pathResults) {
                                if (pathResult.getId() != PAYTYPE_WEIXIN && pathResult.getId() != PAYTYPE_ALIPAY) {
                                    pathResult.setPayAmt(new BigDecimal(etYidikou.getText().toString()));
                                    tempArr.add(pathResult);
                                }
                            }
                            mPresenter.orderPay(GSONUtils.paramToJson(new OrderPayQuery(user.getId()
                                    , orderNo, tempArr, et_paypass.getText().toString())));
                        }
                    });

                    bt_weixin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkInsert(et_paypass)) {
                                return;
                            }
                            List<PayPathResult> tempArr = new ArrayList<>();
                            for (PayPathResult pathResult : pathResults) {
                                if (pathResult.getId() == PAYTYPE_NIANKA) {
                                    if (cbJifenchoice.isChecked()) {
                                        if (!"".equals(etYidikou.getText().toString())) {
                                            BigDecimal temp = new BigDecimal(etYidikou.getText().toString());
                                            if(!temp.equals(new BigDecimal("0"))){
                                                tempArr.add(pathResult);
                                            }
                                        }
                                    }
                                } else if (pathResult.getId() == PAYTYPE_WEIXIN) {
                                    pathResult.setChecked(true);
                                    pathResult.setPayAmt(new BigDecimal(tt_money.getTt_text_back()));
                                    tempArr.add(pathResult);
                                }
                            }
                            mPresenter.orderPay(GSONUtils.paramToJson(new OrderPayQuery(user.getId()
                                    , orderNo, tempArr, et_paypass.getText().toString())));
                        }
                    });

                    bt_alipay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkInsert(et_paypass)) {
                                return;
                            }
                            List<PayPathResult> tempArr = new ArrayList<>();
                            for (PayPathResult pathResult : pathResults) {
                                if (pathResult.getId() == PAYTYPE_NIANKA) {
                                    if (cbJifenchoice.isChecked()) {
                                        if (!"".equals(etYidikou.getText().toString())) {
                                            BigDecimal temp = new BigDecimal(etYidikou.getText().toString());
                                            if(!temp.equals(new BigDecimal("0"))){
                                                tempArr.add(pathResult);
                                            }
                                        }
                                    }
                                } else if (pathResult.getId() == PAYTYPE_ALIPAY) {
                                    pathResult.setChecked(true);
                                    pathResult.setPayAmt(new BigDecimal(tt_money.getTt_text_back()));
                                    tempArr.add(pathResult);
                                }
                            }
                            mPresenter.orderPay(GSONUtils.paramToJson(new OrderPayQuery(user.getId()
                                    , orderNo, tempArr, et_paypass.getText().toString())));
                        }
                    });

                    tv_forgetpass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            IntentUtils.startActivity(getActivity(), FindPayPasswdActivity.class);
                        }
                    });

                    mBottomSheetDialog.show();
                    mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            //                        mBottomSheetDialog = null;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cbJifenchoice.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {
                if (checked) {
                    llNianka.setVisibility(View.VISIBLE);
                    for (PayPathResult payPathResult : pathResults) {
                        if (payPathResult.getId() == PAYTYPE_NIANKA) {
                            etYidikou.setText(BigDecimalUtil.df.format(payPathResult.getPayAmt()));
                            break;
                        }
                    }
                } else {
                    llNianka.setVisibility(View.GONE);
                }
                for (PayPathResult payPathResult : pathResults) {
                    if (payPathResult.getId() == PAYTYPE_NIANKA) {
                        payPathResult.setChecked(checked);
                        break;
                    }
                }
                setShijiAmount();
            }
        });

        etYidikou.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (PayPathResult payPathResult : pathResults) {
                    if (payPathResult.getId() == PAYTYPE_NIANKA) {
                        if (String.valueOf(s) != null && !"".equals(String.valueOf(s))) {
                            BigDecimal temp = new BigDecimal(String.valueOf(s));
                            BigDecimal yi = new BigDecimal(jifenKedikou.getTt_text_back());
                            BigDecimal zjia = new BigDecimal(zongjia.getText().toString().substring(1));

                            BigDecimal small = new BigDecimal("0");

                            if (zjia.compareTo(yi) > 0) {
                                small = yi;
                            } else {
                                small = zjia;
                            }

                            etYidikou.removeTextChangedListener(this);
                            if (temp.compareTo(small) > 0) {
                                etYidikou.setText(String.valueOf(small));
                            } else {
                                etYidikou.setText(String.valueOf(s));
                            }
                            etYidikou.addTextChangedListener(this);
                            Editable editable = etYidikou.getText();
                            Selection.setSelection(editable, editable.length());

                            setShijiAmount();
                        }
                        break;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    for (PayPathResult payPathResult : pathResults) {
                        if (payPathResult.getId() == PAYTYPE_NIANKA) {
                            if (String.valueOf(s) != null && !"".equals(String.valueOf(s))) {
                                payPathResult.setPayAmt(new BigDecimal(String.valueOf(s)));
                            }
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkInsert(EditText etPaypass) {
        if ("".equals(etPaypass.getText().toString().trim())) {
            S.showShort(llMain, "请输入支付密码。");
            return true;
        }
        return false;
    }

    private void initData() {
        mPresenter.getOrderSumAmt(GSONUtils.paramToJson(new GetOrderSumAmtQuery(user.getId(), orderNo)));
        mPresenter.getPayPathBypayOrderNO(GSONUtils.paramToJson(new GetOrderSumAmtQuery(user.getId(), orderNo)));
        mPresenter.test();
    }

    @Override
    public void setPresenter(@NonNull PayOrderContract_Mode.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setGetOrderSumAmtResult(BigDecimal result) {
        zongjia.setText("￥" + BigDecimalUtil.df.format(result));
    }

    @Override
    public void setGetPayPathBypayOrderNOResult(List<PayPathResult> result) {
        pathResults.addAll(result);
        boolean flag = false;
        for (PayPathResult payPathResult : result) {
            if (payPathResult.getId() == PAYTYPE_NIANKA) {
                jifenKedikou.setTt_text_back(BigDecimalUtil.df.format(payPathResult.getDispayPayAmt()));
                jifenZongjine.setTt_text_back(BigDecimalUtil.df.format(payPathResult.getPayAmt()));
                jifenTitle.setText(payPathResult.getName());
                flag = true;
                break;
            }
        }
        if (!flag) {
            rlJifen.setVisibility(View.GONE);
        }
        setShijiAmount();
    }

    @Override
    public void setOrderPayResult(String result) {
        if ("success".equals(result)) {
            IntentUtils.startActivity(getContext(), SettleOrderActivity.class);
        }else{
            Router.startActivty(getActivity(),result,true);
        }
    }

    @Override
    public void settest() {
        btComitpay.setVisibility(View.GONE);
    }

    private void setShijiAmount(){
        try {
            BigDecimal total = new BigDecimal(zongjia.getText().toString().substring(1));
            if (cbJifenchoice.isChecked()) {

                BigDecimal yidikou = null;
                if ("".equals(etYidikou.getText().toString())) {
                    yidikou = new BigDecimal("0");
                } else {
                    yidikou = new BigDecimal(etYidikou.getText().toString());
                }

                zhifujiage.setText("￥"+BigDecimalUtil.df.format(total.subtract(yidikou)));
            }else{
                zhifujiage.setText("￥"+BigDecimalUtil.df.format(total));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
