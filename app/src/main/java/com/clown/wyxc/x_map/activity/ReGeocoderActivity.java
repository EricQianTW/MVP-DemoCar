package com.clown.wyxc.x_map.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.clown.wyxc.R;
import com.clown.wyxc.x_map.util.AMapUtil;
import com.clown.wyxc.x_map.util.ToastUtil;

import java.io.File;
import java.net.URISyntaxException;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

/**
 * 地理编码与逆地理编码功能介绍
 */
public class ReGeocoderActivity extends Activity implements
        GeocodeSearch.OnGeocodeSearchListener, OnClickListener {
    @Bind(R.id.iv_daohang)
    ImageView ivDaohang;
    private ProgressDialog progDialog = null;
    private GeocodeSearch geocoderSearch;
    private String addressName,latName,lonName,addName;
    private AMap aMap;
    private MapView mapView;
    private Marker geoMarker;
    private Dialog private_chat_dialog;

    public static String INTANTNAME_ADDRESS = "address";
    private String addressValue = "南通市";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geocoder_activity);
        ButterKnife.bind(this);

        addressValue = getIntent().getStringExtra(INTANTNAME_ADDRESS);
        /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
        //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
//        MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        try {
            if (aMap == null) {
                aMap = mapView.getMap();
                geoMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                        .icon(BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            }
            Button geoButton = (Button) findViewById(R.id.geoButton);
            geoButton.setText("GeoCoding(北京市朝阳区方恒国际中心A座)");
            geoButton.setOnClickListener(this);
            geocoderSearch = new GeocodeSearch(this);
            geocoderSearch.setOnGeocodeSearchListener(this);
            progDialog = new ProgressDialog(this);

            ivDaohang.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    private_chat_dialog = new Dialog(ReGeocoderActivity.this, R.style.MyDialogStyleTheme);
                    // 设置它的ContentView
                    private_chat_dialog.setContentView(R.layout.prompt_navigation);
                    TextView prompt_title = (TextView) private_chat_dialog.findViewById(R.id.prompt_title);
                    prompt_title.setText("选择导航软件");
                    if (private_chat_dialog != null) {
                        private_chat_dialog.show();
                    }
//                    WindowManager windowManager = ((Activity) getApplicationContext()).getWindowManager();
                    WindowManager windowManager = (ReGeocoderActivity.this).getWindowManager();
                    Display display = windowManager.getDefaultDisplay();
                    WindowManager.LayoutParams lp = private_chat_dialog.getWindow().getAttributes();
                    lp.width = (int) (display.getWidth()); // 设置宽度
                    private_chat_dialog.getWindow().setAttributes(lp);
                    Window window = private_chat_dialog.getWindow();
                    RelativeLayout rl_tengxun = (RelativeLayout) window.findViewById(R.id.rl_tengxun);
                    RelativeLayout rl_gaode = (RelativeLayout) window.findViewById(R.id.rl_gaode);
                    TextView tv_gaode = (TextView) window.findViewById(R.id.tv_gaode);
                    TextView tv_tengxun = (TextView) window.findViewById(R.id.tv_tengxun);

                    if (isInstallByread("com.baidu.BaiduMap")) {
                        rl_tengxun.setEnabled(true);
                    } else {
                        rl_tengxun.setEnabled(false);
                        tv_tengxun.setTextColor(getResources().getColor(R.color.gray));
                    }
                    if (isInstallByread("com.autonavi.minimap")) {
                        rl_gaode.setEnabled(true);
                    } else {
                        rl_gaode.setEnabled(false);
                        tv_gaode.setTextColor(getResources().getColor(R.color.gray));
                    }
                    rl_gaode.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            try {
                                intent = new Intent("android.intent.action.VIEW",
                                        android.net.Uri.parse("androidamap://navi?sourceApplication=税源地图&lat=" + latName + "&lon=" + lonName + "&dev=0&style=2"));
                                startActivity(intent); // 启动调用
                            } catch (Exception e) {
                                Log.e(TAG, e.toString());
                            }
                            startActivity(intent);
                            private_chat_dialog.dismiss();
                        }
                    });
                    rl_tengxun.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            try {
                                intent = Intent.getIntent
                                        ("intent://map/navi?location="+latName+","+lonName +
                                                "&type=TIME&src=thirdapp.navi.hndist.sydt#Intent;scheme=bdapp;" +
                                                "package=com.baidu.BaiduMap;end");
                            } catch (URISyntaxException e) {
                                Log.e(TAG, e.toString());
                            }
                            startActivity(intent);
                            private_chat_dialog.dismiss();
                        }
                    });

                }
            });

            getLatlon(addressValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在获取地址");
        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 响应地理编码
     */
    public void getLatlon(final String name) {
        showDialog();

        GeocodeQuery query = new GeocodeQuery(name, "0513");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getGeocodeAddressList() != null
                    && result.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = result.getGeocodeAddressList().get(0);
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        AMapUtil.convertToLatLng(address.getLatLonPoint()), 15));
                geoMarker.setPosition(AMapUtil.convertToLatLng(address
                        .getLatLonPoint()));

                addressName = "经纬度值:" + address.getLatLonPoint() + "\n位置描述:"
                        + address.getFormatAddress();
                latName = String.valueOf(address.getLatLonPoint().getLatitude());
                lonName = String.valueOf(address.getLatLonPoint().getLongitude());
                addName = address.getFormatAddress();
                ToastUtil.show(ReGeocoderActivity.this, addressName);
            } else {
                ToastUtil.show(ReGeocoderActivity.this, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(this, rCode);
        }
    }

    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 响应地理编码按钮
             */
            case R.id.geoButton:
                getLatlon(addressValue);
                break;
            default:
                break;
        }
    }
}