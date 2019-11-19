package com.android.library.bridge.album.ui.widget;

import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;

import java.util.ArrayList;

public interface ScanCallBack {
    void scanSuccess(ArrayList<AlbumEntity> albumEntityList, ArrayList<FinderEntity> list);

    void resultSuccess(AlbumEntity albumEntity, ArrayList<FinderEntity> finderEntityList);
}
