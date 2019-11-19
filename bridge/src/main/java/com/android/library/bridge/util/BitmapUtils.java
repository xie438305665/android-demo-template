package com.android.library.bridge.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @author xcl
 */
public class BitmapUtils {

    /**
     * 获取图片的宽高
     *
     * @param path 文件路径
     * @return [0] 宽 [1]高
     */
    public static int[] getImageWidthHeight(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    /**
     * 图片转 Base64 带头部
     *
     * @param path 路径
     */
    public static String bitmapToBase64HeaderPng(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return String.valueOf(TextUtils.concat("data:image/jpeg;base64,", Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP)));
    }

    /**
     * 图片转 Base64
     *
     * @param path 路径
     */
    public static String bitmapToBase64(String path) {
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP);
    }

    /**
     * 图片转 Base64 带头部
     *
     * @param bitmap {@link Bitmap}
     */
    public static String bitmapToBase64HeaderPng(Bitmap bitmap) {
        if (bitmap == null) return "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return String.valueOf(TextUtils.concat("data:image/png;base64,", Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP)));
    }

    /**
     * 图片转 Base64 带头部
     *
     * @param bitmap {@link Bitmap}
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        if (bitmap == null) return "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.NO_WRAP);
    }
}
