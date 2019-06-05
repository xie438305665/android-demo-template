package com.android.library.bridge.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

/**
 * @author y
 * @create 2018/12/5
 */
public class SpannableUtils {
    public static SpannableStringBuilder setSpannableString(String content,
                                                            int color,
                                                            int start,
                                                            int end) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        stringBuilder.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return stringBuilder;
    }
}
