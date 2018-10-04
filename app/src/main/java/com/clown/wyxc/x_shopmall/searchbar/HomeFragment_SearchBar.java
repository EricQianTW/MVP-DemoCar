package com.clown.wyxc.x_shopmall.searchbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_search.SearchActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by eric_qiantw on 16/4/22.
 */
public class HomeFragment_SearchBar extends BaseFragment {

    @Bind(R.id.ll_home)
    LinearLayout llHome;
    protected boolean carShow = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_frg_searchbar, container, false);
        ButterKnife.bind(this, view);

        try {
            initViews(view);
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initAction() throws Exception {
        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startActivity(getActivity(), SearchActivity.class);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public HomeFragment_SearchBar() {

    }

    public static HomeFragment_SearchBar newInstance() {
        return new HomeFragment_SearchBar();
    }

    private void initViews(View view) throws Exception {
    }
}
