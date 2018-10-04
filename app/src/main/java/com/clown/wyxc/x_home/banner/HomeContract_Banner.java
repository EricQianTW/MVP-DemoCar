package com.clown.wyxc.x_home.banner;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.Banner;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface HomeContract_Banner{

    interface View extends BaseInterfaceView<Presenter> {
        void setGetBannerListByQueryResult(List<Banner> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getBannerListByQuery(String param);
    }
}
