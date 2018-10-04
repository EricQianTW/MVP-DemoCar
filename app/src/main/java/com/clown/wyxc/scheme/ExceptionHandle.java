package com.clown.wyxc.scheme;

import android.content.Context;

import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_address.address_list.AddressListActivity;
import com.clown.wyxc.x_login.LoginActivity;
import com.clown.wyxc.x_mycar.MyCarActivity;
import com.orhanobut.logger.Logger;


/**
 * Created by eric_qiantw on 16/6/10.
 */
public class ExceptionHandle {
    // 当前class名
    protected final static String TAG = "ExceptionHandle";

    public static void handleError(Context mContext, int errorCode, String errorMessage) {
        try {
            if (ErrorCode.ERROR_CODE_NOTLOGIN == errorCode) {
                IntentUtils.startActivity(mContext, LoginActivity.class);
            } else if (ErrorCode.ERROR_CODE_NOTDEFALUTADDRESS == errorCode) {
                IntentUtils.startActivity(mContext, AddressListActivity.class);
            } else if (ErrorCode.ERROR_CODE_NOTMYCAR == errorCode) {
                IntentUtils.startActivity(mContext, MyCarActivity.class);
            }

            T.showShort(mContext, errorMessage);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

}
