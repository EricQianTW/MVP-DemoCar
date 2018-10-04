package com.clown.wyxc.x_html;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.clown.wyxc.MainActivity;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.utils.IntentUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JokerEric on 2016/6/21.
 */
public class HtmlActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.abl_toolbar)
    AppBarLayout ablToolbar;
    @Bind(R.id.progressbar_layout)
    LinearLayout mProgressbarLayout;
    @Bind(R.id.webview_player)
    WebView mWebView;
    @Bind(R.id.main_content)
    FrameLayout mContentView;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private boolean fromTakePhoto; //是否是从摄像界面返回的webview
    public String fileFullName;//照相后的照片的全整路径
    private ValueCallback<Uri> mUploadMessage;
    private final static int FILECHOOSER_RESULTCODE = 1;
    private MyWebChromeClient myWebChromeClient;
    private View mCustomView = null;
    private String url;
    private String title;
    private int type;

    public final static String PARAM_STRING_URL = "url";
    public final static String PARAM_STRING_TITLE = "title";
    public final static String PARAM_STRING_TYPE = "html_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_act);
        ButterKnife.bind(this);

        initViews();
        initWebView();

        url = getIntent().getStringExtra(PARAM_STRING_URL);
        title = getIntent().getStringExtra(PARAM_STRING_TITLE);
        type = getIntent().getIntExtra(PARAM_STRING_TYPE, 0);

        toolbar.setTitle(title);

        if (url == null || url.equals("")) {
            url = "#";
        }
        mWebView.loadUrl(url, null);

        if (type == 999) {
            IntentUtils.startActivity(HtmlActivity.this, MainActivity.class);
            finish();
        }

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @SuppressLint("JavascriptInterface")
    private void initViews() {
        final Handler mHandler = new Handler();
        //webview增加javascript接口，监听html页面中的js点击事件
        mWebView.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public String clickOnAndroid() {//将被js调用
                mHandler.post(new Runnable() {
                    public void run() {
                        fromTakePhoto = true;
                        //调用 启用摄像头的自定义方法
                        takePhoto("testimg" + Math.random() * 1000 + 1 + ".jpg");
                        System.out.println("========fileFullName: " + fileFullName);
                    }
                });
                return fileFullName;
            }
        }, "demo");

        setSupportActionBar(toolbar);
        initBack();
    }

    /*
     * 调用摄像头的方法
     */
    public void takePhoto(String filename) {
        System.out.println("----start to take photo2 ----");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_MEDIA_TITLE, "TakePhoto");

        //判断是否有SD卡
        String sdDir = null;
        boolean isSDcardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (isSDcardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            sdDir = Environment.getRootDirectory().getAbsolutePath();
        }
        //确定相片保存路径
        String targetDir = sdDir + "/" + "webview_camera";
        File file = new File(targetDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileFullName = targetDir + "/" + filename;
        System.out.println("----taking photo fileFullName: " + fileFullName);
        //初始化并调用摄像头
        intent.putExtra(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileFullName)));
        startActivityForResult(intent, 1);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     * 重写些方法，判断是否从摄像Activity返回的webview activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (fromTakePhoto && requestCode == 1 && resultCode == -1) {
            mWebView.loadUrl("javascript:wave2('" + fileFullName + "')");
        } else {
            mWebView.loadUrl("javascript:wave2('Please take your photo')");
        }
        fromTakePhoto = false;

        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data
                    .getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
    }

    @SuppressWarnings("deprecation")
    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        //settings.setPluginsEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setBuiltInZoomControls(false); // 显示放大缩小
        settings.setSupportZoom(true); // 可以缩放

        //webview支持js脚本
        settings.setJavaScriptEnabled(true);
        //启用数据库
        settings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //启用地理定位
        settings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        settings.setDomStorageEnabled(true);
        //配置权限
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });

        myWebChromeClient = new MyWebChromeClient();
        mWebView.setWebChromeClient(myWebChromeClient);
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        private CustomViewCallback mCustomViewCallback;
        private int mOriginalOrientation = 1;

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            onShowCustomView(view, mOriginalOrientation, callback);
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            if (getPhoneAndroidSDK() >= 14) {
                mCustomView = view;
                mCustomViewCallback = callback;
                mOriginalOrientation = getRequestedOrientation();
                mContentView.setVisibility(View.INVISIBLE);
                setRequestedOrientation(mOriginalOrientation);
            }

        }

        @Override
        public void onHideCustomView() {
            mContentView.setVisibility(View.VISIBLE);
            if (mCustomView == null) {
                return;
            }
            mCustomView.setVisibility(View.GONE);
            mCustomView = null;
            try {
                mCustomViewCallback.onCustomViewHidden();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            setRequestedOrientation(mOriginalOrientation);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {

            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            HtmlActivity.this.startActivityForResult(
                    Intent.createChooser(i, "File Chooser"),
                    FILECHOOSER_RESULTCODE);

        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback uploadMsg,
                                    String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            HtmlActivity.this.startActivityForResult(
                    Intent.createChooser(i, "File Browser"),
                    FILECHOOSER_RESULTCODE);
        }

        // For Android 4.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                    String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            HtmlActivity.this.startActivityForResult(
                    Intent.createChooser(i, "File Chooser"),
                    HtmlActivity.FILECHOOSER_RESULTCODE);

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            toolbar.setTitle(title);
        }
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.contains("a.app.qq.com")) {
                if (url.startsWith("hfwyxc:")) {
                    Intent i = new Intent();
                    i.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    i.setData(content_url);
                    startActivity(i);
                    return true;
                } else if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("ftp:") || url.startsWith("mailto:")) {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                } else {
                    try {
                        Intent intent = new Intent();
                        intent = Intent.getIntent(url);
                        startActivity(intent);
                    } catch (Exception e) {
                        mWebView.stopLoading();
                        e.printStackTrace();
                    }
                    return true;
                }

            } else {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
                return true;
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mProgressbarLayout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressbarLayout.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }

    }

    public static int getPhoneAndroidSDK() {
        int version = 0;
        try {
            version = Build.VERSION.SDK_INT;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;

    }


    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
