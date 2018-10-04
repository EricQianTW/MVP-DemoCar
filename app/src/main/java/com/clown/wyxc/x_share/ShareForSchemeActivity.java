package com.clown.wyxc.x_share;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;

import com.clown.wyxc.base.BaseAppCompatActivity;
import com.clown.wyxc.components.wxapi.ShareUtils;
import com.clown.wyxc.x_bean.GoodsShare;

public class ShareForSchemeActivity extends BaseAppCompatActivity {

	public final static String INTENTNAME_TITLE = "title";
	public final static String INTENTNAME_CONTENT = "content";
	public final static String INTENTNAME_THUMBIMAGEURL = "thumbimageurl";
	public final static String INTENTNAME_CLICKURL = "clickurl";
	public final static String INTENTNAME_TYPE = "type";
	public final static String INTENTNAME_DETAILIMAGEURL = "detailimageurl";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GoodsShare detail = new GoodsShare();
		detail.setTitle(getIntent().getStringExtra(INTENTNAME_TITLE));
		detail.setContents(getIntent().getStringExtra(INTENTNAME_CONTENT));
		detail.setSharePic(getIntent().getStringExtra(INTENTNAME_THUMBIMAGEURL));
		detail.setShareUrl(getIntent().getStringExtra(INTENTNAME_CLICKURL));
		detail.setShareType(Integer.parseInt(getIntent().getStringExtra(INTENTNAME_TYPE)==null?"0":getIntent().getStringExtra(INTENTNAME_TYPE)));
		detail.setDetailPic(getIntent().getStringExtra(INTENTNAME_DETAILIMAGEURL));

		ShareUtils share = new ShareUtils(ShareForSchemeActivity.this);
		share.showShareDialog(detail);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

}
