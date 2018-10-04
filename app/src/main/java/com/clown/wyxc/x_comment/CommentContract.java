package com.clown.wyxc.x_comment;

import com.clown.wyxc.base.BaseInterfacePresenter;
import com.clown.wyxc.base.BaseInterfaceView;
import com.clown.wyxc.x_bean.GetCommOrderItemInfoResult;

import java.util.List;

/**
 * Created by CZP on 2016/7/11.
 */
public interface CommentContract {

    interface View extends BaseInterfaceView<Presenter> {
//        void getAddGoodsCommentResult(MsgImage msgImage);
//        void getcommentResult(Boolean b);
//        void getCommOrderItemInfoResult(List<MsgOrderItemDetail> list);

        void setGetCommOrderItemInfoResult(GetCommOrderItemInfoResult result);
        void setAddEvaluateByOrderIdResult(String result);
        void setPostResult(List<String> result);
    }

    interface Presenter extends BaseInterfacePresenter {
//        void uploadImage(String verf, int userid
//                , int[] imagelist);
//
//        void addGoodsComment(String verf, int userid, List<MsgAfferentComm> msgAfferentCwommList, int orderid);
//
//        void getCommOrderItemInfo(String verf, int useid, int orderid);
        void getCommOrderItemInfo(String param);
        void addEvaluateByOrderId(String param);
        void post(String param);
    }
}
