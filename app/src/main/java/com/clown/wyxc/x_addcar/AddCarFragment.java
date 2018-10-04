package com.clown.wyxc.x_addcar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.clown.baselibray.material.widget.Spinner;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.CarCanNum;
import com.clown.wyxc.components.sublimepicker.Options;
import com.clown.wyxc.components.sublimepicker.SublimePickerFragment;
import com.clown.wyxc.tools.AllCapTransformationMethod;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.CarInfo;
import com.clown.wyxc.x_bean.CarInfoCResult;
import com.clown.wyxc.x_bean.MyCarsObjResult;
import com.clown.wyxc.x_bean.ResInteger;
import com.clown.wyxc.x_bean.x_parambean.CarInfoSave;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.clown.wyxc.x_brandlist.BrandListActivity;
import com.clown.wyxc.x_brandsublist.BrandSubListActivity;
import com.clown.wyxc.x_carwithyear.CarWithYearActivity;
import com.clown.wyxc.x_common.Constants;
import com.clown.wyxc.x_yearwithcar.YearWithCarActivity;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class AddCarFragment extends BaseFragment implements AddCarContract.View {

    @Bind(R.id.tv_title_brand)
    TextView tvTitleBrand;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.tv_title_chepai)
    TextView tvTitleChepai;
    @Bind(R.id.tv_sub_chepai)
    Spinner tvSubChepai;
    @Bind(R.id.tv_sub_chepai2)
    EditText tvSubChepai2;
    @Bind(R.id.tv_chepai)
    EditText tvChepai;
    @Bind(R.id.tv_title_xingshilicheng)
    TextView tvTitleXingshilicheng;
    @Bind(R.id.tv_gongli)
    TextView tvGongli;
    @Bind(R.id.tv_title_shangluriqi)
    TextView tvTitleShangluriqi;
    @Bind(R.id.tv_title_xinghao)
    TextView tvTitleXinghao;
    @Bind(R.id.tv_xinghao)
    TextView tvXinghao;
    @Bind(R.id.tv_title_fadongjihao)
    TextView tvTitleFadongjihao;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.tv_addcar)
    TextView tvAddcar;
    @Bind(R.id.tv_xingshilicheng)
    EditText tvXingshilicheng;
    @Bind(R.id.tv_shangluriqi)
    TextView tvShangluriqi;
    @Bind(R.id.tv_fadongjihao)
    EditText tvFadongjihao;
    @Bind(R.id.tv_default)
    TextView tvDefault;

    private AddCarContract.Presenter mPresenter;

    private CarInfoCResult carInfoCResult;
    private CarInfo carInfo;

    private ArrayAdapter<String> adapterSpinner;
    private List<CarCanNum> arrSpinner;

    private int carId = 0;
    private MyCarsObjResult mMyCarsObjResult = new MyCarsObjResult();
    private int initFlag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addcar_frg, container, false);
        ButterKnife.bind(this, view);

        // 让Fragment中的onCreateOptionsMenu生效必须先调用setHasOptionsMenu方法
        setHasOptionsMenu(true);
        // 在 Activity 重绘时，Fragment 不会被重复绘制，也就是它会被“保留”
        setRetainInstance(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initView();
            initAction();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tvSubChepai2.setTransformationMethod(new AllCapTransformationMethod());
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

    private void initData() {
        mPresenter.getCarCanNumInfo(getActivity());
    }

    /**
     * 根据一级选择的结果加载二级数据
     *
     * @param arrSub
     */
    private void setSubAdapterData(List<CarCanNum> arrSub) {
        try {
            String[] itemSubs = new String[arrSub.size()];
            for (int i = 0; i < arrSub.size(); i++) {
                itemSubs[i] = String.valueOf(arrSub.get(i).getCode().charAt(1));
            }
            ArrayAdapter<String> adapterSub = new ArrayAdapter<>(getActivity(), R.layout.row_spn, itemSubs);
            adapterSub.setDropDownViewResource(R.layout.row_spn_dropdown);
//            tvSubChepai2.setAdapter(adapterSub);

            Bundle data = getArguments();
            carId = data.getInt(AddCarActivity.INTENTNAME_CARID);

            if (carId != 0 && initFlag == 0) {
                tvAddcar.setText("保存");
                mPresenter.getMyCarsById(GSONUtils.paramToJson(new QueryId(carId, user.getId())));
                initFlag++;
            } else if (carId == 0) {
                tvAddcar.setText("添加车辆");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AddCarFragment() {
        new AddCarPresenter(this);
    }

    public static AddCarFragment newInstance() {
        return new AddCarFragment();
    }

    private void initAction() throws Exception {
        tvAddcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String chePai = tvSubChepai.getSelectedItem().toString() + tvSubChepai2.getText().toString() + tvChepai.getText().toString();
                    mPresenter.saveCarInfo(GSONUtils.paramToJson(new CarInfoSave(carId
                            , user.getId()
                            , mMyCarsObjResult.getCxId()
                            , mMyCarsObjResult.getCarId()
                            , Integer.parseInt("".equals(tvXingshilicheng.getText().toString()) ? "0" : tvXingshilicheng.getText().toString())
                            , chePai
                            , mMyCarsObjResult.getCarYear()
                            , tvFadongjihao.getText().toString())));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startFragmentForResult(AddCarFragment.this, getContext(), BrandListActivity.class);
            }
        });

        tvXinghao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mMyCarsObjResult.getCxId() == null) {
//                if (carInfoCResult == null || carInfoCResult.getCxList().size() == 0) {
                    T.showShort(getContext(), "请先选择品牌");
                } else {
                    HashMap map = new HashMap();
                    map.put(AddCarActivity.INTENTNAME_PARENTID, String.valueOf(mMyCarsObjResult.getCxId()));
                    IntentUtils.startFragmentForResult(AddCarFragment.this, getContext(), CarWithYearActivity.class, map);
                }
            }
        });

        tvSubChepai.setOnItemClickListener(new Spinner.OnItemClickListener() {
            @Override
            public boolean onItemClick(Spinner parent, View view, int position, long id) {
//                tvSubChepai2.removeAllViewsInLayout();
                setSubAdapterData(arrSpinner.get(position).getList());
                return true;
            }
        });

