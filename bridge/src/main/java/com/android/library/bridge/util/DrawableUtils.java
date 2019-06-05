package com.android.library.bridge.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * @author xcl
 */
public class DrawableUtils {
    public static void setCompoundDrawables(TextView textView, Drawable drawable) {
        if (drawable == null) {
            textView.setCompoundDrawables(null, null, null, null);
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }
}
