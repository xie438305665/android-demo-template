package com.android.library.widget.detector;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

public class KeyboardStatusDetector implements ViewTreeObserver.OnGlobalLayoutListener {

    private KeyboardListener keyboardListener;
    private boolean keyboardVisible = false;

    public KeyboardStatusDetector(@NonNull KeyboardListener keyboardListener) {
        this.keyboardListener = keyboardListener;
    }

    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        //获取当前界面可视部分
        keyboardListener.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        //获取屏幕的高度
        int screenHeight = keyboardListener.getActivity().getWindow().getDecorView().getRootView().getHeight();
        //此处就是用来获取键盘的高度的， 在键盘没有弹出的时候 此高度为0 键盘弹出的时候为一个正数
        int heightDifference = screenHeight - r.bottom;
        if (heightDifference > getBottomKeyboardHeight()) {//有些手机用的是底部虚拟键,所以要大于虚拟键的高度
            if (!keyboardVisible) {
                keyboardVisible = true;
                keyboardListener.onVisibilityChanged(true);
            }
        } else {
            if (keyboardVisible) {
                keyboardVisible = false;
                keyboardListener.onVisibilityChanged(false);
            }
        }
    }

    /**
     * @return 底部的虚拟栏的高度
     */
    public int getBottomKeyboardHeight() {
        int screenHeight = getAccurateScreenDpi()[1];
        DisplayMetrics dm = new DisplayMetrics();
        keyboardListener.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);//去除底部虚拟栏之后的metric
        int heightDifference = screenHeight - dm.heightPixels;
        return heightDifference;
    }

    /**
     * 获取实际的屏幕尺寸,所有的连同底部虚拟栏
     */
    public int[] getAccurateScreenDpi() {
        int[] screenWH = new int[2];
        Display display = keyboardListener.getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            Class<?> c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            screenWH[0] = dm.widthPixels;
            screenWH[1] = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenWH;
    }

    public interface KeyboardListener {
        Activity getActivity();

        void onVisibilityChanged(boolean flag);
    }
}
