package com.clown.wyxc.x_utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.clown.wyxc.x_utils.bean.RouterXml;
import com.clown.wyxc.x_utils.constants.Const;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 路由器
 * Created by tt17 on 2016/10/24.
 */

public class Router {

    private static Map<String, String> _routeTable = null;
    private static Boolean _InitDone = false;
    private static String _WebViewPackageName = null;

    /**
     * 构造器
     */
    public Router() {
    }

    /**
     * 初始化路由器状态
     *
     * @return
     */
    public static Boolean InitDone() {
        return Router._InitDone;
    }

    /**
     * 初始化
     */
    public static void Init() {
        _InitDone = true;
    }

    /**
     * 设置webView
     *
     * @param webViewPackage
     */
    public static void setWebViewPackage(String webViewPackage) {
        _WebViewPackageName = webViewPackage;
    }

    /**
     * 实例化路由容器对象
     */
    private static void readRoute() {
        if (Router._routeTable == null) {
            Router._routeTable = new HashMap<String, String>();
        }
    }

    /**
     * 注册原生画面
     *
     * @param path
     * @param actionName
     */
    public static void AddRoute(String path, String actionName) {
        readRoute();

        String key = path.toLowerCase();
        _routeTable.put(key, actionName);

    }

    /**
     * 匹配当前url的原生 Activity
     *
     * @param site
     * @param path
     * @return
     */
    public static String GetRoute(Activity fromAct, String site, String path) {
        try {
            readRoute();

            String key = site + path.toLowerCase();
            String result = _routeTable.get(key);
            if (result != null && result.startsWith(".")) {
                result = fromAct.getPackageName() + result;
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 尝试启动原生界面
     *
     * @param fromAct
     * @param urlStr
     * @return
     */
    public static boolean TryRunNative(Activity fromAct, String urlStr) {
        try {
            Uri uri = Uri.parse(urlStr);
            URL url = null;
            url = new URL(urlStr);

            String act = null;

            act = Router.GetRoute(fromAct, url.getHost(), url.getPath());

            if (act != null) {
                Intent intent = new Intent();
                intent.setClassName(fromAct, act);

                Set<String> arr = uri.getQueryParameterNames();

                Iterator<String> it = arr.iterator();

                Bundle bundle = new Bundle();

                while (it.hasNext()) {
                    String str = it.next();
                    bundle.putString(str, uri.getQueryParameter(str));
                }

                intent.putExtras(bundle);

                fromAct.startActivity(intent);
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void startActivty(Activity fromAct, String urlStr, boolean isClose) {
        try {
            if (urlStr == null || "".equals(urlStr)) {
                return;
            }
            Uri uri = Uri.parse(urlStr);
            URL url = null;

            String tempUrl = "http://"+urlStr.split("://")[1];
            url = new URL(tempUrl);

            String act = null;

            act = Router.GetRoute(fromAct, url.getHost(), url.getPath());

            if (act != null) {
                Intent intent = new Intent();
                intent.setClassName(fromAct, act);

                Set<String> arr = uri.getQueryParameterNames();

                Iterator<String> it = arr.iterator();

//                Bundle bundle = new Bundle();

                while (it.hasNext()) {
                    String str = it.next();
//                    bundle.putString(str, uri.getQueryParameter(str));
                    intent.putExtra(str,uri.getQueryParameter(str));
                }

//                intent.putExtras(bundle);

                fromAct.startActivity(intent);

                if (isClose) {
                    fromAct.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果是httpUrl 则调用WebView 并跳转
     *
     * @param fromAct
     * @param url
     * @param close:关闭0:不关闭1
     */
    public static void RouterToUrl(Activity fromAct, String url, int close) {
        if (TryRunNative(fromAct, url) == false) {
            Intent intent = new Intent();
            intent.setClassName(fromAct, _WebViewPackageName);

            Bundle bundle = new Bundle();
            bundle.putString(Const.PARAMETER_URL, url);
            intent.putExtras(bundle);

            fromAct.startActivity(intent);
            if (close == 0) {
                fromAct.finish();
            }
        }
    }

    public static void loadRouterXml(InputStream is) {
        //通过assertmanager的open方法获取到beauties.xml文件的输入流
        try {
            List<RouterXml> routerArray = RouterParser.parse(is);
            for (RouterXml router : routerArray) {
                Router.AddRoute(router.getHost() + router.getPath(), router.getActivity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
