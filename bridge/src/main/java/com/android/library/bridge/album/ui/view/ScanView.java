package com.android.library.bridge.album.ui.view;

import android.database.Cursor;

import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;
import com.android.library.bridge.album.ui.widget.ScanCallBack;

import java.util.ArrayList;

public interface ScanView {

    void start(ScanCallBack scanCallBack, String bucketId, int page, int count);

    void resultScan(ScanCallBack scanCallBack, String path);

    void scanCursor(ArrayList<AlbumEntity> albumEntityList, int dataColumnIndex, int idColumnIndex, int sizeColumnIndex, Cursor cursor);

    void cursorFinder(ArrayList<FinderEntity> finderEntityList);

    int cursorCount(String bucketId);

    Cursor getCursor(String bucketId, int page, int count);

    String[] getSelectionArgs(String bucketId);

}
