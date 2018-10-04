package com.clown.wyxc.x_feedback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.PhotoPopupWindows;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.components.pacificadapter.VerticalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.PhotoUtils;
import com.clown.wyxc.utils.SDCard.Constants;
import com.clown.wyxc.utils.SDCard.FileUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.x_parambean.FeedBackQuery;
import com.orhanobut.logger.Logger;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

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
public class FeedbackFragment extends BaseFragment implements FeedbackContract.View {

    @Bind(R.id.ll_main)
    LinearLayout llMain;
    @Bind(R.id.iv_take_photos)
    ImageView ivTakePhotos;
    @Bind(R.id.rv_icon)
    RecyclerView rvIcon;
    @Bind(R.id.tv_commit)
    TextView tvCommit;
    @Bind(R.id.et_comment)
    EditText etComment;

    private FeedbackContract.Presenter mPresenter;
    private RecyclerAdapter<String> adapter;
    private PhotoPopupWindows mPhotoWindows;
    private final String tempPicPath = Environment.getExternalStorageDirectory().getPath()
            + "/" + Constants.FILE_ROOT_NAME
            + "/" + Constants.FILE_PIC_NAME + "/" + "Temp" + "/";
    private String tempPath;
    private List<String> mArray = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feedback_frg, container, false);
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
            initAdapter();
            initView();
            initAction();
        } catch (Exception e) {
            e.printStackTrace();
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

    public FeedbackFragment() {
        new FeedbackPresenter(this);
    }

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    private void initAction() throws Exception {
        ivTakePhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(adapter.getItemCount());
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.postFeedBack(GSONUtils.paramToJson(new FeedBackQuery(mArray, null, user.getId(), etComment.getText().toString(),null)));
            }
        });
    }

    @Override
    public void setPresenter(@NonNull FeedbackContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setPostResult(List<String> result) {
        try {
            mArray.addAll(result);
            if (result.size() > 0) {
                adapter.replaceAll(mArray);
            }
        } catch (Exception e) {
            Logger.e(e, TAG);
            e.printStackTrace();
        }
    }

    @Override
    public void setPostFeedBackResult(int result) {
        T.showShort(getContext(), "提交成功");
        getActivity().finish();
    }

    private void initView() throws Exception {
        rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvIcon.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .showLastDivider(true)
                .build());

        rvIcon.addItemDecoration(new VerticalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .showLastDivider(true)
                .build());

        rvIcon.setAdapter(adapter);
    }

    private void initAdapter() throws Exception {
        adapter = new RecyclerAdapter<String>(getContext(), R.layout.feedback_adp) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final String info) {
                try {
                    try {
                        helper.setImageUrl(R.id.iv_pic, "http://api.ixiuc.com/upload/app/" + info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    helper.getItemView().setTag("hello world");
                } catch (Exception e) {
                    Logger.e(e, TAG);
                    e.printStackTrace();
                }
            }
        };
    }

    public void showDialog(int count) {
        try {
            List<String> strArray = new ArrayList<String>();
            strArray.add("本地图片");
            strArray.add("拍照");
            strArray.add("取消");
            if (count < 5) {
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
            } else {
                T.showShort(getActivity(), "照片数量不能超过5张");
            }
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