package com.android.library.bridge.album.ui.view;

import android.app.Activity;

import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;

import java.util.ArrayList;

public interface AlbumView {

    void showProgress();

    void hideProgress();

    void scanSuccess(ArrayList<AlbumEntity> albumEntityList);

    void scanFinder(ArrayList<FinderEntity> list);

    ArrayList<AlbumEntity> getSelectEntity();

    Activity getAlbumActivity();

    void onAlbumNoMore();

    void resultSuccess(AlbumEntity albumEntity);
}
