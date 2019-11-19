package com.android.library.bridge.album.presenter;


import com.android.library.bridge.album.entity.AlbumEntity;

import java.util.ArrayList;
import java.util.List;

public interface PreviewPresenter {
    void scan(String bucketId, int page, int count);

    void mergeEntity(List<AlbumEntity> albumEntityList, ArrayList<AlbumEntity> selectAlbumEntityList);

}
