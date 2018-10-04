package com.clown.wyxc.x_comment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.components.PhotoPopupWindows;
import com.clown.wyxc.components.listadapter.BaseListAdapter;
import com.clown.wyxc.components.listadapter.ViewHolder;
import com.clown.wyxc.components.pacificadapter.HorizontalItemDecoration;
import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.utils.PhotoUtils;
import com.clown.wyxc.utils.SDCard.Constants;
import com.clown.wyxc.utils.SDCard.FileUtils;
import com.clown.wyxc.utils.T;
import com.clown.wyxc.x_bean.GetCommOrderItemInfoResult;
import com.clown.wyxc.x_bean.OrderItem;
import com.clown.wyxc.x_bean.OrderItemService;
import com.clown.wyxc.x_bean.x_parambean.AddEvaluateByOrderIdQuery;
import com.clown.wyxc.x_bean.x_parambean.GetCommOrderItemInfoQuery;
import com.clown.wyxc.x_bean.x_parambean.GoodsPingLun;
import com.clown.wyxc.x_bean.x_parambean.ServicePingLun;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.RecyclerAdapterHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.ali.Constants.REQUESTCODE_UPLOADAVATAR_LOCATION;
import static com.clown.wyxc.constant.Constants.REQUEST_TAKE_PHOTO;

/**
 * Created by CZP on 2016/7/11.
 */
public class CommentFragment extends BaseFragment implements CommentContract.View {
    @Bind(R.id.rl_main)
    RelativeLayout rlMain;
    @Bind(R.id.btn_comment)
    Button btnComment;
    @Bind(R.id.rv_comment)
    RecyclerView rvComment;
    @Bind(R.id.rv_commentservice)
    RecyclerView rvCommentservice;
    private CommentContract.Presenter mPresenter;

