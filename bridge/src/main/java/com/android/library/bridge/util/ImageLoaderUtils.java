package com.android.library.bridge.util;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.math.BigDecimal;

public class ImageLoaderUtils {

    private static RequestOptions requestOptions = new RequestOptions();


    public static void display(@NonNull ImageView imageView, int url) {
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    public static void display(@NonNull ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    public static void display(@NonNull ImageView imageView, Object url) {
        Glide.with(imageView.getContext()).load(url).apply(requestOptions).into(imageView);
    }

    public static void displayFile(@NonNull Context context, String path, Target<File> target) {
        Glide.with(context).downloadOnly().load(path).apply(new RequestOptions().dontAnimate().skipMemoryCache(true)).into(target);
    }

    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }

    public static void clearImageDiskCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(() -> Glide.get(context).clearDiskCache()).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDiskCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    private static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return String.valueOf(0);
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
