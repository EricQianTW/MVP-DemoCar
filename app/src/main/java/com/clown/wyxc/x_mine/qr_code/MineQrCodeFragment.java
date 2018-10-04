package com.clown.wyxc.x_mine.qr_code;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.ali.QrCodeWrite;
import com.clown.wyxc.base.BaseFragment;
import com.clown.wyxc.bean.MsgUserInfo;
import com.clown.wyxc.utils.S;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MineQrCodeFragment extends BaseFragment implements MineQrCodeContract.View{


    @Bind(R.id.main_ll)
    LinearLayout main_ll;
    @Bind(R.id.nead_shot)
    LinearLayout nead_shot;

    @Bind(R.id.iv_head)
    ImageView iv_head;
    @Bind(R.id.iv_qr_code)
    ImageView iv_qr_code;
    @Bind(R.id.save_to_share)
    ImageView save_to_share;
    @Bind(R.id.fl_iv_head)
    ImageView fl_iv_head;

    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_show)
    TextView tv_show;
    @Bind(R.id.tv_sacn_share)
    TextView tv_sacn_share;

    private MineQrCodeContract.Presenter mPresenter;
    private String phone,phone_close;
    private int now_ok=0;
    public static String tempPicPath = Environment.getExternalStorageDirectory().getPath()
            + "/" + com.clown.wyxc.utils.SDCard.Constants.FILE_ROOT_NAME
            + "/" + com.clown.wyxc.utils.SDCard.Constants.FILE_PIC_NAME + "/" + "Temp" + "/";
    private String mPicPath = com.clown.wyxc.utils.SDCard.Constants.FILE_ROOT_NAME
            + "/" + com.clown.wyxc.utils.SDCard.Constants.FILE_PIC_NAME + "/" + "Temp" + "/";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_qr_code, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        setRetainInstance(true);

        find();
//        mPresenter.getPhone(user.getVerify(),user.getUserId());
       return view;
    }


    private void find() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAction();
    }

    private void initAction() {
        tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(now_ok == 0){
                    now_ok = 1;
                    tv_phone.setText(phone);
                    tv_show.setText("隐藏");
                }else{
                    now_ok = 0;
                    tv_phone.setText(phone_close);
                    tv_show.setText("显示");
                }
            }
        });
        save_to_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd_HH-mm-ss", Locale.US);
                    String compressPath = tempPicPath + System.currentTimeMillis() + ".png";
                    getScreenHot(nead_shot,
                            compressPath);

                    String newname = sdf.toString() + ".png";

                    File imageFile = new File(compressPath, newname);

                    broadcast(compressPath);

                    ContentValues localContentValues = new ContentValues();
                    localContentValues.put("_data", imageFile.toString());
                    localContentValues.put("description", "save image ---");
                    localContentValues.put("mime_type", "image/jpeg");
                    ContentResolver localContentResolver = getActivity().getContentResolver();
                    Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    localContentResolver.insert(localUri, localContentValues);

                    S.showLong(main_ll, "已保存二维码图片在 " + mPicPath);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void broadcast(String path) throws Exception{
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        getActivity().sendBroadcast(intent);
    }
    private void getScreenHot(View v, String filePath) {
        try {
            Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(bitmap);
            v.draw(canvas);

            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (FileNotFoundException e) {
                throw new InvalidParameterException();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setReback(MsgUserInfo info) {
        phone = info.getAccountMobile();
        phone_close = phone.substring(0,3)+"****"+phone.substring(7, phone.length());
        tv_name.setText(info.getNickName());
        tv_phone.setText(phone_close);
        ImageLoader.getInstance().displayImage(info.getHeadImg(), iv_head);
        fl_iv_head.setImageResource(R.mipmap.ic_launcher);
        if(info.getQrCodeStr()!=null) {
            CreateTwoDCode(info.getQrCodeStr());
        }
    }

    public MineQrCodeFragment() {
        new MineQrcodePresenter(this);
    }

    public static MineQrCodeFragment newInstance() {
        return new MineQrCodeFragment();
    }

    @Override
    public void setPresenter(@NonNull MineQrCodeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void CreateTwoDCode(String content) {
        Bitmap qrcode = generateQRCode(content);

        iv_qr_code.setImageBitmap(qrcode);
    }

    private Bitmap generateQRCode(String content) {
        try {
            QrCodeWrite writer = new QrCodeWrite();
            // MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, 1000, 1000);
            return bitMatrix2Bitmap(matrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] rawData = new int[w * h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = Color.WHITE;
                if (matrix.get(i, j)) {
                    color = Color.BLACK;
                }
                rawData[i + (j * w)] = color;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
        bitmap.setPixels(rawData, 0, w, 0, 0, w, h);
        return bitmap;
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
}
