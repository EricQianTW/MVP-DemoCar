package com.clown.wyxc.x_map.activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.x_map.util.AMapUtil;
import com.clown.wyxc.x_map.util.ToastUtil;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 地理编码与逆地理编码功能介绍
 */
public class GaodeMapForSchemeActivity extends BaseAppCompatActivity implements LocationSource,AMapLocationListener,OnClickListener,GeocodeSearch.OnGeocodeSearchListener {
	private static final String TAG = "TalkGeocoderActivity";
	private ProgressDialog progDialog = null;
	private GeocodeSearch geocoderSearch;
	private String addressName;
	private AMap aMap;
	private MapView mapView;
	private LatLonPoint latLonPoint;
	private Marker geoMarker;
	private Marker regeoMarker;

	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;

	private String citycode = "0513";
	private String address = "";
	private String pointname = "";
	private String latitude = "";
	private String longitude = "";
	private Dialog private_chat_dialog;

	private ImageView iv_back;
	private TextView action;

	public static String INTENTNAME_ADDRESS = "address";
	public static String INTENTNAME_LATITUDE = "latitude";
	public static String INTENTNAME_LONGITUDE = "longitude";
	public static String INTENTNAME_POINTNAME = "pointname";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geocoder);

		try {
			mapView = (MapView) findViewById(R.id.map);
			mapView.onCreate(savedInstanceState);// 此方法必须重写

			find();
			init();
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	private void find() {
		address = getIntent().getStringExtra(INTENTNAME_ADDRESS);
		latitude = getIntent().getStringExtra(INTENTNAME_LATITUDE);
		longitude = getIntent().getStringExtra(INTENTNAME_LONGITUDE);
		pointname = getIntent().getStringExtra(INTENTNAME_POINTNAME);

		iv_back = (ImageView) findViewById(R.id.img_left);
		iv_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		action = (TextView) findViewById(R.id.img_right);
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			geoMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
			regeoMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
		}
		geocoderSearch = new GeocodeSearch(this);
		geocoderSearch.setOnGeocodeSearchListener(this);
		progDialog = new ProgressDialog(this);

//		action.setText("导航");

		if(latitude != null
				&& longitude != null
				&& "0.00".equals(latitude)
				&& !"0.00".equals(longitude)){
			latLonPoint = new LatLonPoint(Double.parseDouble(latitude), Double.parseDouble(longitude));
			getAddress(latLonPoint);
		}else if(address != null && !"".equals(address)){
			getLatlon(address);
		}
		action.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				private_chat_dialog = new Dialog(getApplicationContext(), R.style.MyDialogStyleTheme);
				// 设置它的ContentView
				private_chat_dialog.setContentView(R.layout.prompt_navigation);
				TextView prompt_title = (TextView) private_chat_dialog.findViewById(R.id.prompt_title);
				prompt_title.setText("选择导航软件");
				if(private_chat_dialog != null){
					private_chat_dialog.show();
				}
				WindowManager windowManager = getWindowManager();
				Display display = windowManager.getDefaultDisplay();
				WindowManager.LayoutParams lp = private_chat_dialog.getWindow().getAttributes();
				lp.width = (int) (display.getWidth()); // 设置宽度
				private_chat_dialog.getWindow().setAttributes(lp);
				Window window = private_chat_dialog.getWindow();
				RelativeLayout fontsize_cb_14 = (RelativeLayout) window.findViewById(R.id.rl_gaode);
				RelativeLayout fontsize_cb_18 = (RelativeLayout) window.findViewById(R.id.rl_tengxun);

				if (isInstallByread("com.baidu.BaiduMap")) {
					fontsize_cb_18.setVisibility(View.VISIBLE);
				} else {
					fontsize_cb_18.setVisibility(View.GONE);
				}
				if (isInstallByread("com.autonavi.minimap")) {
					fontsize_cb_14.setVisibility(View.VISIBLE);
				} else {
					fontsize_cb_14.setVisibility(View.GONE);
				}
				fontsize_cb_14.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						try {
							intent = Intent
									.getIntent("androidamap://viewMap?sourceApplication=移动门店&poiname="+ pointname +"&lat="+latitude+"&lon="+longitude+"&dev=0");
						} catch (URISyntaxException e) {
							Log.e(TAG, e.toString());
						}
						startActivity(intent);

						private_chat_dialog.dismiss();
					}
				});
				fontsize_cb_18.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						try {
							intent = Intent.getIntent("intent://map/marker?location="+ latitude +","+ longitude +"&title=我的位置&content="+pointname+"&src=移动门店#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
						} catch (URISyntaxException e) {
							Log.e(TAG, e.toString());
						}
						startActivity(intent);
						private_chat_dialog.dismiss();
					}
				});
			}
		});
	}

	private boolean isInstallByread(String packageName)
	{
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
		if (null != mlocationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			mlocationClient.onDestroy();
			mlocationClient = null;
			mLocationOption = null;
		}
	}

