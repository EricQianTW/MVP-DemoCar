package com.clown.wyxc.scheme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.x_utils.Router;
import com.orhanobut.logger.Logger;

public class SchemeActivity extends BaseAppCompatActivity {
	public String path;
	public String data1;
	public String data2;
	public String scheme;
	public Uri uri;
	public Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			intent = getIntent();
			scheme = intent.getDataString();

			Router.startActivty(SchemeActivity.this,scheme,true);

//			uri = intent.getData();
//
//			if (uri != null && Constants.SCHEME_CODE.equals(scheme)) {
//				path = uri.getPath();
//				if (!"".equals(path)) {
//					String[] arrayStrings = path.split("/");
//					data1 = arrayStrings[1];
//					if (arrayStrings.length == 3) {
//						data2 = arrayStrings[2];
//					}
//
//				}
//				if (SchemeUtil.CLASSIFY_PAGE.equals(data1)) {
////					if (SchemeUtil.RULES_PAGE_HTML.equals(data2)) {
////						intent = new Intent(getApplicationContext(), HtmlActivity.class);
////						intent.putExtra("url", uri.getQueryParameter("url"));
////						intent.putExtra("title", uri.getQueryParameter("title"));
////					} else if (SchemeUtil.RULES_PAGE_HOMEPAGE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MainActivity.class);
////						MainActivity.setIndex(0);
////					} else if (SchemeUtil.RULES_PAGE_SHOPPINGCAR.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MainActivity.class);
////						MainActivity.setIndex(2);
////					} else if (SchemeUtil.RULES_PAGE_USERCENTER.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MainActivity.class);
////						MainActivity.setIndex(3);
////					} else if (SchemeUtil.RULES_PAGE_GOODSDETAIL.equals(data2)) {
////						intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
////						intent.putExtra(GoodsDetailActivity.INTENTNAME_GOODSID, uri.getQueryParameter("goodsid"));
////						intent.putExtra(GoodsDetailActivity.INTENTNAME_GOODSSTOCKID, uri.getQueryParameter("goodsstockid"));
////					} else if (SchemeUtil.RULES_PAGE_PACKAGEDETAIL.equals(data2)) {
////						intent = new Intent(getApplicationContext(), SetMealActivity.class);
////						intent.putExtra(SetMealActivity.INTENTNAME_SETMEALID, uri.getQueryParameter("packageid"));
////					} else if (SchemeUtil.RULES_PAGE_SEARCHKEY.equals(data2)) {
////						intent = new Intent(getApplicationContext(), SearchActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_GOODSLIST.equals(data2)) {
////						intent = new Intent(getApplicationContext(), SearchActivity.class);
////						intent.putExtra(SearchActivity.INTENTNAME_TYPE1, uri.getQueryParameter("type1"));
////						intent.putExtra(SearchActivity.INTENTNAME_TYPE2, uri.getQueryParameter("type2"));
////						intent.putExtra(SearchActivity.INTENTNAME_KEYVALUE, uri.getQueryParameter("keyvalue"));
////					} else if (SchemeUtil.RULES_PAGE_PHONECALL.equals(data2)) {
////						String number = uri.getQueryParameter("phonenum");
////						intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
////					} else if (SchemeUtil.RULES_PAGE_SHARE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), ShareForSchemeActivity.class);
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_TITLE, uri.getQueryParameter("title"));
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_CONTENT, uri.getQueryParameter("content"));
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_THUMBIMAGEURL, uri.getQueryParameter("thumbimageurl"));
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_CLICKURL, uri.getQueryParameter("clickurl"));
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_TYPE, uri.getQueryParameter("type"));
////						intent.putExtra(ShareForSchemeActivity.INTENTNAME_DETAILIMAGEURL, uri.getQueryParameter("detailimageurl"));
////					} else if (SchemeUtil.RULES_PAGE_LOGIN.equals(data2)) {
////						intent = new Intent(getApplicationContext(), LoginActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_SETADDRESS.equals(data2)) {
////						intent = new Intent(getApplicationContext(), AddressNewActivity.class);
////						intent.putExtra(AddressNewActivity.INTENTNAME_NEWRECEIVERINFOID, uri.getQueryParameter("addressid"));
////					} else if (SchemeUtil.RULES_PAGE_MANAGERADDRESS.equals(data2)) {
////						intent = new Intent(getApplicationContext(), AddressManagerActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_LOSTPSW.equals(data2)) {
////						intent = new Intent(getApplicationContext(), PasswordFristActivity.class);
////						intent.putExtra(PasswordFristActivity.INTENTNAME_LOSTTYPE, uri.getQueryParameter("losttype"));
////					} else if (SchemeUtil.RULES_PAGE_MODIFYUSERINFO.equals(data2)) {
////						intent = new Intent(getApplicationContext(), PersonalActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_ORDERPAYSTEPONE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), FirmOrderActivity.class);
////						intent.putExtra(FirmOrderActivity.INTENTNAME_ORDERNO, uri.getQueryParameter("guid"));
////					} else if (SchemeUtil.RULES_PAGE_ORDERPAYSTEPTWO.equals(data2)) {
////						intent = new Intent(getApplicationContext(), PayOrderActivity.class);
////						intent.putExtra(PayOrderActivity.INTENTNAME_ORDERNO, uri.getQueryParameter("guid"));
////					} else if (SchemeUtil.RULES_PAGE_ORDERLIST.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MyOrderActivity.class);
////						intent.putExtra(MyOrderActivity.INTENT_ORDERSTATE,uri.getQueryParameter("orderstate"));
////					} else if (SchemeUtil.RULES_PAGE_ORDERDETAIL.equals(data2)) {
////						intent = new Intent(getApplicationContext(), OrderInfoActivity.class);
////						intent.putExtra(OrderInfoActivity.INTENTNAME_ORDERID, uri.getQueryParameter("orderid"));
////					} else if (SchemeUtil.RULES_PAGE_USERMAKERV.equals(data2)) {
////						intent = new Intent(getApplicationContext(), CommentActivity.class);
////						intent.putExtra(CommentActivity.INTENT_ORDERID, uri.getQueryParameter("orderid"));
////					} else if (SchemeUtil.RULES_PAGE_AFTERSALESLIST.equals(data2)) {
////						intent = new Intent(getApplicationContext(), AfterSaleListActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_APPLYAFTERSERVICE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), ApplyActivity.class);
////						if (uri.getQueryParameter("aftersaleno") != null && !"".equals(uri.getQueryParameter("aftersaleno"))) {
////							intent.putExtra(ApplyActivity.INTENT_AFTERSALENO, uri.getQueryParameter("aftersaleno"));
////						}
////						if (uri.getQueryParameter("orderid") != null && !"".equals(uri.getQueryParameter("orderid"))) {
////							intent.putExtra(ApplyActivity.INTENT_ORDERID, Integer.parseInt(uri.getQueryParameter("orderid")));
////						}
////						if (uri.getQueryParameter("orderitemid") != null && !"".equals(uri.getQueryParameter("orderitemid"))) {
////							intent.putExtra(ApplyActivity.INTENT_ORDERITEMID, Integer.parseInt(uri.getQueryParameter("orderitemid")));
////						}
////					} else if (SchemeUtil.RULES_PAGE_AFTERSALESDETAIL.equals(data2)) {
////						intent = new Intent(getApplicationContext(), ReturnOrderActivity.class);
////						intent.putExtra(AfterSaleListActivity.INTENT_AFTERSALENO, Integer.parseInt(uri.getQueryParameter("aftersaleno")));
////					} else if (SchemeUtil.RULES_PAGE_MODIFYUSERPSW.equals(data2)) {
////					} else if (SchemeUtil.RULES_PAGE_MYQRCODE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MineQrCodeActivity.class);
////					} else if (SchemeUtil.RULES_PAGE_MYMESSAGE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), MessageActivity.class);
////					} else if (SchemeUtil.RULES_WEIXIN_PAY.equals(data2)) {
////						intent = new Intent(getApplicationContext(), WxPayActivity.class);
////						intent.putExtra("out_trade_no", uri.getQueryParameter("out_trade_no"));
////						intent.putExtra("body", uri.getQueryParameter("body"));
////						intent.putExtra("total_fee", uri.getQueryParameter("total_fee"));
////						intent.putExtra("notifyurl_type", uri.getQueryParameter("notifyurl_type"));
////					} else if (SchemeUtil.RULES_ALIPAY_PAY.equals(data2)) {
////						intent = new Intent(getApplicationContext(), AliPayActivity.class);
////						intent.putExtra("out_trade_no", uri.getQueryParameter("out_trade_no"));
////						intent.putExtra("subject", uri.getQueryParameter("subject"));
////						intent.putExtra("body", uri.getQueryParameter("body"));
////						intent.putExtra("total_fee", uri.getQueryParameter("total_fee"));
////						intent.putExtra("notifyurl_type", uri.getQueryParameter("notifyurl_type"));
////					} else if (SchemeUtil.RULES_PAGE_VIDEOPLAYER.equals(data2)) {
////						intent = new Intent(getApplicationContext(), InternetVideo.class);
////						intent.putExtra("path", uri.getQueryParameter("videourl"));
////					}
////				} else if (SchemeUtil.CLASSIFY_WEIXIN.equals(data1)) {
////				} else if (SchemeUtil.CLASSIFY_ALIPAY.equals(data1)) {
////				} else if (SchemeUtil.PASSWORD_CHANGE.equals(data1)) {
////					if (SchemeUtil.PASSWORD_CHANGE.equals(data2)) {
////						intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
////						intent.putExtra("viewtype", uri.getQueryParameter("viewtype"));
////					}
////				} else if (SchemeUtil.PASSWORD_LOST.equals(data1)) {
////					if (SchemeUtil.PASSWORD_LOST.equals(data2)) {
////						intent = new Intent(getApplicationContext(), PasswordFristActivity.class);
////						intent.putExtra(PasswordFristActivity.INTENTNAME_LOSTTYPE, uri.getQueryParameter(PasswordFristActivity.INTENTNAME_LOSTTYPE));
////						intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY);
////					}
//				}
//			}

//			startActivity(intent);
		} catch (Exception e) {
			Logger.e(e, TAG);
			e.printStackTrace();
		}
		finish();
	}
}
