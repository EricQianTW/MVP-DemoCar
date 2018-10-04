package com.clown.wyxc.zxing;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.util.Hashtable;

public class CaptureTool {
	private static Bitmap scanBitmap;
	
	/**
	 * 扫描二维码图片的方法
	 * @param path
	 * @return
	 */
	public static Result scanningImage(String path) {
        if (TextUtils.isEmpty(path)) {  
            return null;  
        }  
        // DecodeHintType 和EncodeHintType  
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 设置二维码内容的编码
        BitmapFactory.Options options = new BitmapFactory.Options();  
        options.inJustDecodeBounds = true; // 先获取原大小  
        scanBitmap = BitmapFactory.decodeFile(path, options);  
        options.inJustDecodeBounds = false; // 获取新的大小  
  
        int sampleSize = (int) (options.outHeight / (float) 200);  
  
        if (sampleSize <= 0)  
            sampleSize = 1;  
        options.inSampleSize = sampleSize;  
        scanBitmap = BitmapFactory.decodeFile(path, options);  
  
        RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);  
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        try {  
            return reader.decode(bitmap1, hints);  
        } catch (NotFoundException e) {
            e.printStackTrace();  
        } catch (ChecksumException e) {
            e.printStackTrace();  
        } catch (FormatException e) {
            e.printStackTrace();  
        }  
  
        return null;  
  
    }

    public static Bitmap generateCode(String content,BarcodeFormat format) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            // MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix matrix = writer.encode(content, format, 500, 500);
            return bitMatrix2Bitmap(matrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
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

    public static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bitmap;
    }

    public static String getPhotoPath(Uri uri,Activity activity) {
        String[] proj = { MediaStore.Images.Media.DATA };
        // 好像是android多媒体数据库的封装接口，具体的看Android文档
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(uri, proj, null, null, null);
        // 按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        // 最后根据索引值获取图片路径
        String path = cursor.getString(column_index);
        return path;
    }
}
