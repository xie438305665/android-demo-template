package com.android.library.net;

/**
 * @author xcl
 */
@SuppressWarnings("WeakerAccess")
public class NetUrl {
    private NetUrl() {
    }

    private static final String DEV_URL = "";
    private static final String RELEASE_URL = "";

    private static final String WEB_DEV_URL = "";
    private static final String WEB_RELEASE_URL = "";

    static final String SUCCESS_CODE = "00";
    static final String ERROR_CODE = "10000";
    static final String DATA_NULL = "格式错误";
    public static final String TOKEN_ERROR_QUIT = "身份验证失败,请重新登录";

    public static String getDefaultBaseUrl() {
        return getBaseUrl(BuildConfig.DEBUG);
    }

    public static String getBaseUrl(boolean hasDev) {
        return hasDev ? DEV_URL : RELEASE_URL;
    }

    public static String getDefaultWebBaseUrl() {
        return getWebBaseUrl(BuildConfig.DEBUG);
    }

    public static String getWebBaseUrl(boolean hasDev) {
        return hasDev ? WEB_DEV_URL : WEB_RELEASE_URL;
    }
}
