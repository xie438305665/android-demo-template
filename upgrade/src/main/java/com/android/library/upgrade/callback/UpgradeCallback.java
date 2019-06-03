package com.android.library.upgrade.callback;

import com.android.library.upgrade.VersionUploadEntity;

/**
 * @author xcl
 */
public interface UpgradeCallback {
    /***  开始检测更新 ***/
    void onUpgradeStart();

    /***  无论是否存在新版本,检测成功
     * @param data***/
    void onUpgradeSuccess(VersionUploadEntity data);

    /***  检测失败 ***/
    void onUpgradeError();

    /**
     * 下载成功
     *
     * @param versionUploadEntity
     */
    void onDownloadSuccess(VersionUploadEntity versionUploadEntity);
}
