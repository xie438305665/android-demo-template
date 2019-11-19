package com.android.library.bridge.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;


@TargetApi(Build.VERSION_CODES.KITKAT)
public class StatusBarUtil {

    public static final String LIGHT_CONTENT = "light-content";
    public static final String DARK_CONTENT = "dark-content";
    private static final String TAG = "StatusBarUtils";

    public static void darkStyle(@NonNull Activity activity, @ColorInt int darkColor) {
        setStyle(activity, DARK_CONTENT, darkColor, -1);
    }

    public static void lightStyle(@NonNull Activity activity, @ColorInt int lightColor) {
        setStyle(activity, LIGHT_CONTENT, -1, lightColor);
    }

    public static void setStyle(@NonNull Activity activity, final String style, @ColorInt int darkColor, @ColorInt int lightColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(style.equals(DARK_CONTENT) ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : 0);
            if (style.equals(DARK_CONTENT)) {
                setColor(activity, darkColor);
                if (isMiUI()) {
                    miUIStatusBar(activity, true);
                }
            } else {
                setColor(activity, lightColor);
            }
        }

    }

    public static void setColor(@NonNull Activity activity, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(id);
        }
    }


    public static boolean isMiUI() {
        return Utils.isMiUI();
    }

    public static void miUIStatusBar(Activity activity, boolean dark) {
        try {
            Window window = activity.getWindow();
            int darkModeFlag;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method method = window.getClass().getMethod("setExtraFlags", int.class, int.class);
            if (dark) {
                method.invoke(window, darkModeFlag, darkModeFlag);
            } else {
                method.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }


    private static class Utils {

        private static final String SYS_EMUI = "sys_emui";
        private static final String SYS_MIUI = "sys_miui";
        private static final String SYS_FLYME = "sys_flyme";
        private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
        private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
        private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
        private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
        private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
        private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";

        static boolean isLollipop_Mr1() {
            return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1;
        }

        static boolean isM() {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        }

        static boolean isMiUI() {
            return TextUtils.equals(getSystem(), SYS_MIUI);
        }

        static String getSystem() {
            String SYS = null;
            try {
                Properties properties = new Properties();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
                    if (properties.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                            || properties.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                            || properties.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                        SYS = SYS_MIUI;
                    } else if (properties.getProperty(KEY_EMUI_API_LEVEL, null) != null
                            || properties.getProperty(KEY_EMUI_VERSION, null) != null
                            || properties.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                        SYS = SYS_EMUI;
                    } else if (getSystemProperty().toLowerCase().contains("flyme")) {
                        SYS = SYS_FLYME;
                    }
                }
            } catch (Exception e) {
                Log.i("StatusBar", e.getMessage());
                e.printStackTrace();
            }
            return SYS;
        }

        static String getSystemProperty() {
            try {
                @SuppressLint("PrivateApi") Class<?> clz = Class.forName("android.os.SystemProperties");
                Method get = clz.getMethod("get", String.class, String.class);
                return (String) get.invoke(clz, "ro.build.display.id", "");
            } catch (Exception ignored) {
            }
            return "";
        }
    }

}
