package com.clown.wyxc.x_addcar;

import android.app.Activity;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.bean.CarCanNum;
import com.clown.wyxc.x_bean.MyCarsObjResult;
import com.clown.wyxc.x_bean.ResInteger;

import java.util.List;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public interface AddCarContract {
    interface View extends BaseInterfaceView<Presenter> {
        void setSaveCarInfoResult(ResInteger result);
        void setGetMyCarsByIdResult(MyCarsObjResult result);
        void setCarCanNumInfoResult(List<CarCanNum>  result);
        void setCilckSetDefaultResult(Integer result);
    }

    interface Presenter extends BaseInterfacePresenter {
        void saveCarInfo(String param);
        void getMyCarsById(String param);
        void getCarCanNumInfo(Activity activity);
        void cilckSetDefault(String param);
    }
}