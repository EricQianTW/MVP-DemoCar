package com.clown.wyxc.x_wuliu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.components.stepview.StepView;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_base.Message;
import com.clown.wyxc.x_bean.ExpressByOrderIdResult;
import com.clown.wyxc.x_bean.IogisticsInfo;
import com.clown.wyxc.x_bean.x_parambean.OrderQuery;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by cc on 2017/5/18.
 */

public class WuliuActivity extends BaseAppCompatActivity {

    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.state)
    TextView mState;
    @Bind(R.id.ordernum)
    TextView mOrdernum;
    @Bind(R.id.company)
    TextView mCompany;
    @Bind(R.id.stepview)
    StepView mStepview;

    private int orderid;
    public final static String INTENTNAME_ORDERID = "orderid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliu);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("消费日志");
        setSupportActionBar(toolbar);
        initBack();

        Intent intent = getIntent();
        orderid = intent.getIntExtra(INTENTNAME_ORDERID, 0);

        getExpressInfoByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(),orderid)));
    }

    public void getExpressInfoByOrderId(String param){
        try {
            OkHttpUtils
                    .postString()
                    .url("http://api.ixiuc.com//api/Order/GetExpressInfoByOrderId")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .content(param)
                    .build()
                    .execute(new Callback<String>() {
                        @Override
                        public String parseNetworkResponse(Response response) throws Exception {
                            return response.body().string();
                        }

                        @Override
                        public void onError(okhttp3.Call call, Exception e) {
                            Logger.e(e, "something happend");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                TypeToken<Message<ExpressByOrderIdResult>> token = new TypeToken<Message<ExpressByOrderIdResult>>() {
                                };
                                Message<ExpressByOrderIdResult> dataPackage = GSONUtils.fromJson(response, token);
                                if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {
                                    resetView(dataPackage.getBody());
                                } else {
                                    showError(dataPackage.getCustomCode(), dataPackage.getInfo());
                                    Logger.e(TAG, dataPackage.getInfo());
                                }

                            } catch (Exception e) {
                                Logger.e(e, TAG);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void resetView(ExpressByOrderIdResult msg) throws Exception {
        ImageLoader.getInstance().displayImage(msg.getExImgPath(), mImage);
        mCompany.setText("承运公司:" + msg.getExpressName());
        mOrdernum.setText("订单编号:" + msg.getExpressNO());

        mStepview.setDatas(msg.getIogisticsInfoList());
        // 设置view的绑定监听
        mStepview.setBindViewListener(new StepView.BindViewListener() {
            @Override
            public void onBindView(TextView itemMsg, TextView itemDate, Object data) {
                IogisticsInfo sid = (IogisticsInfo) data;
                itemMsg.setText(sid.getWuliuInfo());
                itemDate.setText(sid.getTime());
            }
        });
    }
}
