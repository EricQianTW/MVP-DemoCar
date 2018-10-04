package com.clown.wyxc.x_callphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.clown.baselibray.material.app.Dialog;
import com.clown.baselibray.material.app.DialogFragment;
import com.clown.baselibray.material.app.SimpleDialog;
import com.clown.baselibray.material.app.ThemeManager;
import com.clown.wyxc.R;
import com.clown.wyxc.base.BaseAppCompatActivity;

/**
 * Created by eric_clown on 2017/7/2.
 */

public class CallPhoneActivity extends BaseAppCompatActivity {

    public static String INTENTNAME_PHONENUM = "phonenum";
    private String phonenum = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        phonenum = getIntent().getStringExtra(INTENTNAME_PHONENUM);

        Dialog.Builder builder = null;
        boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
        builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog){
            @Override
            public void onPositiveActionClicked(DialogFragment fragment) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenum));
                if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
                finish();
                super.onPositiveActionClicked(fragment);
            }

            @Override
            public void onNegativeActionClicked(DialogFragment fragment) {
                finish();
                super.onNegativeActionClicked(fragment);
            }
        };

        builder.title(phonenum)
                .positiveAction("确认")
                .negativeAction("取消");

        DialogFragment fragment = DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(), null);


    }
}