    private List<String> pathList = new ArrayList<String>();         //本地拍照的文件名list
    // private ArrayList<int[]> imageList = new ArrayList<int[]>();
    private String tempPath;                    //  SD卡下PIC/Temp/temp.jgp路径
    private PhotoPopupWindows mPhotoWindows;
    private RecyclerAdapter<OrderItem> adapter;
    private RecyclerAdapter<OrderItemService> adapterService;
    private final String tempPicPath = Environment.getExternalStorageDirectory().getPath()
            + "/" + Constants.FILE_ROOT_NAME
            + "/" + Constants.FILE_PIC_NAME + "/" + "Temp" + "/";
    public int count = 0;
    //    private List<MsgAfferentComm> msgAfferentCwommList = new ArrayList<>();
//    private List<CommentGroup> commentlist = new ArrayList<CommentGroup>();
    private int orderid;
    private int currentPage = 0;
    private int currentItem = 0;
    private List<OrderItem> mOrderItemArr = new ArrayList<>();
    private List<OrderItemService> mOrderItemServiceArr = new ArrayList<>();

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_frag, container, false);
        try {

            ButterKnife.bind(this, view);
            mPresenter = new CommentPresenter(this);
            initAdapter();
            initView();
            initAction();
            getMsgOrderId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ButterKnife.bind(this, view);
        return view;
    }

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    public void initView() throws Exception {

        rvComment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvComment.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvComment.setAdapter(adapter);

        rvCommentservice.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCommentservice.addItemDecoration(new HorizontalItemDecoration
                .Builder(getContext())
                .colorResId(R.color.gray_theme)
                .sizeResId(R.dimen.height_explore_divider_5)
                .build());
        rvCommentservice.setAdapter(adapterService);

    }

    public void initAction() {
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<GoodsPingLun> goodsPingLunArr = new ArrayList<GoodsPingLun>();
                    for (OrderItem orderItem : adapter.getAll()) {
                        GoodsPingLun temp = new GoodsPingLun(orderItem.getGoodsId(), orderItem.getImgGuidList(), orderItem.getPoint(), orderItem.getCommentContent());
                        goodsPingLunArr.add(temp);
                    }
                    List<ServicePingLun> servicePingLunArr = new ArrayList<ServicePingLun>();
                    for (OrderItemService orderItem : adapterService.getAll()) {
                        ServicePingLun temp = new ServicePingLun(orderItem.getId(), orderItem.getImgGuidList(), orderItem.getPoint(), orderItem.getCommentContent());
                        servicePingLunArr.add(temp);
                    }

                    AddEvaluateByOrderIdQuery addEvaluateByOrderIdQuery = new AddEvaluateByOrderIdQuery(user.getId(), orderid, goodsPingLunArr, servicePingLunArr);
                    mPresenter.addEvaluateByOrderId(GSONUtils.paramToJson(addEvaluateByOrderIdQuery));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getMsgOrderId() throws Exception {
        Bundle bundle = getArguments();
        orderid = bundle.getInt(CommentActivity.INTENT_ORDERID, 0);
        mPresenter.getCommOrderItemInfo(GSONUtils.paramToJson(new GetCommOrderItemInfoQuery(user.getId(), orderid)));
    }

    public void initAdapter() throws Exception {

        adapter = new RecyclerAdapter<OrderItem>(getActivity(), R.layout.comment_frg_item) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final OrderItem item) {
                if (item.getPic() != null) {
                    helper.setImageUrl(R.id.iv_pic, item.getPic());
                }
                helper.setText(R.id.tv_miaoshu,item.getGoodsName());
                EditText et_comment = (EditText) helper.getItemView().findViewById(R.id.et_comment);
                RatingBar ratingbar = (RatingBar) helper.getItemView().findViewById(R.id.starnum);
                et_comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        item.setCommentContent(s.toString());
                    }
                });

                ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        item.setPoint((int) rating);
                    }
                });

                helper.setOnClickListener(R.id.iv_takephoto, new View.OnClickListener() {      //拍照的监听
                    @Override
                    public void onClick(View v) {
                        currentItem = helper.getAdapterPosition();
                        currentPage = 0;
                        showDialog();
                    }
                });

                final BaseAdapter commentAdapter = new BaseListAdapter<String>(getActivity()
                        , item.getImgGuidList()) {

                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        try {
                            String images = list.get(position);
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(images, convertView, position);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.goodsdetail_adp_comment, null);
                    }

                    private void setData(String item, View convertView, final int position) {
                        ImageView commentpic = ViewHolder.get(convertView, R.id.item_commentpic);
                        ImageLoader.getInstance().displayImage("http://api.ixiuc.com/upload/app/" + item, commentpic);
                    }

                };

                helper.setAdapter(R.id.gv_photos, commentAdapter);
            }
        };

        adapterService = new RecyclerAdapter<OrderItemService>(getActivity(), R.layout.comment_frg_item) {
            @Override
            protected void convert(final RecyclerAdapterHelper helper, final OrderItemService item) {
                if (item.getPic() != null) {
                    helper.setImageUrl(R.id.iv_pic, item.getPic());
                }

                EditText et_comment = (EditText) helper.getItemView().findViewById(R.id.et_comment);
                RatingBar ratingbar = (RatingBar) helper.getItemView().findViewById(R.id.starnum);
                et_comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        item.setCommentContent(s.toString());
                    }
                });

                ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        item.setPoint((int) rating);
                    }
                });

                helper.setOnClickListener(R.id.iv_takephoto, new View.OnClickListener() {      //拍照的监听
                    @Override
                    public void onClick(View v) {
                        currentItem = helper.getAdapterPosition();
                        currentPage = 1;
                        showDialog();
                    }
                });

                final BaseAdapter commentAdapter = new BaseListAdapter<String>(getActivity()
                        , item.getImgGuidList()) {
                    @Override
                    public View bindView(int position, View convertView, ViewGroup parent) {
                        convertView = null;
                        try {
                            String images = list.get(position);
                            if (convertView == null) {
                                convertView = createViewByType();
                            }
                            setData(images, convertView, position);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return convertView;
                    }

                    private View createViewByType() {
                        return mInflater.inflate(R.layout.goodsdetail_adp_comment, null);
                    }

                    private void setData(String item, View convertView, final int position) {
                        ImageView commentpic = ViewHolder.get(convertView, R.id.item_commentpic);
                        ImageLoader.getInstance().displayImage("http://api.ixiuc.com/upload/app/" + item, commentpic);
                    }

                };

                helper.setAdapter(R.id.gv_photos, commentAdapter);
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setAddEvaluateByOrderIdResult(String result) {
        T.showShort(getContext(), "评价完成");
        getActivity().finish();
    }

    @Override
    public void setPostResult(List<String> result) {
        try {
            if (currentPage == 0) {
                if (result.size() > 0) {
                    List<OrderItem> arr = adapter.getAll();
                    List<String> arrStr = arr.get(currentItem).getImgGuidList();
                    if(arrStr == null){
                        arrStr = new ArrayList<>();
                    }

                    arrStr.add(result.get(0));
                    arr.get(currentItem).setImgGuidList(arrStr);

//                    adapter.clear();
//                    adapter.addAll(arr);
//                    mOrderItemArr.clear();
//                    mOrderItemArr.addAll(arr);
                    adapter.notifyDataSetChanged();
                }
            } else {
                if (result.size() > 0) {
                    List<OrderItemService> arr = adapterService.getAll();
                    List<String> arrStr = arr.get(currentItem).getImgGuidList();
                    if(arrStr == null){
                        arrStr = new ArrayList<>();
                    }
                    arrStr.add(result.get(0));
                    arr.get(currentItem).setImgGuidList(arrStr);

//                    mOrderItemServiceArr.clear();
//                    mOrderItemServiceArr.addAll(arr);
                    adapterService.notifyDataSetChanged();
//                    adapterService.replaceAll(arr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGetCommOrderItemInfoResult(GetCommOrderItemInfoResult result) {
//        mOrderItemArr.addAll(result.getOrderItemList());
//        mOrderItemServiceArr.addAll(result.getOrderItemServiceList());

        adapter.addAll(result.getOrderItemList());
        adapterService.addAll(result.getOrderItemServiceList());
//        adapter.addAll(mOrderItemArr);
//        adapterService.addAll(mOrderItemServiceArr);
    }

    public void showDialog() {
        try {
            List<String> strArray = new ArrayList<String>();
            strArray.add("本地图片");
            strArray.add("拍照");
            strArray.add("取消");
            mPhotoWindows = new PhotoPopupWindows(getActivity(), rlMain, strArray);
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
