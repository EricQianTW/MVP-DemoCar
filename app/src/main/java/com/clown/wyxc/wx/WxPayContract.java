package com.clown.wyxc.wx;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface WxPayContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setWeiXinPayAppResult();
    }

    interface Presenter extends BaseInterfacePresenter {
        void weiXinPayApp(final IWXAPI msgApi, String param);
    }
}
