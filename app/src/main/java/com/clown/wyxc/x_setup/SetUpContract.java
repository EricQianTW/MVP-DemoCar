package com.clown.wyxc.x_setup;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.Users;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface SetUpContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setUpdateReceiveNoticeResult(int result);
        void setGetUsersByIdResult(Users result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void updateReceiveNotice(String param);
        void getUsersById(String param);
    }
}
