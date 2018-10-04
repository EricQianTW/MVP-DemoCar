package com.clown.wyxc.x_findpaypassword;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface FindPayPasswdContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setUpdatePayPasswordResult(int result);

        void setSendCodeResult(String codeResult);
    }

    interface Presenter extends BaseInterfacePresenter {
        void updatePayPassword(String param);

        void sendCode(String param);
    }
}