//        tvSubChepai2.setOnItemClickListener(new Spinner.OnItemClickListener() {
//            @Override
//            public boolean onItemClick(Spinner parent, View view, int position, long id) {
//                tvSubChepai2.setTag(arrSpinner.get(position).getList().get(position).getCode());
//                tvSubChepai2.removeAllViewsInLayout();
//                setSubAdapterData(arrSpinner.get(position).getList());
//                return true;
//            }
//        });

        tvShangluriqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SublimePickerFragment pickerFrag = new SublimePickerFragment();
                pickerFrag.setCallback(mFragmentCallback);

                Pair<Boolean, SublimeOptions> optionsPair = Options.getOptions();

                // Valid options
                Bundle bundle = new Bundle();
                bundle.putParcelable("SUBLIME_OPTIONS", optionsPair.second);
                pickerFrag.setArguments(bundle);

                pickerFrag.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                pickerFrag.show(getActivity().getSupportFragmentManager(), "SUBLIME_PICKER");
            }
        });
        tvDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.cilckSetDefault(GSONUtils.paramToJson(new QueryId(carId, user.getId())));
            }
        });
    }

    @Override
    public void setPresenter(@NonNull AddCarContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setSaveCarInfoResult(ResInteger result) {
        T.showShort(getContext(), "车辆添加成功");
        getActivity().finish();
    }

    @Override
    public void setGetMyCarsByIdResult(MyCarsObjResult result) {
        mMyCarsObjResult = result;

        tvBrand.setText(mMyCarsObjResult.getPpName() + mMyCarsObjResult.getCarCXName());
        tvXingshilicheng.setText(String.valueOf(mMyCarsObjResult.getKm()));
        tvXinghao.setText(mMyCarsObjResult.getCarName());
        tvFadongjihao.setText(mMyCarsObjResult.getEngineNo());
        tvShangluriqi.setText(mMyCarsObjResult.getCarYear());

        String first = String.valueOf(result.getCarNo().charAt(0));
        String second = String.valueOf(result.getCarNo().charAt(1));

        int indexFirst = -1;
        int indexSecond = -1;
        Hello:
        for (int i = 0; i < arrSpinner.size(); i++) {
            if (first.equals(arrSpinner.get(i).getCode())) {
                indexFirst = i;
                for (int j = 0; j < arrSpinner.get(i).getList().size(); j++) {
                    if (second.equals(arrSpinner.get(i).getList().get(j).getCode().substring(1))) {
                        indexSecond = j;
                        break Hello;
                    }
                }
            }
        }
        tvSubChepai.setSelection(indexFirst);
        tvSubChepai2.setSelection(indexSecond);
        tvChepai.setText(result.getCarNo().substring(2));

        if (mMyCarsObjResult.getIsDefault().intValue() != 1) {
            tvDefault.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCarCanNumInfoResult(List<CarCanNum> result) {
        arrSpinner = result;
        String[] items = new String[arrSpinner.size()];
        for (int i = 0; i < arrSpinner.size(); i++) {
            items[i] = arrSpinner.get(i).getCode();
        }
        adapterSpinner = new ArrayAdapter<>(getActivity(), R.layout.row_spn, items);
        adapterSpinner.setDropDownViewResource(R.layout.row_spn_dropdown);
        tvSubChepai.setAdapter(adapterSpinner);

        setSubAdapterData(arrSpinner.get(0).getList());
    }

    @Override
    public void setCilckSetDefaultResult(Integer result) {
        try {
            if (result == 1) {
                T.showShort(getContext(), "设置成功");
            }
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 回调方法，从第二个页面回来的时候会执行这个方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 根据上面发送过去的请求吗来区别
        try {
            switch (requestCode) {
                case Activity.RESULT_FIRST_USER:
                    if (resultCode == Constants.RETURN_CODE_COMMON) {
                        String carInfoStr = data.getStringExtra(BrandSubListActivity.INTENTNAME_CARINFOCX);
                        carInfoCResult = GSONUtils.fromJson(carInfoStr, CarInfoCResult.class);
                        tvBrand.setText(carInfoCResult.getName() + carInfoCResult.getCxList().get(0).getName());

                        mMyCarsObjResult.setCxId(carInfoCResult.getCxList().get(0).getId());
                        mMyCarsObjResult.setCId(carInfoCResult.getId());

                        tvXinghao.setText("");
                    } else if (resultCode == Constants.RETURN_CODE_COMMON2) {
                        String carInfoStr = data.getStringExtra(YearWithCarActivity.INTENTNAME_CARINFO);
                        carInfo = GSONUtils.fromJson(carInfoStr, CarInfo.class);
                        tvXinghao.setText(carInfo.getName());

                        mMyCarsObjResult.setCarId(carInfo.getId());
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate,
                                            int hourOfDay, int minute,
                                            SublimeRecurrencePicker.RecurrenceOption recurrenceOption,
                                            String recurrenceRule) {
            tvShangluriqi.setText(String.valueOf(selectedDate).split("\n")[0]);
            mMyCarsObjResult.setCarYear(String.valueOf(selectedDate).split("\n")[0]);
        }
    };
}