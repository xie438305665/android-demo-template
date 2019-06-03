package com.android.library.upgrade.callback;

import com.android.library.upgrade.VersionUploadEntity;

/**
 * @author xcl
 */
public interface FindUpgradeCallback {
    /***  存在新版本,需要更新
     * @param data***/
    void onUpgradeFindNewVersion(VersionUploadEntity data);

    /***  不存在新版本,无需任何操作 ***/
    void onUpgradeFindNoVersion();
}
