package com.clown.wyxc.x_message;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.MessageResult;

import java.util.List;

/**
 * Created by CZP on 2016/7/19.
 */
public interface MessageContract {

    interface View extends BaseInterfaceView<Presenter>{
        void setgetMessageListResult(List<MessageResult> result);
    }

    interface Presenter extends BaseInterfacePresenter{
        void getMessageList(String param);
    }
}


