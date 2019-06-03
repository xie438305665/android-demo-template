package com.android.library.bridge.util;

import android.view.Gravity;

import com.hjq.toast.style.ToastBlackStyle;

/**
 * @author xcl
 * @create 2019/1/11
 */
public class ToastStyle extends ToastBlackStyle {
    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public int getYOffset() {
        return 200;
    }
}
