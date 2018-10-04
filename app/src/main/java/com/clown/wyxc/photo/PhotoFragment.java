package com.clown.wyxc.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bm.library.PhotoView;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.loadimage.ImageLoadOptions;
import com.clown.wyxc.scheme.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class PhotoFragment extends BaseFragment implements PhotoContract.View {

    @Bind(R.id.image)
    ViewPager mPager;

    @Bind(R.id.main_ll)
    LinearLayout llMain;

    private ArrayList<String> mImage;
    private int[] imgsId = new int[]{R.drawable.default_goods};
    private int now;
    private PhotoContract.Presenter mPresenter;
    private static long lastClickTime,nowClickTime;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_frg, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        try {
            lastClickTime = System.currentTimeMillis();
            Bundle data = getArguments();
            if (data.getStringArrayList(Constants.JUMP_STRING) != null) {
                mImage = data.getStringArrayList(Constants.JUMP_STRING);
                now = data.getInt(Constants.JUMP_STRING1);
            }
            mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
            mPager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return mImage.size();
                }

                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position) {
                    PhotoView view = new PhotoView(getContext());
                    view.enable();
                    view.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                view.setImageResource(imgsId[position]);
                    Bitmap bitmap = BitmapFactory.decodeFile(mImage.get(position));
                    view.setImageBitmap(bitmap);
                    ImageLoader.getInstance().displayImage(mImage.get(position), view, ImageLoadOptions.getPhotoOptions());
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nowClickTime = System.currentTimeMillis();
                            if(nowClickTime - lastClickTime < 2000){
                                getActivity().finish();
                            }
                            lastClickTime = nowClickTime;
                        }
                    });
                    container.addView(view);

                    return view;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

      //  ImageLoader.getInstance().displayImage(mImage, imageView, ImageLoadOptions.getPhotoOptions());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPager.setCurrentItem(now);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public PhotoFragment() {
        new PhotoPresenter(this);
    }

    public static PhotoFragment newInstance() {
        return new PhotoFragment();
    }


    @Override
    public void setPresenter(@NonNull PhotoContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setArticle() {

    }
}
