package com.clown.wyxc.x_classify;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GoodsTypeResult;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public interface ClassifyContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setGetGoodsTypeResult(List<GoodsTypeResult> result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void getGoodsType(String param);
    }
}
