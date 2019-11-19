package com.android.library.bridge.album.ui.view;

import android.net.Uri;
import android.os.Bundle;

import com.android.library.bridge.album.annotation.AlbumResultType;
import com.android.library.bridge.album.entity.FinderEntity;

import java.util.List;

public interface AlbumMethodFragmentView {

    void initRecyclerView();

    void disconnectMediaScanner();

    void onScanAlbum(String bucketId, boolean isFinder, boolean result);

    void openCamera();

    void openUCrop(String path, Uri uri);

    void refreshMedia(@AlbumResultType int type);

    List<FinderEntity> getFinderEntity();

    void multiplePreview();

    void multipleSelect();

    void onResultPreview(Bundle bundle);
}
