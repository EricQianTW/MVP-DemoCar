package com.clown.wyxc.tools;

import android.os.CountDownTimer;

/**
 * Created by eric_clown on 2017/5/6.
 */

public class CountDownTimerTool extends CountDownTimer {

    /**
     * @param millisInFuture    表示以毫秒为单位 倒计时的总数
     *                          <p>
     *                          例如 millisInFuture=1000 表示1秒
     * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
     *                          <p>
     *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
     */
    public CountDownTimerTool(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mOnCountDownListening.onTickListening(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        mOnCountDownListening.onFinishListening();
    }

    public interface OnCountDownListening {
        void onTickListening(long millisUntilFinished);
        void onFinishListening();
    }

    OnCountDownListening mOnCountDownListening = null;

    public void setOnCountDownListening(OnCountDownListening e) {
        mOnCountDownListening = e;
    }
}