//	@Override
//	protected void initView() throws Exception {
//
//	}
//
//	@Override
//	protected void initAction() throws Exception {
//
//	}
//
//	@Override
//	protected void initData() throws Exception {
//
//	}

	/**
	 * 显示进度条对话框
	 */
	public void showDialog() {
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在获取地址...");
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

		GeocodeQuery query = new GeocodeQuery(name, citycode);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
		geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
	}

	/**
	 * 响应逆地理编码
	 */
	public void getAddress(final LatLonPoint latLonPoint) {
		showDialog();
		RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
				GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
		geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
	}

	/**
	 * 地理编码查询回调
	 */
	@Override
	public void onGeocodeSearched(GeocodeResult result, int rCode) {
		dismissDialog();
		if (rCode == 0) {
			if (result != null && result.getGeocodeAddressList() != null
					&& result.getGeocodeAddressList().size() > 0) {
				GeocodeAddress address = result.getGeocodeAddressList().get(0);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						AMapUtil.convertToLatLng(address.getLatLonPoint()), 15));
				geoMarker.setPosition(AMapUtil.convertToLatLng(address
						.getLatLonPoint()));
				addressName = getString(R.string.longitude_and_latitude) + address.getLatLonPoint() + getString(R.string.position_description)
						+ address.getFormatAddress();
				ToastUtil.show(GaodeMapForSchemeActivity.this, addressName);
			} else {
				ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.no_result);
			}
		} else if (rCode == 27) {
			ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.error_network);
		} else if (rCode == 32) {
			ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.error_key);
		} else {
			ToastUtil.show(GaodeMapForSchemeActivity.this,
					getString(R.string.error_other) + rCode);
		}
	}

	/**
	 * 逆地理编码回调
	 */
	@Override
	public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
		dismissDialog();
		if (rCode == 0) {
			if (result != null && result.getRegeocodeAddress() != null
					&& result.getRegeocodeAddress().getFormatAddress() != null) {
				addressName = result.getRegeocodeAddress().getFormatAddress()
						+ getString(R.string.around);
				aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
						AMapUtil.convertToLatLng(latLonPoint), 15));
				regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
				ToastUtil.show(GaodeMapForSchemeActivity.this, addressName);
			} else {
				ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.no_result);
			}
		} else if (rCode == 27) {
			ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.error_network);
		} else if (rCode == 32) {
			ToastUtil.show(GaodeMapForSchemeActivity.this, R.string.error_key);
		} else {
			ToastUtil.show(GaodeMapForSchemeActivity.this,
					getString(R.string.error_other) + rCode);
		}
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();

			initOption();

			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			mlocationClient.startLocation();
		}
	}

	// 根据控件的选择，重新设置定位参数
	private void initOption() {
		// 设置定位模式为高精度模式
		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置定位监听
		mlocationClient.setLocationListener(this);
		// 设置是否需要显示地址信息
		mLocationOption.setNeedAddress(true);
		/**
		 * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
		 * 注意：只有在高精度模式下的单次定位有效，其他方式无效
		 */
		mLocationOption.setGpsFirst(false);
//		if (!TextUtils.isEmpty(strInterval)) {
		// 设置发送定位请求的时间间隔,最小值为2000，如果小于2000，按照2000算
		mLocationOption.setInterval(Long.valueOf("10000"));
//		}
	}

	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
				citycode = amapLocation.getCity();
				address = amapLocation.getAddress();
				latLonPoint = new LatLonPoint(amapLocation.getLatitude(), amapLocation.getLongitude());
				getAddress(latLonPoint);
				aMap.setMyLocationEnabled(false);
			} else {
				String errText = getString(R.string.seek_fail) + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr",errText);
			}
		}
	}
}