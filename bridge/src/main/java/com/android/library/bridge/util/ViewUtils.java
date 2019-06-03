package com.android.library.bridge.util;

import android.view.View;

/**
 * @author xcl
 */
public class ViewUtils {

    public static boolean gone(View view) {
        return view != null && view.getVisibility() == View.GONE;
    }

    public static boolean visible(View view) {
        return view != null && view.getVisibility() == View.VISIBLE;
    }

    public static boolean inVisible(View view) {
        return view != null && view.getVisibility() == View.INVISIBLE;
    }

    public static void goneView(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public static void visibleView(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void invisibleView(View... views) {
        for (View view : views) {
            if (view != null && view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }
}
