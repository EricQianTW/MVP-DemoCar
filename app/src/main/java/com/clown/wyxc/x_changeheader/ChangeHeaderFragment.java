package com.clown.wyxc.x_changeheader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.CircleImageView;
import com.clown.wyxc.components.PhotoPopupWindows;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.PhotoUtils;
import com.clown.wyxc.utils.SDCard.Constants;
import com.clown.wyxc.utils.SDCard.FileUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.Users;
import com.clown.wyxc.x_bean.UsersHeadPicQuery;
import com.clown.wyxc.x_bean.x_parambean.QueryId;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.ali.Constants.REQUESTCODE_UPLOADAVATAR_LOCATION;
import static com.clown.wyxc.constant.Constants.REQUEST_TAKE_PHOTO;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by eric_qiantw on 16/4/20.
 */
public class ChangeHeaderFragment extends BaseFragment implements ChangeHeaderContract.View {

    @Bind(R.id.userheader)
    CircleImageView userheader;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.ll_main)
    RelativeLayout llMain;
    @Bind(R.id.et_username)
    EditText etUsername;

    private ChangeHeaderContract.Presenter mPresenter;

    private PhotoPopupWindows mPhotoWindows;
    private final String tempPicPath = Environment.getExternalStorageDirectory().getPath()
            + "/" + Constants.FILE_ROOT_NAME
            + "/" + Constants.FILE_PIC_NAME + "/" + "Temp" + "/";
    private String tempPath;
    private String picId;
    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.changeheader_frg, container, false);
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

    private void initData() {
        mPresenter.getUsersById(GSONUtils.paramToJson(new QueryId(user.getId(),null)));
    }

    private void initView() throws Exception {
        type = getArguments().getInt(ChangeHeaderActivity.TYPE);
        if (type == 1) {
            etUsername.setVisibility(View.INVISIBLE);
        }else{
            userheader.setVisibility(View.INVISIBLE);
        }
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

    public ChangeHeaderFragment() {
        new ChangeHeaderPresenter(this);
    }

    public static ChangeHeaderFragment newInstance() {
        return new ChangeHeaderFragment();
    }

    private void initAction() throws Exception {
        userheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    mPresenter.updateHeadPic(GSONUtils.paramToJson(new UsersHeadPicQuery(user.getId(), picId, null)));
                }else{
                    mPresenter.updateHeadPic(GSONUtils.paramToJson(new UsersHeadPicQuery(user.getId(), null, etUsername.getText().toString())));
                }
            }
        });
    }

    @Override
    public void setPresenter(@NonNull ChangeHeaderContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setPostResult(List<String> result) {
        if (result.size() > 0) {
            picId = result.get(0);
            ImageLoader.getInstance().displayImage("http://api.ixiuc.com/upload/app/" + picId, userheader);
        }
    }

    @Override
    public void setUpdateHeadPicResult(int result) {
        T.showShort(getContext(),"更改设置成功");
        getActivity().finish();
    }

    @Override
    public void setGetUsersByIdResult(Users result) {
        ImageLoader.getInstance().displayImage(result.getHeadPic(), userheader);
        etUsername.setText(result.getName());
    }

    public void showDialog() {
        try {
            List<String> strArray = new ArrayList<String>();
            strArray.add("本地图片");
            strArray.add("拍照");
            strArray.add("取消");
            mPhotoWindows = new PhotoPopupWindows(getActivity(), llMain, strArray);
            mPhotoWindows.setOnPopItemClickListening(new PhotoPopupWindows.OnPopItemClickListening() {
                @Override
                public void PopItemClickListening(String string, int position) {

                    if (position == 0) {
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, REQUESTCODE_UPLOADAVATAR_LOCATION);
                    } else if (position == 1) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File file = new File(tempPicPath, "temp.JPG");
                        tempPath = file.getPath();
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                    } else {
                        mPhotoWindows.dismiss();
                    }

                    mPhotoWindows.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    if (resultCode == -1) {// 拍照
                        String path = PhotoUtils.compressPic(tempPath);            //压缩
                        mPresenter.post(path);
                    }
                    break;
                case REQUESTCODE_UPLOADAVATAR_LOCATION:
                    if (resultCode == -1 && null != data) {// 相册返回
                        String path = FileUtils.getSystemPhotoPath(data.getData(), getActivity());
                        mPresenter.post(path);
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}