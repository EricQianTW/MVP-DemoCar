package com.clown.wyxc.x_feedback;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface FeedbackContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setPostResult(List<String> result);
        void setPostFeedBackResult(int result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void post(String param);
        void postFeedBack(String param);
    }
}