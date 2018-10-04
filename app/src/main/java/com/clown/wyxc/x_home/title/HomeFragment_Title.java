package com.clown.wyxc.x_home.title;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.ApplicationManager;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_citychoose.CityChooseActivity;
import com.clown.wyxc.x_message.MessageActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_Title extends BaseFragment implements HomeContract_Title.View {

    @Bind(R.id.location_tv)
    TextView locationTv;
    @Bind(R.id.message_img)
    ImageView messageImg;
    @Bind(R.id.cv_main)
    RelativeLayout cvMain;

    private HomeContract_Title.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_title, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        try {
            initViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initViews() throws Exception {
        locationTv.setText(ApplicationManager.getInstance().getaMapLocation().getCity());
    }

    public HomeFragment_Title() {

    }

    public static HomeFragment_Title newInstance() {
        return new HomeFragment_Title();
    }

    private void initAction() {
        messageImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentUtils.startActivity(getContext(), VehicletradeListActivity.class);
                IntentUtils.startActivity(getContext(), MessageActivity.class);
            }
        });
        locationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap map = new HashMap();
                map.put(CityChooseActivity.INTETNNAME_FROM, "0");
                IntentUtils.startActivityForResult(getContext(), CityChooseActivity.class, map);
            }
        });
    }

    @Override
    public void setPresenter(HomeContract_Title.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
