package com.clown.wyxc.ali;

import android.app.Activity;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface AliPayContract {
    interface View extends BaseInterfaceView<Presenter> {
//        void setAliPayRes();
        void showMessage(String message);

        void setAliPayAppResult();
    }

    interface Presenter extends BaseInterfacePresenter {
//        void aliPay(Activity mContext, String subject, String out_orderid, String body, String price,String notifyurl_type, int passportid);

        void aliPayApp(Activity mContext,String param);
    }
}
