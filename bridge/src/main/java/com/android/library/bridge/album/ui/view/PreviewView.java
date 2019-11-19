package com.android.library.bridge.album.ui.view;

import android.app.Activity;

import com.android.library.bridge.album.entity.AlbumEntity;

import java.util.ArrayList;

public interface PreviewView {

    void scanSuccess(ArrayList<AlbumEntity> albumEntityList);

    Activity getPreviewActivity();

    void hideProgress();

    void showProgress();

}
