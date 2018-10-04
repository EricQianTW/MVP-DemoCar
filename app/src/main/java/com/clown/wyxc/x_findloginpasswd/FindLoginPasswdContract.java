package com.clown.wyxc.x_findloginpasswd;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.UsersResult;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface FindLoginPasswdContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setFindPasswordResult(UsersResult result);

        void setSendCodeResult(String codeResult);
    }

    interface Presenter extends BaseInterfacePresenter {
        void findPassword(String param);

        void sendCode(String param);
    }
}
