package com.clown.wyxc.x_mycar;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MyCarsResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface MyCarContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetMyCarsByUsersIdResult(List<MyCarsResult> result);
        void setDeleteMyCarsByIdResult(Integer result);
        void setCilckSetDefaultResult(Integer result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMyCarsByUsersId(String param);
        void deleteMyCarsById(String param);
        void cilckSetDefault(String param);
    }
}