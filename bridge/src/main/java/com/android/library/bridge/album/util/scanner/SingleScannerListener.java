package com.android.library.bridge.album.util.scanner;

import com.android.library.bridge.album.annotation.AlbumResultType;

public interface SingleScannerListener {
    @SuppressWarnings("EmptyMethod")
    void onScanStart();

    void onScanCompleted(@AlbumResultType int type);
}