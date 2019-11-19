package com.android.library.bridge.album.listener;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.android.library.bridge.album.annotation.FrescoType;
import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;

/**
 * 图片加载
 */

public interface AlbumImageLoader {

    void displayAlbum(@NonNull ImageView view, int width, int height, @NonNull AlbumEntity albumEntity);

    void displayAlbumThumbnails(@NonNull ImageView view, @NonNull FinderEntity finderEntity);

    void displayPreview(@NonNull ImageView view, @NonNull AlbumEntity albumEntity);

    ImageView frescoView(@NonNull Context context, @FrescoType int type);
}
