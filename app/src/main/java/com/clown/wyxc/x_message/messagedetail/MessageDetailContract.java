package com.clown.wyxc.x_message.messagedetail;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgPushMessage;

import java.util.List;

/**
 * Created by CZP on 2016/7/19.
 */
public interface MessageDetailContract {

    interface View extends BaseInterfaceView<Presenter> {
        void setMsgListInfoResult(List<MsgPushMessage> list);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getMsgListInfo(String verf,int userid,int currentpage,int sendTpy);
    }
}
