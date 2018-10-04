package com.clown.wyxc.x_slide.slidefragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.utils.IntentUtils;
import com.clown.wyxc.x_bean.Banner;
import com.clown.wyxc.x_welcome.WelcomActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class SubSlideFragment extends Fragment {
    @Bind(R.id.iv_pics)
    ImageView ivPics;
    @Bind(R.id.bt_commit)
    TextView btCommit;
    private Banner mBanner;
    private int mLength;

    public static SubSlideFragment getInstance(Banner banner, int length) {
        SubSlideFragment sf = new SubSlideFragment();
        sf.mBanner = banner;
        sf.mLength = length;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.subslide_frg, null);
        ButterKnife.bind(this, v);

        ImageLoader.getInstance().displayImage(mBanner.getPic(), ivPics);

//        if (mBanner.getSort() == mLength) {
            btCommit.setVisibility(View.VISIBLE);
            btCommit.setText("立即体验");
//
            btCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentUtils.startActivityFinish(getActivity(), WelcomActivity.class);
                }
            });
//        }else{
//            btCommit.setVisibility(View.VISIBLE);
//            btCommit.setText("下一页");
//        }



        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}