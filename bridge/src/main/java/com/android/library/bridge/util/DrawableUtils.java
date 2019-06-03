package com.android.library.bridge.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * @author xcl
 */
public class DrawableUtils {
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
