package com.clown.wyxc.x_share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.bean.MsgShareDetail;
import com.clown.wyxc.components.wxapi.Util;
import com.clown.wyxc.utils.BitmapUtil;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareActivity extends BaseAppCompatActivity {

    @Bind(R.id.weixin_haoyou)
    TextView weixinHaoyou;
    @Bind(R.id.weixin_pengyouquan)
    TextView weixinPengyouquan;
    @Bind(R.id.weixin_shoucang)
    TextView weixinShoucang;
    @Bind(R.id.share_bar)
    LinearLayout shareBar;

    private MsgShareDetail msgShareDetail;
    private IWXAPI api;
    private static final int THUMB_SIZE = 150;

    public static final String APP_ID = "wx66ff8851dd096110";

    public final static String INTENTNAME_TITLE = "title";
    public final static String INTENTNAME_CONTENT = "content";
    public final static String INTENTNAME_THUMBIMAGEURL = "thumbimageurl";
    public final static String INTENTNAME_CLICKURL = "clickurl";
    public final static String INTENTNAME_TYPE = "type";
    public final static String INTENTNAME_DETAILIMAGEURL = "detailimageurl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            initView();
            initAction();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initView() throws Exception {
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }

    protected void initAction() throws Exception {
        weixinHaoyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int scene = SendMessageToWX.Req.WXSceneSession;
                    shareMain(scene, msgShareDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e(TAG,e.toString());
                }
            }
        });

        weixinPengyouquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int scene = SendMessageToWX.Req.WXSceneTimeline;
                    shareMain(scene, msgShareDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e(TAG,e.toString());
                }
            }
        });

        weixinShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int scene = SendMessageToWX.Req.WXSceneFavorite;
                    shareMain(scene, msgShareDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.e(TAG,e.toString());
                }
            }
        });
    }

    protected void initData() throws Exception {
        msgShareDetail = new MsgShareDetail();

        String title = getIntent().getStringExtra(INTENTNAME_TITLE);
        String content = getIntent().getStringExtra(INTENTNAME_CONTENT);
        String thumbimageurl = getIntent().getStringExtra(INTENTNAME_THUMBIMAGEURL);
        String clickurl = getIntent().getStringExtra(INTENTNAME_CLICKURL);
        String type = getIntent().getStringExtra(INTENTNAME_TYPE);
        String detailimageurl = getIntent().getStringExtra(INTENTNAME_DETAILIMAGEURL);

        msgShareDetail.setContent(content);
        msgShareDetail.setTitle(title);
        msgShareDetail.setImage(thumbimageurl);
        msgShareDetail.setUrl(clickurl);
        if(type != null){
            msgShareDetail.setShareType(Integer.parseInt(type));
        }
        msgShareDetail.setDetailImgUrl(detailimageurl);

        api = WXAPIFactory.createWXAPI(getApplicationContext(), APP_ID);
        api.registerApp(APP_ID);
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
                if( display - Y > shareBar.getMeasuredHeight()){
                    finish();
                }
                break;
            /**balId>0,小球的移动状态*/
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void shareMain(int scene, MsgShareDetail detail) throws Exception {
        if (detail.getShareType() == 0) {
            shareText(detail, scene);
        } else if (detail.getShareType() == 1) {
            shareBitmap(detail, scene);
        } else if (detail.getShareType() == 2) {
            shareUrl(detail, scene);
        }
    }

    public void shareUrl(MsgShareDetail detail, int scene) throws Exception {
        WXWebpageObject webpageObject = new WXWebpageObject();
        webpageObject.webpageUrl = detail.getUrl();

        WXMediaMessage message = new WXMediaMessage(webpageObject);
        message.title = detail.getTitle();
        message.description = detail.getContent();


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        message.thumbData = BitmapUtil.bitmap2Bytes(bitmap);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;
//        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.scene = scene;

        boolean b = api.sendReq(req);
        Log.d("WX", "" + b);
    }

    public void shareText(MsgShareDetail detail, int scene) throws Exception {
        WXTextObject textObject = new WXTextObject();
        textObject.text = detail.getContent();

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = textObject;
        message.description = detail.getContent();

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = message;
        req.scene = scene;

        api.sendReq(req);
    }

    public void shareBitmap(MsgShareDetail detail, int scene) throws Exception {
        Bitmap bmp = BitmapUtil.getBitmapFromUri(Uri.parse(detail.getDetailImgUrl()), getApplicationContext());

        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = scene;

        api.sendReq(req);
    }

}
