package com.clown.wyxc.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.amap.api.location.AMapLocation;
import com.clown.wyxc.scheme.ExceptionHandle;
import com.clown.wyxc.utils.ValidationUtils;
import com.clown.wyxc.utils.ViewUtils;
import com.clown.wyxc.x_bean.UsersResult;
import com.flipboard.bottomsheet.commons.BottomSheetFragment;
import com.orhanobut.logger.Logger;

/**
 * Created by eric_qiantw on 16/6/7.
 */
public class BaseBottomSheetFragment extends BottomSheetFragment {

    // 当前class名
    protected String TAG = this.getClass().getName();

    // 声明控件check对象
    protected ValidationUtils validation;

    // 声明焦点控件
    protected View focusView = null;

    // 登录者
    public static UsersResult user;
    public static boolean islogin;

    protected static AMapLocation aMapLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        validation = new ValidationUtils(getContext());
        user = ((BaseAppCompatActivity) getActivity()).getUser();
        aMapLocation = ApplicationManager.getInstance().getaMapLocation();
    }

    @Override
    public void onResume() {
        super.onResume();
        islogin = ((BaseAppCompatActivity) getActivity()).isLogin();
    }

    /**
     * Shows the progress UI and hides the register form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show, final ProgressBar progressBar, final ViewGroup viewGroup) throws Exception{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            ViewUtils.disableSubControls(viewGroup,show);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            ViewUtils.disableSubControls(viewGroup,show);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void showError(int errorCode, String errorMessage) {
        try {
            ExceptionHandle.handleError(getContext(), errorCode, errorMessage);
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }
}
