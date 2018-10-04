package com.clown.wyxc.scheme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.clown.wyxc.html.HtmlActivity;

public class SchemeUtil {

	public static void startSchemeIntent(Context context, String url) throws Exception{
		if(url != null && !"".equals(url)){
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri content_url = Uri.parse(url);
			intent.setData(content_url);
			context.startActivity(intent);
		}
	}

	public static void startSchemeIntentAndHtml(Context context,String url,String title){
		if(url != null && !"".equals(url)){

			Uri uri = Uri.parse(url);
			Intent intent = new Intent();
			if(Constants.SCHEME_CODE.equals(uri.getScheme())){
				intent.setAction("android.intent.action.VIEW");
				intent.setData(uri);
			}else if(Constants.HTTP_CODE.equals(uri.getScheme())){
				intent.setClass(context, HtmlActivity.class);
				intent.putExtra("title",  title);
				intent.putExtra("html_type", 0);
				intent.putExtra("url", url);
			}
			context.startActivity(intent);
		}
	}

    public static void startSchemeIntentNewTaskWithFinish(Context context, String url) throws Exception{
        if(url != null && !"".equals(url)){
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            intent.setData(content_url);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

	public static void startSchemeIntentWithFinish(Context context, String url) throws Exception{
		if(url != null && !"".equals(url)){
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri content_url = Uri.parse(url);
			intent.setData(content_url);
			context.startActivity(intent);
			((Activity)context).finish();
		}
	}
//
//	public static void startHtmlSchemeIntent(Context context, String url, String title){
//		if(url != null && !"".equals(url)){
//			Intent intent = new Intent();
//			intent.setClass(context, HtmlActivity.class);
//			intent.putExtra("title", title);
//			intent.putExtra("html_type", 0);
//			intent.putExtra("url", url);
//			context.startActivity(intent);
//		}
//	}
	
	/**
	 * 商品类别
	 */
	public static String CLASSIFY_PAGE = "page";
	public static String RULES_PAGE_HTML = "html";
	public static String RULES_PAGE_HOMEPAGE = "homepage";
	public static String RULES_PAGE_SHOPPINGCAR = "shoppingcar";
	public static String RULES_PAGE_USERCENTER = "usercenter";
	public static String RULES_PAGE_GOODSDETAIL = "goodsdetail";
	public static String RULES_PAGE_PACKAGEDETAIL = "packagedetail";
	public static String RULES_PAGE_SEARCHKEY = "searchkey";
	public static String RULES_PAGE_GOODSLIST = "goodslist";
	public static String RULES_PAGE_PHONECALL = "phonecall";
	public static String RULES_PAGE_SHARE = "share";
	public static String RULES_PAGE_LOGIN = "login";
	public static String RULES_PAGE_SETADDRESS = "setaddress";
	public static String RULES_PAGE_MANAGERADDRESS = "manageraddress";
	public static String RULES_PAGE_LOSTPSW = "lostpsw";
	public static String RULES_PAGE_MODIFYUSERINFO = "modifyuserinfo";
	public static String RULES_PAGE_ORDERPAYSTEPONE = "orderpaystepone";
	public static String RULES_PAGE_ORDERPAYSTEPTWO = "orderpaysteptwo";
	public static String RULES_PAGE_ORDERLIST = "orderlist";
	public static String RULES_PAGE_ORDERDETAIL = "orderdetail";

	public static String RULES_PAGE_USERMAKERV = "usermakerv";
	public static String RULES_PAGE_AFTERSALESLIST = "aftersaleslist";
	public static String RULES_PAGE_APPLYAFTERSERVICE = "applyafterservice";
	public static String RULES_PAGE_AFTERSALESDETAIL = "aftersalesdetail";
	public static String RULES_PAGE_MODIFYUSERPSW = "modifyuserpsw";
	public static String RULES_PAGE_MYQRCODE = "myqrcode";
	public static String RULES_PAGE_MYMESSAGE = "mymessage";
	public static String RULES_PAGE_VIDEOPLAYER = "videoplayer";

	/**
	 * 微信支付
	 */
	public static String CLASSIFY_WEIXIN = "wexinabout";
	public static String RULES_WEIXIN_PAY = "weixinpay";

    /**
     * 阿里支付
     */
    public static String CLASSIFY_ALIPAY = "alipay";
    public static String RULES_ALIPAY_PAY = "alipay";

	/**
	 * 密码修改
	 */
	public static String PASSWORD_CHANGE = "modifyuserpsw";
	public static String PASSWORD_LOST = "lostpsw";
}
