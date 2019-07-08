package com.android.library.upgrade.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import android.text.TextUtils;

import com.android.library.upgrade.VersionUploadEntity;

import java.io.File;

/**
 * @author xcl
 * 工具类集合
 */

public class UpgradeUtils {


    private static final String DATA_TYPE = "application/vnd.android.package-archive";

    private UpgradeUtils() {
    }

    /**
     * 是否存在新版本
     *
     * @param context
     * @param versionUploadEntity
     * @return
     */
    public static boolean checkNewVersion(Context context, VersionUploadEntity versionUploadEntity) {
        if (versionUploadEntity == null || versionUploadEntity.getObject() == null) {
            return false;
        }
        String version = UpgradeUtils.getVersion(context);
        String versionNumber = versionUploadEntity.getObject().getVersionNumber();
        int newVersion = new Version(version).compareTo(new Version(versionNumber));
        return !TextUtils.isEmpty(version) && !TextUtils.isEmpty(versionNumber) && newVersion < 0;
    }

    public static boolean checkNewVersion(Context context, String lastVersion) {
        String version = UpgradeUtils.getVersion(context);
        int newVersion = new Version(version).compareTo(new Version(lastVersion));
        return !TextUtils.isEmpty(version) && !TextUtils.isEmpty(lastVersion) && newVersion < 0;
    }


    /**
     * 安装apk
     *
     * @param activity 上下文
     * @param filePath 下载路径
     */
    public static void installApp(Activity activity, String packageName, String filePath) {
        if (filePath == null) {
            return;
        }
        if (filePath.endsWith(".apk")) {
            File _file = new File(filePath);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri apkUri = FileProvider.getUriForFile(activity, packageName + ".provider", _file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, DATA_TYPE);
            } else {
                intent.setDataAndType(Uri.fromFile(_file), DATA_TYPE);
            }
            activity.startActivity(intent);
        }
    }


    /**
     * 获取versionName
     *
     * @return versionName
     */
    public static String getVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
