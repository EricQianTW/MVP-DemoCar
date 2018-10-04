package com.clown.wyxc.x_payorder.payorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.TitleTextview;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.x_bean.MsgOrderPay;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.math.BigDecimal;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class PayOrderFragment_PayOrder extends BaseFragment implements PayOrderContract_PayOrder.View {

    @Bind(R.id.bt_weixin)
    Button btWeixin;
    @Bind(R.id.bt_alipay)
    Button btAlipay;
    @Bind(R.id.bt_commit)
    Button btCommit;
    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.et_paypass)
    EditText etPaypass;
    @Bind(R.id.tt_money)
    TitleTextview ttMoney;
    @Bind(R.id.tv_forgetpass)
    TextView tvForgetpass;
    private PayOrderContract_PayOrder.Presenter mPresenter;

    public static final String INTENTNAME_LSITMSGORDERPAY = "intentname_lsitmsgorderpay";
    public static final String INTENTNAME_ZHIFUJIAGE = "intentname_zhifujiage";
    public static final String INTENTNAME_ORDERNO = "intentname_orderno";

    private List<MsgOrderPay> msgOrderPayArry;
    private BigDecimal decimal;
    private String orderNo;
    private MaterialDialog mMaterialDialog;

    private boolean keyBoardShown = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payorder_frg_payorder, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            initView();
            initData();
            initAction();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
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

    private void initView() throws Exception {
        Bundle data = getArguments();
        TypeToken<List<MsgOrderPay>> token = new TypeToken<List<MsgOrderPay>>() {
        };
        msgOrderPayArry = (List<MsgOrderPay>) GSONUtils.fromJson(data.getString(INTENTNAME_LSITMSGORDERPAY, ""), token);
        decimal = new BigDecimal(data.getString(INTENTNAME_ZHIFUJIAGE, "0"));
        orderNo = data.getString(INTENTNAME_ORDERNO, "");

        if (decimal.compareTo(new BigDecimal("0")) == 0) {
            btCommit.setVisibility(View.VISIBLE);
        } else {
            btAlipay.setVisibility(View.VISIBLE);
            btWeixin.setVisibility(View.VISIBLE);
        }

        ttMoney.setTt_text_back("￥" + String.valueOf(decimal));
    }

    private void initAction() throws Exception {
        btWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    if (checkInsert()) {
//                        return;
//                    }
//                    List<MsgOrderPay> array = new ArrayList<MsgOrderPay>();
//                    if(msgOrderPayArry.size() != 0){
//                        for (MsgOrderPay temp : msgOrderPayArry) {
//                            if (temp.getPayTyp() != OrderPayType.ZHIFUBAO.getnCode()) {
//                                if (temp.getPayTyp() == OrderPayType.WEIXIN.getnCode()) {
//                                    setValue(temp, decimal);
//                                } else {
//                                    setValue(temp, temp.getPayAmt());
//                                }
//                                array.add(temp);
//                            }
//                        }
//                    }else{
//                        MsgOrderPay temp = new MsgOrderPay();
//                        temp.setPayStat(1);
//                        temp.setPayAmt(decimal);
//                        temp.setSplitDur(temp.getSplitDur());
//                        temp.setGuidOrderNo(orderNo);
//                        temp.setPayTyp(OrderPayType.WEIXIN.getnCode());
//                        array.add(temp);
//                    }
//                    mPresenter.orderUserPay(user.getVerify(), user.getUserId(), etPaypass.getText().toString(), orderNo, array);
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
        btAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    if (checkInsert()) {
//                        return;
//                    }
//                    List<MsgOrderPay> array = new ArrayList<MsgOrderPay>();
//                    if (msgOrderPayArry.size() != 0) {
//                        for (MsgOrderPay temp : msgOrderPayArry) {
//                            if (temp.getPayTyp() != OrderPayType.WEIXIN.getnCode()) {
//                                if (temp.getPayTyp() == OrderPayType.ZHIFUBAO.getnCode()) {
//                                    setValue(temp, decimal);
//                                } else {
//                                    setValue(temp, temp.getPayAmt());
//                                }
//                                array.add(temp);
//                            }
//                        }
//                    }else{
//                        MsgOrderPay temp = new MsgOrderPay();
//                        temp.setPayStat(1);
//                        temp.setPayAmt(decimal);
//                        temp.setSplitDur(temp.getSplitDur());
//                        temp.setGuidOrderNo(orderNo);
//                        temp.setPayTyp(OrderPayType.ZHIFUBAO.getnCode());
//                        array.add(temp);
//                    }
//
//                    mPresenter.orderUserPay(user.getVerify(), user.getUserId(), etPaypass.getText().toString(), orderNo, array);
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
        btCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    if (checkInsert()) {
//                        return;
//                    }
//                    List<MsgOrderPay> array = new ArrayList<MsgOrderPay>();
//                    for (MsgOrderPay temp : msgOrderPayArry) {
//                        if (temp.getPayTyp() != OrderPayType.WEIXIN.getnCode() && temp.getPayTyp() != OrderPayType.ZHIFUBAO.getnCode()) {
//                            setValue(temp, temp.getPayAmt());
//                            array.add(temp);
//                        }
//                    }
//                    mPresenter.orderUserPay(user.getVerify(), user.getUserId(), etPaypass.getText().toString(), orderNo, array);
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
        tvForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put(PasswordFristActivity.INTENTNAME_LOSTTYPE, "2");
//                IntentUtils.startActivity(getContext(), PasswordFristActivity.class,map);
            }
        });
    }

    private boolean checkInsert() {
        if ("".equals(etPaypass.getText().toString().trim())) {
            S.showShort(llMain, "请输入支付密码。");
            return true;
        }
        return false;
    }

    private void setValue(MsgOrderPay temp, BigDecimal decimal) throws Exception {
//        temp.setPayStat(1);
//        temp.setPayAmt(decimal);
//        temp.setSplitDur(temp.getSplitDur());
    }

    private void initData() throws Exception {
    }

    public PayOrderFragment_PayOrder() {
        new PayOrderPresenter_PayOrder(this);
    }

    public static PayOrderFragment_PayOrder newInstance() {
        return new PayOrderFragment_PayOrder();
    }

    @Override
    public void setPresenter(PayOrderContract_PayOrder.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setOrderUserPayRes(String result) {
        IntentUtils.startSchemeIntent(getContext(), result);
        getActivity().finish();
    }
}
