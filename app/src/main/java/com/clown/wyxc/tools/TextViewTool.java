package com.clown.wyxc.tools;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * Created by eric_clown on 2017/5/6.
 */

public class TextViewTool {

    /**
     *  为Textview中部分文字设置颜色
     * @param tv
     * @param color
     * @param start
     * @param end
     */
    public static void setTextViewPartColor(TextView tv, int color, int start, int end) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(tv.getText().toString());

        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(color);

        stringBuilder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(stringBuilder);
    }
}
