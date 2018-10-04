package com.clown.wyxc.photo;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface PhotoContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setArticle();
    }

    interface Presenter extends BaseInterfacePresenter {

        void getArticle();
    }
}
