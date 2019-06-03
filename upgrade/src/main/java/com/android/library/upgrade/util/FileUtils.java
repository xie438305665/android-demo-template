package com.android.library.upgrade.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @author xcl
 */

public class FileUtils {

    private FileUtils() {
    }

    /**
     * 获取文件下载路径
     *
     * @param fileName 目录名称
     * @return 路径
     */
    public static File getDiskFileDir(Context context, String fileName) {
        String path;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalFilesDir = context.getExternalFilesDir(fileName);
            path = externalFilesDir.getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path);
    }
}
