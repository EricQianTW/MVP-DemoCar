package com.clown.wyxc.x_companydetail;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;

/**
 * Created by cc on 2016/8/4.
 */
public interface CompanyDetailContract {

    interface View extends BaseInterfaceView<Present>{
        void modifyGoodsCollectionResult(int i);
    };

    interface Present extends BaseInterfacePresenter{
        void modifyGoodsCollection(String verf,int userid,int goodsId);
    }
}
