package com.clown.wyxc.x_changeheader;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.Users;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface ChangeHeaderContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setPostResult(List<String> result);
        void setUpdateHeadPicResult(int result);
        void setGetUsersByIdResult(Users result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void updateHeadPic(String param);
        void post(String param);
        void getUsersById(String param);
    }
}