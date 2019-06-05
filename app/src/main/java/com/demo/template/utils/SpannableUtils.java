package com.demo.template.utils;

import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.android.library.bridge.util.UIUtils;
import com.demo.template.R;

/**
 * @author y
 * @create 2018/12/5
 */
public class SpannableUtils {

    public static SpannableStringBuilder getAnswerV2Percentage(String percentage, String suffix) {
        SpannableStringBuilder mText = new SpannableStringBuilder(percentage + suffix);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(ContextCompat.getColor(UIUtils.getContext(), R.color.colorPrimary));
        mText.setSpan(foregroundColorSpan, percentage.length(), mText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mText;
    }

    public static SpannableStringBuilder getAnswerPortPercentage(String percentage, String suffix) {
        SpannableStringBuilder mText = new SpannableStringBuilder(percentage + "\n" + suffix);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(UIUtils.getColor(R.color.colorAnswerSpannable));
        mText.setSpan(foregroundColorSpan, 0, mText.length() - suffix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mText;
    }

    public static SpannableStringBuilder getAnswerPortSchedule(String schedule, String suffix) {
        SpannableStringBuilder mText = new SpannableStringBuilder(schedule + "\n" + suffix);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(UIUtils.getColor(R.color.colorAnswerSpannable));
        mText.setSpan(foregroundColorSpan, 0, mText.length() - suffix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mText;
    }

    public static SpannableStringBuilder getAnswerLandPercentage(String percentage, String suffix) {
        SpannableStringBuilder mText = new SpannableStringBuilder(suffix + "  " + percentage);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(UIUtils.getColor(R.color.colorAnswerSpannable));
        mText.setSpan(foregroundColorSpan, suffix.length(), mText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mText;
    }

    public static SpannableStringBuilder getAnswerLandSchedule(String schedule, String suffix) {
        SpannableStringBuilder mText = new SpannableStringBuilder(suffix + "  " + schedule);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(UIUtils.getColor(R.color.colorAnswerSpannable));
        mText.setSpan(foregroundColorSpan, suffix.length(), mText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return mText;
    }

}
