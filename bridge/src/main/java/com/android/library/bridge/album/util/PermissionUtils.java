package com.android.library.bridge.album.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.library.bridge.album.AlbumConstant;

/**
 * 权限 工具类
 */

public class PermissionUtils {
    private PermissionUtils() {
    }

    public static boolean storage(Activity activity) {
        return permission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, AlbumConstant.WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
    }

    public static boolean camera(Activity activity) {
        return permission(activity, Manifest.permission.CAMERA, AlbumConstant.CAMERA_REQUEST_CODE);
    }

    private static boolean permission(Activity activity, String permissions, int code) {
        if (ContextCompat.checkSelfPermission(activity, permissions) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permissions}, code);
            return false;
        }
        return true;
    }
}
