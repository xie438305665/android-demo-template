package com.android.library.bridge.util;

import java.util.regex.Pattern;

/**
 * @author xcl
 */
public class NumberUtils {

    public static int ERROR = -111111;

    public static int parseInt(String obj) {
        try {
            return Integer.parseInt(obj);
        } catch (Exception ignored) {
        }
        return ERROR;
    }

    public static float parseFloat(String obj) {
        try {
            return Float.parseFloat(obj);
        } catch (Exception ignored) {
        }
        return ERROR;
    }

    public static double parseDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception ignored) {
        }
        return 0;
    }

    public static boolean endWithZero(double obj) {
        String stringNum = String.valueOf(obj);
        return stringNum.contains(".") && !stringNum.endsWith(".0");
    }

    public static String parseDouble(double obj) {
        if (String.valueOf(obj).endsWith(".0")) {
            return String.valueOf((int) obj);
        }
        return String.valueOf(obj);
    }

    public static boolean isInteger(String value) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(value).matches();
    }
}
