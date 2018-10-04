package com.clown.wyxc.x_login;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.UsersResult;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface LoginContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setLoginResult(UsersResult result);

        void setSendCodeResult(String codeResult);
    }

    interface Presenter extends BaseInterfacePresenter {
        void loginCode(String param);

        void login(String param);

        void sendCode(String param);

        void findPassword(String param);
    }
}
