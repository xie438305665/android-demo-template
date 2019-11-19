package com.android.library.bridge.album.listener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.library.bridge.album.annotation.PermissionsType;
import com.android.library.bridge.album.entity.AlbumEntity;

import java.io.File;
import java.util.List;

/**
 * Album 回调
 */

public interface AlbumListener {
    void onAlbumActivityFinish();

    void onAlbumPermissionsDenied(@PermissionsType int type);

    void onAlbumFragmentNull();

    void onAlbumPreviewFileNull();

    void onAlbumFinderNull();

    void onAlbumBottomPreviewNull();

    void onAlbumBottomSelectNull();

    void onAlbumFragmentFileNull();

    void onAlbumPreviewSelectNull();

    void onAlbumCheckBoxFileNull();

    void onAlbumFragmentCropCanceled();

    void onAlbumFragmentCameraCanceled();

    void onAlbumFragmentUCropError(@Nullable Throwable data);

    void onAlbumResources(@NonNull List<AlbumEntity> list);

    void onAlbumUCropResources(@Nullable File scannerFile);

    void onAlbumMaxCount();

    void onAlbumActivityBackPressed();

    void onAlbumOpenCameraError();

    void onAlbumEmpty();

    void onAlbumNoMore();

    void onAlbumResultCameraError();

    void onVideoPlayError();
}
