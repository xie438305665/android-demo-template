package com.android.library.bridge.album.ui.widget;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.android.library.bridge.R;
import com.android.library.bridge.album.annotation.FrescoType;
import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;
import com.android.library.bridge.album.listener.AlbumImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Album Glide图片加载
 */

public class SimpleGlideAlbumImageLoader implements AlbumImageLoader {

    private RequestOptions requestOptions;

    public SimpleGlideAlbumImageLoader() {
        requestOptions = new RequestOptions()
                .placeholder(R.drawable.album_ic_launcher_background)
                .error(R.drawable.album_ic_launcher_background)
                .centerCrop();
    }

    @Override
    public void displayAlbum(@NonNull ImageView view, int width, int height, @NonNull AlbumEntity albumEntity) {
        Glide
                .with(view.getContext())
                .load(albumEntity.getPath())
                .apply(requestOptions.override(width, height))
                .into(view);
    }

    @Override
    public void displayAlbumThumbnails(@NonNull ImageView view, @NonNull FinderEntity finderEntity) {
        Glide
                .with(view.getContext())
                .load(finderEntity.getThumbnailsPath()).apply(requestOptions)
                .into(view);
    }

    @Override
    public void displayPreview(@NonNull ImageView view, @NonNull AlbumEntity albumEntity) {
        Glide
                .with(view.getContext())
                .load(albumEntity.getPath()).apply(requestOptions)
                .into(view);
    }

    @Override
    public ImageView frescoView(@NonNull Context context, @FrescoType int type) {
        return null;
    }

}
