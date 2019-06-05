package com.demo.template.utils;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.android.library.bridge.util.UIUtils;

public class DrawableUtils {
    public static void setScoreItemIcon(TextView textView, int id) {
        Drawable drawable = UIUtils.getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setUserItemIcon(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setUserSignItemIcon(TextView textView, Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    public static void setNull(TextView textView) {
        textView.setCompoundDrawables(null, null, null, null);
    }
}
