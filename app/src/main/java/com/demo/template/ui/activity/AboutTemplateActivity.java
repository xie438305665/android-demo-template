package com.demo.template.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.library.bridge.BridgeConstant;
import com.android.library.bridge.R2;
import com.android.library.bridge.core.MVPActivity;
import com.android.library.bridge.core.MVPresenterImpl;
import com.android.library.bridge.util.ImageLoaderUtils;
import com.android.library.upgrade.Upgrade;
import com.android.library.upgrade.VersionUploadEntity;
import com.android.library.upgrade.callback.UpgradeCallback;
import com.demo.template.BuildConfig;
import com.demo.template.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xcl
 */
public class AboutTemplateActivity extends MVPActivity {

    @BindView(R.id.tv_version)
    AppCompatTextView mVersion;
    @BindView(R.id.tv_remove_disk_cache)
    AppCompatTextView mDiskCache;

    @BindString(R.string.about_version)
    String aboutVersion;
    @BindString(R.string.remove_disk_cache)
    String rmDiskCache;

    private Upgrade upgrade;

    @Override
    protected void initCreate(@Nullable Bundle savedInstanceState) {
        mCustomToolBar.setCenterTvText(R.string.about_application);
        mVersion.setText(String.format(aboutVersion, BuildConfig.VERSION_NAME));
        mDiskCache.setText(String.format(rmDiskCache, ImageLoaderUtils.getDiskCacheSize(this)));
    }

    @Override
    protected MVPresenterImpl initPresenter() {
        return null;
    }

    @Override
    protected void onStatusRetry() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected boolean showToolBar() {
        return true;
    }

    @OnClick(R.id.tv_upgrade)
    public void onUpgrade() {
        if (upgrade == null) {
            upgrade = Upgrade.getInstance(this);
        }
        upgrade
                .setType(Upgrade.TYPE_CLICK)
                .setIcon(R.drawable.ic_launcher)
                .setApkName(BridgeConstant.UPGRADE_NAME)
                .setSoftwareId(BridgeConstant.UPGRADE_ID)
                .setPackageName(BridgeConstant.MASTER_ID)
                .setUpgradeCallback(new UpgradeCallback() {
                    @Override
                    public void onUpgradeStart() {
                        showLoading();
                    }

                    @Override
                    public void onUpgradeSuccess(VersionUploadEntity versionUploadEntity) {
                        hideLoading();
                    }

                    @Override
                    public void onUpgradeError() {
                        hideLoading();
                    }

                    @Override
                    public void onDownloadSuccess(VersionUploadEntity versionUploadEntity) {
                    }
                }).start();
    }

    @OnClick(R.id.tv_remove_disk_cache)
    public void onRemoveDiskCacheClicked() {
        ImageLoaderUtils.clearImageDiskCache(this);
        showLoading();
        new Handler().postDelayed(() -> {
            mDiskCache.setText(String.format(rmDiskCache, ImageLoaderUtils.getDiskCacheSize(this)));
            hideLoading();
        }, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (upgrade != null) {
            upgrade.onDestroy();
        }
    }
}
