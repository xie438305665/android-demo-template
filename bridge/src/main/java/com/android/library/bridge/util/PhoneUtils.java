package com.android.library.bridge.util;

import android.text.TextUtils;

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

    /**
     * 手机号校验
     *
     * @param mobiles
     * @return boolean
     */
    public static boolean isMobileNumber(String mobiles) {
        String telRegex = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }
}
