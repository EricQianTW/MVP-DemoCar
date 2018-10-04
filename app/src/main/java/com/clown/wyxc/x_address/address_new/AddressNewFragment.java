package com.clown.wyxc.x_address.address_new;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.bigkoo.pickerview.OptionsPickerView;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.realm.RAreaList;
import com.clown.wyxc.constant.Constants;
import com.clown.wyxc.utils.AMapUtils;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.KeyBoardUtils;
import com.clown.wyxc.utils.S;
import com.clown.wyxc.utils.ValidationUtils;
import com.clown.wyxc.x_bean.DeliveryAddress;
import com.clown.wyxc.x_bean.x_parambean.DeliveryAddressIns;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static com.clown.wyxc.R.id.rl_dingwei;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class AddressNewFragment extends BaseFragment implements AddressNewContract.View {
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.login_progress)
    ProgressBar loginProgress;
    @Bind(R.id.tv_receiver)
    AutoCompleteTextView tvReceiver;
    @Bind(R.id.tv_mobile)
    AutoCompleteTextView tvMobile;
    @Bind(R.id.tv_detailaddress)
    AutoCompleteTextView tvDetailaddress;
    @Bind(R.id.email_login_form)
    LinearLayout emailLoginForm;
    @Bind(R.id.login_form)
    NestedScrollView loginForm;
    @Bind(R.id.rl_location)
    RelativeLayout rlLocation;
    @Bind(R.id.cb_choice)
    CheckBox cbChoice;
    @Bind(R.id.arrow_right)
    ImageView arrowRight;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    @Bind(R.id.bt_addAddress)
    Button btAddAddress;
    @Bind(R.id.bt_deleteAddress)
    Button btDeleteAddress;
    @Bind(R.id.new_aaaa)
    LinearLayout newAaaa;
    @Bind(R.id.tv_dingwei)
    TextView tvDingwei;
    @Bind(rl_dingwei)
    RelativeLayout rlDingwei;

    private AddressNewContract.Presenter mPresenter;

    private OptionsPickerView pvOptions;
    private ArrayList<String> options1Items = new ArrayList<String>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<ArrayList<ArrayList<String>>>();

    private Integer newreceiverinfoid = null;
    private AMapUtils locaUtils;
    private String adcode = "";
    private String cityString = "";
    private String addressString = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addressnew_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);

        try {
            Bundle data = getArguments();
            String receive = data.getString(AddressNewActivity.INTENTNAME_NEWRECEIVERINFOID);
            if (receive != null) {
                newreceiverinfoid = Integer.parseInt(receive);
            }
            initView();
            initAction();
            initData();
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
        return view;
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
        ButterKnife.unbind(this);
    }

    private void initView() throws Exception {
        //选项选择器
        pvOptions = new OptionsPickerView(getActivity());

        locaUtils = new AMapUtils(getContext(), true, false, true);
        locaUtils.init();
    }

    private void initData() throws Exception {
        initCityInfo();

        // 新增或者修改
        if (newreceiverinfoid != -1) {
            btDeleteAddress.setVisibility(View.VISIBLE);
            mPresenter.getDeliveryAddressById(GSONUtils.paramToJson(new QueryId(newreceiverinfoid, user.getId())));
        } else {
            btDeleteAddress.setVisibility(View.GONE);
        }
    }

    public AddressNewFragment() {
        new AddressNewPresenter(this);
    }

    public static AddressNewFragment newInstance() {
        return new AddressNewFragment();
    }

    private void initAction() throws Exception {
        rlLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KeyBoardUtils.closeKeybord(getActivity());
                    RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
                    Realm.setDefaultConfiguration(realmConfig);
                    Realm realm = Realm.getDefaultInstance();

                    final RealmResults<RAreaList> puppiesProvince = realm.where(RAreaList.class).equalTo("level", "province").findAll();
                    final RealmResults<RAreaList> puppiesCity = realm.where(RAreaList.class).equalTo("level", "city").findAll();

                    ArrayList<String> level1 = new ArrayList<>();
                    for (RAreaList temp : puppiesProvince) {
                        level1.add(temp.getName());
                    }

                    options1Items.addAll(level1);
                    for (RAreaList province : puppiesProvince) {

                        ArrayList<RAreaList> cityList = new ArrayList<RAreaList>();
                        ArrayList<ArrayList<String>> districtLists = new ArrayList<ArrayList<String>>();
                        cityList.addAll(realm.where(RAreaList.class).equalTo("level", "city").beginsWith("adcode", province.getAdcode().substring(0, 2)).findAll());
                        ArrayList<String> level2 = new ArrayList<>();
                        for (RAreaList temp : cityList) {
                            level2.add(temp.getName());
                        }
                        for (RAreaList city : puppiesCity) {
                            if (province.getAdcode().substring(0, 2).equals(city.getAdcode().substring(0, 2))) {
                                ArrayList<RAreaList> districtList = new ArrayList<RAreaList>();
                                districtList.addAll(realm.where(RAreaList.class).equalTo("level", "district").beginsWith("adcode", city.getAdcode().substring(0, 4)).findAll());
                                ArrayList<String> level3 = new ArrayList<>();
                                for (RAreaList temp : districtList) {
                                    level3.add(temp.getName());
                                }
                                districtLists.add(level3);
                            }
                        }
                        options2Items.add(level2);
                        options3Items.add(districtLists);
                    }

                    //三级联动效果
                    pvOptions.setPicker(options1Items, options2Items, options3Items, true);
                    pvOptions.setTitle("选择城市");
                    pvOptions.setCyclic(false, true, true);
                    //设置默认选中的三级项目
                    //监听确定选择按钮
                    pvOptions.setSelectOptions(0, 0, 0);
                    pvOptions.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                try {
                    //返回的分别是三个级别的选中位置
                    String strProvince = options1Items.get(options1);
                    String strCity = options2Items.get(options1).get(option2);
                    String strDistrict = options3Items.get(options1).get(option2).get(options3);
                    String tx = strProvince + "_" + strCity + "_" + strDistrict;

                    getAdCode(strProvince, strCity, strDistrict);

                    tvLocation.setText(tx);
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });

        btAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkAddAddress()) {
                        focusView.requestFocus();
                    } else {
                        showProgress(true, loginProgress, loginForm);
                        mPresenter.saveDeliveryAddress(GSONUtils.paramToJson(new DeliveryAddressIns(newreceiverinfoid
                                , tvReceiver.getText().toString()
                                , tvMobile.getText().toString()
                                , tvLocation.getText().toString() + "_" + tvDetailaddress.getText().toString()
                                , user.getId()
                                , cbChoice.isChecked() ? 1 : 0
                                , adcode
                        )));
                    }
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });
        btDeleteAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newreceiverinfoid != -1) {
                    mPresenter.deleteDeliveryAddressById(GSONUtils.paramToJson(new QueryId(newreceiverinfoid, user.getId())));
                }
            }
        });

        locaUtils.setOnGetLocationListen(new AMapUtils.OnGetLocationListening() {
            @Override
            public void GetLocationListening(AMapLocation aMapLocation) {
                try {
                    cityString = locaUtils.getAMapLocation().getProvince() + locaUtils.getAMapLocation().getCity() + locaUtils.getAMapLocation().getDistrict();
                    addressString = locaUtils.getAMapLocation().getAddress().split(locaUtils.getAMapLocation().getDistrict())[1];
                    getAdCode(locaUtils.getAMapLocation().getProvince(), locaUtils.getAMapLocation().getCity(), locaUtils.getAMapLocation().getDistrict());
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        });

        rlDingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(cityString) || "".equals(addressString)) {
                    S.showShort(llMain, "定位正在进行，请稍候重试");
                }
                tvLocation.setText(cityString);
                tvDetailaddress.setText(addressString);
            }
        });
    }

    private void initCityInfo() {
        try {
            RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
            Realm.setDefaultConfiguration(realmConfig);
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();

            final RealmResults<RAreaList> removeList = realm.where(RAreaList.class).findAll();
            removeList.deleteAllFromRealm();

            InputStreamReader isr = new InputStreamReader(getActivity().getAssets().open("districtlist.json"), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
            JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
            //直接传入JSONObject来构造一个实例
            JSONArray array = testjson.getJSONArray("body");         //从JSONObject中取出数组对象
            for (int i = 0; i < array.length(); i++) {
                JSONObject role = array.getJSONObject(i);    //取出数组中的对象

                RAreaList country = realm.createObject(RAreaList.class);
                country.setAdcode(role.getString("adcode"));
                country.setLevel(role.getString("level"));
                country.setName(role.getString("name"));
            }
            realm.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAdCode(String strProvince, String strCity, String strDistrict) throws Exception {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getContext()).build();
        Realm.setDefaultConfiguration(realmConfig);
        Realm realm = Realm.getDefaultInstance();

        final RealmResults<RAreaList> puppiesProvince = realm.where(RAreaList.class).equalTo("name", strProvince).findAll();

        if (puppiesProvince.size() != 0) {
            String provinceAdcode = puppiesProvince.get(0).getAdcode();
            RealmResults<RAreaList> tempCity = realm.where(RAreaList.class).equalTo("name", strCity).beginsWith("adcode", provinceAdcode.substring(0, 2)).findAll();
            if (tempCity.size() > 0) {
                String cityAdcode = tempCity.get(0).getAdcode();
                RealmResults<RAreaList> tempDistinct = realm.where(RAreaList.class).equalTo("name", strDistrict).beginsWith("adcode", cityAdcode.substring(0, 4)).findAll();
                if (tempDistinct.size() > 0) {
                    adcode = tempDistinct.get(0).getAdcode();
                } else {
                    S.showShort(llMain, "定位失败，请重试");
                }
            } else {
                S.showShort(llMain, "定位失败，请重试");
            }
        } else {
            S.showShort(llMain, "定位失败，请重试");
        }
    }

    /**
     * 验证画面控件
     *
     * @return
     */
    private boolean checkAddAddress() throws Exception {
        //Reset errors
        ValidationUtils.resetErrorControls(loginForm);
        if (validation.isEmpty(tvReceiver, validation.isEmptyMessage("收件人"))) {
            focusView = tvReceiver;
            return true;
        }

        if (validation.isEmpty(tvMobile, validation.isEmptyMessage("联系电话"))) {
            focusView = tvReceiver;
            return true;
        }

        if (validation.isNotMobileNumber(tvMobile, validation.isNotMobileNumberMessage("联系电话"))) {
            focusView = tvMobile;
            return true;
        }

        if (validation.isEmpty(tvLocation, validation.isEmptyMessage("所在地区"))) {
            focusView = tvLocation;
            return true;
        }

        if (validation.isEmpty(tvDetailaddress, validation.isEmptyMessage("详细地址"))) {
            focusView = tvDetailaddress;
            return true;
        }

        return false;
    }

    @Override
    public void setPresenter(@NonNull AddressNewContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setSaveDeliveryAddressResult(int result) {
        try {
            if (result == Constants.OKHTTP_RESULT_SUCESS) {
                showProgress(false, loginProgress, loginForm);
                S.showShort(llMain, "保存成功");
                getActivity().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDeleteDeliveryAddressByIdResult(int result) {
        try {
            if (result == Constants.OKHTTP_RESULT_SUCESS) {
                showProgress(false, loginProgress, loginForm);
                S.showShort(llMain, "删除成功");
                getActivity().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCilckSetDefaultResult(int result) {
        try {
            if (result == Constants.OKHTTP_RESULT_SUCESS) {
                showProgress(false, loginProgress, loginForm);
                S.showShort(llMain, "设置成功");
                getActivity().finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGetDeliveryAddressByIdResult(DeliveryAddress result) {
        try {
            tvReceiver.setText(result.getConsignee());
            tvMobile.setText(result.getPhone());
            List<String> arr = Arrays.asList(result.getAddress().split("_"));
            tvLocation.setText(arr.get(0) + "_" + arr.get(1) + "_" + arr.get(2));
            tvDetailaddress.setText(arr.get(3));
            adcode = result.getAdCode();
            if (result.getIsDefault() == 1) {
                cbChoice.setChecked(true);
                cbChoice.setEnabled(false);
            } else {
                cbChoice.setChecked(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
