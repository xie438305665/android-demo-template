package com.android.library.bridge.util;

/**
 * @Author xcl
 * @CreateDate 2019/3/15
 */
public class PhoneUtils {
    private static final class PhoneHolder {
        private static final PhoneUtils PHONE_HOLDER = new PhoneUtils();
    }

    public static PhoneUtils getInstance() {
        return PhoneHolder.PHONE_HOLDER;
    }

    /**
     * 获取手机型号
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机品牌
     */
    public static String getBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机系统版本
     */
    public static String getVersion() {
        return android.os.Build.VERSION.RELEASE;
    }
}
