package com.clown.wyxc.x_welcome;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.clown.wyxc.MainActivity;
import com.clown.wyxc.R;
import com.clown.wyxc.base.AppManager;
import com.clown.wyxc.base.ApplicationManager;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.update.LoadAnsyReadVersionXML;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.AMapUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.SDCard.ExternalDataManager;
import com.clown.wyxc.utils.SPUtils;
import com.clown.wyxc.x_slide.SlideActivity;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.Bind;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class WelcomeFragment extends BaseFragment implements WelcomeContract.View, EasyPermissions.PermissionCallbacks {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    private WelcomeContract.Presenter mPresenter;

    private AMapUtils locaUtils;

    String[] perms = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_COARSE_LOCATION
            };

    public WelcomeFragment() {
        new WelcomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_frg, container, false);
        // ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        boolean first = (boolean) SPUtils.get(getActivity(),SPUtils.SP_BOOLEAN_FIRST,true);
        if(first){
            SPUtils.put(getActivity(), SPUtils.SP_BOOLEAN_FIRST, false);
            IntentUtils.startActivityFinish(getActivity(), SlideActivity.class);
        }else{
            allPermission();
        }

        return view;
    }

    private void initsdcard() throws Exception {
        if (ExternalDataManager.ExternalDataPath() == "") {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("内存设备未准备好，无法运行");
            builder.setNegativeButton("知道了",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            AppManager.getAppManager().AppExit(getContext());
                        }
                    });
            builder.create().show();
        }
    }

    private void jumpto() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.startActivityFinish(getActivity(), MainActivity.class);
            }
        }, 2000);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ButterKnife.unbind(this);
    }

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    private void initAction() {
        locaUtils.setOnGetLocationListen(new AMapUtils.OnGetLocationListening() {
            @Override
            public void GetLocationListening(AMapLocation aMapLocation) {
                try {
                    ApplicationManager.getInstance().setaMapLocation(locaUtils.getAMapLocation());
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void setPresenter(@NonNull WelcomeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @AfterPermissionGranted(Constants.RC_PERM)
    public void allPermission() {
        if (EasyPermissions.hasPermissions(getContext(), perms)) {
            locaUtils = new AMapUtils(getContext(), true, false, true);
            locaUtils.init();
            initAction();
            compareGo();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "App运行过程中需要以下权限，拒绝后部分功能不能正常使用",
                    Constants.RC_PERM, perms);
        }
    }

    private void compareGo() {
        try {
            initsdcard();

            LoadAnsyReadVersionXML loadXml = new LoadAnsyReadVersionXML(getContext());
            loadXml.execute();

            loadXml.setOnPassUpdateListening(new LoadAnsyReadVersionXML.OnPassUpdateListening() {
                @Override
                public void PassUpdateListening() {
                    jumpto();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        compareGo();
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        compareGo();
    }
}
