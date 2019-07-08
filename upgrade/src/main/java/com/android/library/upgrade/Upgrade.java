package com.android.library.upgrade;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.library.upgrade.callback.FindUpgradeCallback;
import com.android.library.upgrade.callback.UpgradeCallback;
import com.android.library.upgrade.net.UpDateServer;
import com.android.library.upgrade.util.FileUtils;
import com.android.library.upgrade.util.UpgradeUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;

import java.io.File;

import io.reactivex.network.RxNetWork;
import io.reactivex.network.RxNetWorkListener;
import okhttp3.FormBody;


public class Upgrade implements RxNetWorkListener<VersionUploadEntity> {

    /***  进来检测版本,不给任何提示 ***/
    public static final int TYPE_FIRST = 0;
    /***  点击检测,给dialog提示 ***/
    public static final int TYPE_CLICK = 1;
    /***  查找是否存在新版本,适用于小红点之类的提示 ***/
    public static final int TYPE_FIND = 2;

    private Activity context;
    private UpgradeCallback upgradeCallback;
    private FindUpgradeCallback findUpgradeCallback;
    private String apkName;
    private String softwareId;
    private String packageName;
    private int type;
    private int icon;
    private DownloadProgressDialog downloadProgressDialog;

    public static Upgrade getInstance(Activity context) {
        return new Upgrade(context);
    }

    private Upgrade(Activity context) {
        this.context = context;
        downloadProgressDialog = DownloadProgressDialog.create(context);
    }

    public Upgrade setUpgradeCallback(UpgradeCallback upgradeCallback) {
        this.upgradeCallback = upgradeCallback;
        return this;
    }

    public Upgrade setFindUpgradeCallback(FindUpgradeCallback findUpgradeCallback) {
        this.findUpgradeCallback = findUpgradeCallback;
        return this;
    }

    public Upgrade setApkName(String apkName) {
        this.apkName = apkName;
        return this;
    }

    public Upgrade setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
        return this;
    }

    public Upgrade setType(int type) {
        this.type = type;
        return this;
    }

    public Upgrade setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Upgrade setIcon(int icon) {
        this.icon = icon;
        return this;
    }

    public Upgrade start() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add(UpgradeConst.VERSION_NUM_KEY, UpgradeUtils.getVersion(context));
        builder.add(UpgradeConst.SOFTWARE_ID_KEY, softwareId);
        RxNetWork.getInstance().cancel(UpgradeConst.SOFTWARE_ID_KEY);
        RxNetWork.getInstance().getApi(UpgradeConst.SOFTWARE_ID_KEY, RxNetWork.observable(UpDateServer.class).upload(builder.build()), this);
        return this;
    }

    @Override
    public void onNetWorkSuccess(final VersionUploadEntity data) {
        if (upgradeCallback != null) {
            upgradeCallback.onUpgradeSuccess(data);
        }
        if (!UpgradeUtils.checkNewVersion(context, data)) {
            noVersionTips();
            return;
        }
        if (type == TYPE_FIND) {
            if (findUpgradeCallback != null) {
                findUpgradeCallback.onUpgradeFindNewVersion(data);
            }
            return;
        }
        if (TextUtils.equals(data.getObject().getForceUpdate(), UpgradeConst.VERSION_FLAG)) {
            if (checkContextFinishing()) {
                return;
            }
            new AlertDialog.Builder(context)
                    .setTitle(R.string.upgrade_title)
                    .setMessage(R.string.upgrade_tips_message)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok, (dialog, which) -> downloadStart(data))
                    .show();
            return;
        }
        downloadStart(data);
    }

    @Override
    public void onNetWorkStart() {
        if (upgradeCallback != null) {
            upgradeCallback.onUpgradeStart();
        }
    }

    @Override
    public void onNetWorkError(Throwable e) {
        if (upgradeCallback != null) {
            upgradeCallback.onUpgradeError();
        }
        switch (type) {
            case TYPE_FIRST:
            case TYPE_FIND:
                break;
            case TYPE_CLICK:
                Toast.makeText(context, context.getString(R.string.upgrade_net_error), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNetWorkComplete() {

    }

    private void noVersionTips() {
        switch (type) {
            case TYPE_FIRST:
                break;
            case TYPE_FIND:
                if (findUpgradeCallback != null) {
                    findUpgradeCallback.onUpgradeFindNoVersion();
                }
                break;
            case TYPE_CLICK:
                if (checkContextFinishing()) {
                    return;
                }
                new AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.upgrade_no_tips_message))
                        .setPositiveButton(android.R.string.ok, null)
                        .show();
                break;
        }
    }

    public void downloadStart(VersionUploadEntity versionUploadEntity) {
        if (checkContextFinishing()) {
            return;
        }
        downloadProgressDialog = DownloadProgressDialog.create(context)
                .setTitle(context.getString(R.string.upgrade_title))
                .setMessage(versionUploadEntity.getObject().getChangeLog())
                .setIcon(icon)
                .setDownloadUrl(versionUploadEntity.getObject().getFileUrl())
                .setDownloadPath(String.format(FileUtils.getDiskFileDir(context, UpgradeConst.UPGRADE_FILE_NAME) + "/%s_%s.apk", apkName, versionUploadEntity.getObject().getVersionNumber()))
                .setTag("")
                .setDownloadCallBack(new DownloadProgressDialog.SimpleCall() {
                    @Override
                    public void completed(BaseDownloadTask task) {
                        if (upgradeCallback != null) {
                            upgradeCallback.onDownloadSuccess(versionUploadEntity);
                        }
                        UpgradeUtils.installApp(context, packageName, task.getPath());
                        if (!TextUtils.equals(versionUploadEntity.getObject().getForceUpdate(), UpgradeConst.VERSION_FLAG)) {
                            forceUpdate(versionUploadEntity.getObject().getVersionNumber());
                        }
                    }
                }).start();
    }

    private void forceUpdate(String lastVersion) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage("请更新到最新版本")
                .setPositiveButton(android.R.string.ok, null)
                .show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            File apkFile = checkoutApk(apkName, lastVersion);
            if (apkFile.exists()) {
                UpgradeUtils.installApp(context, packageName, apkFile.getPath());
                return;
            }
            start();
        });
    }

    private File checkoutApk(String apkName, String lastVersion) {
        return new File(String.format(FileUtils.getDiskFileDir(context, UpgradeConst.UPGRADE_FILE_NAME) + "/%s_%s.apk", apkName, lastVersion));
    }

    private boolean checkContextFinishing() {
        return context == null || context.isFinishing();
    }

    public void onDestroy() {
        RxNetWork.getInstance().cancel(UpgradeConst.SOFTWARE_ID_KEY);
        if (downloadProgressDialog != null)
            downloadProgressDialog.dismiss();
    }

}
