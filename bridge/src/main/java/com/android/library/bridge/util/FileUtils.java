package com.android.library.bridge.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

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

    /**
     * 删除文件,如果该文件存在的话
     *
     * @param path 路径
     */
    public static void delete(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            boolean delete = file.delete();
        }
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
            boolean delete = file.delete();
        } else if (file.exists()) {
            boolean delete = file.delete();
        }
    }
}
