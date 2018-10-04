package com.clown.wyxc.x_qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.ali.QrCodeWrite;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.clown.wyxc.R.id.iv_qr_code;
import static com.clown.wyxc.zxing.CaptureTool.bitMatrix2Bitmap;

/**
 * Created by eric_clown on 2017/7/2.
 */

public class QrCodeActivity extends BaseAppCompatActivity {

    @Bind(iv_qr_code)
    ImageView ivQrCode;

    public static String INTANTNAME_STRING = "qrcode";
    @Bind(R.id.title)
    TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_act);
        ButterKnife.bind(this);

        String string = getIntent().getStringExtra(INTANTNAME_STRING);
        title.setText("请扫描二维码:" + string);
        CreateTwoDCode(string);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();

        int X = (int) event.getX();
        int Y = (int) event.getY();

        int display = this.getWindowManager().getDefaultDisplay().getHeight();

        switch (eventaction) {
            /**当初始进来的时候 ，向下移动的状态*/
            case MotionEvent.ACTION_DOWN:
                finish();
                break;
            /**balId>0,小球的移动状态*/
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void CreateTwoDCode(String content) {
        Bitmap qrcode = generateQRCode(content);

        ivQrCode.setImageBitmap(qrcode);
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
}
