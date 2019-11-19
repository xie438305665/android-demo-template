package com.android.library.bridge.album.presenter.impl;

import android.content.ContentResolver;
import android.text.TextUtils;

import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;
import com.android.library.bridge.album.presenter.PreviewPresenter;
import com.android.library.bridge.album.ui.view.PreviewView;
import com.android.library.bridge.album.ui.view.ScanView;
import com.android.library.bridge.album.ui.widget.ScanCallBack;
import com.android.library.bridge.album.util.scan.AlbumScanUtils;
import com.android.library.bridge.album.util.scan.VideoScanUtils;
import com.android.library.bridge.album.util.task.AlbumTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Preview P层
 */

public class PreviewPresenterImpl implements PreviewPresenter, ScanCallBack {
    private final PreviewView previewView;
    private final ScanView scanView;

    public PreviewPresenterImpl(PreviewView previewView, boolean isVideo) {
        this.previewView = previewView;
        ContentResolver contentResolver = previewView.getPreviewActivity().getContentResolver();
        scanView = isVideo ? VideoScanUtils.get(contentResolver) : AlbumScanUtils.get(contentResolver);
    }

    @Override
    public void scan(final String bucketId, final int page, final int count) {
        previewView.getPreviewActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                previewView.showProgress();
            }
        });
        AlbumTask.get().start(() -> scanView.start(PreviewPresenterImpl.this, bucketId, page, count));
    }

    @Override
    public void mergeEntity(List<AlbumEntity> albumEntityList, ArrayList<AlbumEntity> selectAlbumEntityList) {
        if (albumEntityList == null || selectAlbumEntityList == null) {
            return;
        }
        for (AlbumEntity albumEntity : selectAlbumEntityList) {
            String path = albumEntity.getPath();
            for (AlbumEntity allAlbumEntity : albumEntityList) {
                String allEntityPath = allAlbumEntity.getPath();
                if (TextUtils.equals(path, allEntityPath)) {
                    allAlbumEntity.setCheck(albumEntity.isCheck());
                }
            }
        }
    }


    @Override
    public void scanSuccess(final ArrayList<AlbumEntity> albumEntityList, ArrayList<FinderEntity> list) {
        previewView.getPreviewActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                previewView.hideProgress();
                previewView.scanSuccess(albumEntityList);
            }
        });
    }

    @Override
    public void resultSuccess(AlbumEntity albumEntity, ArrayList<FinderEntity> finderEntityList) {

    }

}
