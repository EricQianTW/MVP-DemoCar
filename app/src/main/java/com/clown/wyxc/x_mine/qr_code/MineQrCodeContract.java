package com.clown.wyxc.x_mine.qr_code;


import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.MsgUserInfo;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface MineQrCodeContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setReback(MsgUserInfo now_ok);
    }
    interface Presenter extends BaseInterfacePresenter {
        void getPhone(String verify,int userId);
    }
}
