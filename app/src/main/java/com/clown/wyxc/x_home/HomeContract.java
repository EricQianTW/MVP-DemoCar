package com.clown.wyxc.x_home;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract {

    interface View extends BaseInterfaceView<Presenter> {
        void settest();
    }

    interface Presenter extends BaseInterfacePresenter {
        void test();
    }
}
