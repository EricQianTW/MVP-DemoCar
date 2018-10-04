package com.clown.wyxc.x_mine.header;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.Users;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface MineContract_Header {

    interface View extends BaseInterfaceView<Presenter> {
        void setGetUsersByIdResult(Users result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getUsersById(String param);
    }
}
