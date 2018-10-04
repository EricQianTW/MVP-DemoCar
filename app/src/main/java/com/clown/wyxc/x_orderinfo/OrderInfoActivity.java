package com.clown.wyxc.x_orderinfo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.ActivityUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.OrderInfoDetaliResult;
import com.clown.wyxc.x_bean.x_parambean.OrderQuery;
import com.clown.wyxc.x_orderinfo.orderaddress.OrderAddFragment;
import com.clown.wyxc.x_orderinfo.orderlist.OrderListFragment;
import com.clown.wyxc.x_orderinfo.orderstate.OrderStateFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CZP on 2016/7/24.
 * 订单详情  包括地址信息 物流信息 以及订单商品信息
 */
public class OrderInfoActivity extends BaseAppCompatActivity implements OrderInfoContract.View {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.orderstate)
    FrameLayout orderstate;
    @Bind(R.id.orderaddd)
    FrameLayout orderaddd;
    @Bind(R.id.orderlist)
    FrameLayout orderlist;

    private OrderInfoDetaliResult mdetail;
    private OrderInfoContract.Present mPresent;
    private int orderid;
    public final static String INTENTNAME_ORDERID = "orderid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderinfo_act);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initBack();

        try {
            orderid = getIntent().getIntExtra(INTENTNAME_ORDERID, 0);
            mPresent = new OrderInfoPresent(this);
            mPresent.getOrderByOrderId(GSONUtils.paramToJson(new OrderQuery(user.getId(), orderid)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPresenter(OrderInfoContract.Present presenter) {

    }

    public void initFragment() throws Exception {
        OrderStateFragment statefrg = (OrderStateFragment) getSupportFragmentManager()
                .findFragmentById(R.id.orderstate);
        OrderAddFragment addressfrag = (OrderAddFragment) getSupportFragmentManager()
                .findFragmentById(R.id.orderaddd);

        OrderListFragment listfrag = (OrderListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.orderlist);
        if (statefrg == null) {
            statefrg = OrderStateFragment.newInstance();
        }
        if (addressfrag == null) {
            addressfrag = OrderAddFragment.newInstance();
        }
        if (listfrag == null) {
            listfrag = OrderListFragment.newInstance();
        }

        Bundle statefrgbundle = new Bundle();
        statefrgbundle.putSerializable("orderStateClass", mdetail.getOrderDetailState());
        statefrgbundle.putSerializable("iogisticsInfo", mdetail.getIogisticsInfo());
        statefrgbundle.putSerializable("orderdate", mdetail.getOrderdate());
        statefrg.setArguments(statefrgbundle);

        Bundle listbundle = new Bundle();
        listbundle.putSerializable("MsgOrderInfoDetail", mdetail);
        listfrag.setArguments(listbundle);

        Bundle addressbunld = new Bundle();
        addressbunld.putSerializable("addressInfo", mdetail);
        addressfrag.setArguments(addressbunld);


        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), statefrg, R.id.orderstate);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), addressfrag, R.id.orderaddd);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), listfrag, R.id.orderlist);

    }

    @Override
    public void setGetOrderByOrderIdResult(OrderInfoDetaliResult result) {
        try {
            if (result != null) {
                mdetail = result;
                initFragment();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
