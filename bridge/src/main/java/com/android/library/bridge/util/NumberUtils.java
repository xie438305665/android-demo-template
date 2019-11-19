package com.android.library.bridge.util;

import android.text.TextUtils;

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

    public static boolean isInteger(CharSequence value) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(value).matches();
    }

    public static boolean isNumber(CharSequence str) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isInt || isDouble;
    }

    public static String formatNumber(double number) {
        if (number == 0) return "0";
        String fractionStr = String.valueOf(number);
        if (fractionStr.endsWith(".0")) {
            String[] str = fractionStr.split("\\.0");
            return str[0];
        }
        return fractionStr;
    }

    public static String formatNumber(String number) {
        if (TextUtils.isEmpty(number)) return "";
        if (number.endsWith(".0")) {
            String[] str = number.split("\\.0");
            return str[0];
        }
        return number;
    }
}
