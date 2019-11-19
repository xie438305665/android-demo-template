package com.android.library.bridge.album.ui.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.listener.AlbumListener;

import java.io.File;
import java.util.List;

/**
 * Album 响应
 */

public class SimpleAlbumListener implements AlbumListener {

    @Override
    public void onAlbumActivityFinish() {

    }

    @Override
    public void onAlbumPermissionsDenied(int type) {

    }

    @Override
    public void onAlbumFragmentNull() {

    }

    @Override
    public void onAlbumPreviewFileNull() {

    }

    @Override
    public void onAlbumFinderNull() {

    }

    @Override
    public void onAlbumBottomPreviewNull() {

    }

    @Override
    public void onAlbumBottomSelectNull() {

    }

    @Override
    public void onAlbumFragmentFileNull() {

    }

    @Override
    public void onAlbumPreviewSelectNull() {

    }

    @Override
    public void onAlbumCheckBoxFileNull() {

    }

    @Override
    public void onAlbumFragmentCropCanceled() {

    }

    @Override
    public void onAlbumFragmentCameraCanceled() {

    }

    @Override
    public void onAlbumFragmentUCropError(@Nullable Throwable data) {

    }

    @Override
    public void onAlbumResources(@NonNull List<AlbumEntity> list) {

    }

    @Override
    public void onAlbumUCropResources(@Nullable File scannerFile) {

    }

    @Override
    public void onAlbumMaxCount() {

    }

    @Override
    public void onAlbumActivityBackPressed() {

    }

    @Override
    public void onAlbumOpenCameraError() {

    }

    @Override
    public void onAlbumEmpty() {

    }

    @Override
    public void onAlbumNoMore() {

    }

    @Override
    public void onAlbumResultCameraError() {

    }

    @Override
    public void onVideoPlayError() {

    }
}
