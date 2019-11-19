package com.android.library.bridge.album.presenter;

import com.android.library.bridge.album.entity.AlbumEntity;

import java.util.ArrayList;

public interface AlbumPresenter {
    void scan(String bucketId, int page, int count);

    void mergeEntity(ArrayList<AlbumEntity> albumList, ArrayList<AlbumEntity> multiplePreviewList);

    void firstMergeEntity(ArrayList<AlbumEntity> albumEntityList, ArrayList<AlbumEntity> selectEntity);

    boolean isScan();

    void resultScan(String path);
}
