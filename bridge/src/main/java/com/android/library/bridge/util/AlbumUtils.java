package com.android.library.bridge.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.library.bridge.R;
import com.android.library.bridge.album.Album;
import com.android.library.bridge.album.AlbumConfig;
import com.android.library.bridge.album.AlbumConstant;
import com.android.library.bridge.album.annotation.FrescoType;
import com.android.library.bridge.album.annotation.PermissionsType;
import com.android.library.bridge.album.entity.AlbumEntity;
import com.android.library.bridge.album.entity.FinderEntity;
import com.android.library.bridge.album.listener.AlbumImageLoader;
import com.android.library.bridge.album.listener.AlbumListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

public class AlbumUtils {

    private static AlbumConfig CONFIG = new AlbumConfig(AlbumConstant.TYPE_NIGHT)
            .setHideCamera(false)
            .setPermissionsDeniedFinish(true)
            .setPermissionsDeniedFinish(false)
            .setPreviewFinishRefresh(true)
            .setPreviewBackRefresh(true);

    public static void openFeedbackAlbum(AppCompatActivity activity, SimpleListener simpleListener) {
        resetAlbum();
        Album
                .getInstance()
                .setAlbumListener(simpleListener)
                .setConfig(CONFIG.setRadio(false).setMultipleMaxCount(1).setHideCamera(false))
                .start(activity);
    }

    @SuppressWarnings("ConstantConditions")
    public static void resetAlbum() {
        Album
                .getInstance()
                .setAlbumListener(null);
    }

    public abstract static class SimpleListener implements AlbumListener {

        @Override
        public void onAlbumActivityFinish() {
        }

        @Override
        public void onAlbumPermissionsDenied(@PermissionsType int type) {
            UIUtils.show(R.string.permissions_denied);
        }

        @Override
        public void onAlbumFragmentNull() {
        }

        @Override
        public void onAlbumPreviewFileNull() {
        }

        @Override
        public void onAlbumFinderNull() {
        }

        @Override
        public void onAlbumBottomPreviewNull() {
        }

        @Override
        public void onAlbumBottomSelectNull() {
        }

        @Override
        public void onAlbumFragmentFileNull() {
        }

        @Override
        public void onAlbumPreviewSelectNull() {
        }

        @Override
        public void onAlbumCheckBoxFileNull() {
        }

        @Override
        public void onAlbumFragmentCropCanceled() {
        }

        @Override
        public void onAlbumFragmentCameraCanceled() {
        }

        @Override
        public void onAlbumFragmentUCropError(@Nullable Throwable data) {
        }

        @Override
        public void onAlbumUCropResources(@Nullable File scannerFile) {
        }

        @Override
        public void onAlbumMaxCount() {
            UIUtils.show(R.string.album_max_count);
        }

        @Override
        public void onAlbumActivityBackPressed() {
        }

        @Override
        public void onAlbumOpenCameraError() {
        }

        @Override
        public void onAlbumEmpty() {
            UIUtils.show(R.string.album_empty);
        }

        @Override
        public void onAlbumNoMore() {
        }

        @Override
        public void onAlbumResultCameraError() {
        }

        @Override
        public void onVideoPlayError() {
        }
    }

    public static class SimpleGlide4xAlbumImageLoader implements AlbumImageLoader {

        private RequestOptions requestOptions;

        public SimpleGlide4xAlbumImageLoader() {
            requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_album_default_loading)
                    .error(R.drawable.ic_album_default_loading)
                    .centerCrop();
        }

        @Override
        public void displayAlbum(@NonNull ImageView view, int width, int height, @NonNull AlbumEntity albumModel) {
            Glide
                    .with(view.getContext())
                    .load(albumModel.getPath())
                    .apply(requestOptions.override(width, height))
                    .into(view);
        }

        @Override
        public void displayAlbumThumbnails(@NonNull ImageView view, @NonNull FinderEntity finderModel) {
            Glide
                    .with(view.getContext())
                    .load(finderModel.getThumbnailsPath()).apply(requestOptions)
                    .into(view);
        }

        @Override
        public void displayPreview(@NonNull ImageView view, @NonNull AlbumEntity albumModel) {
            Glide
                    .with(view.getContext())
                    .load(albumModel.getPath()).apply(requestOptions)
                    .into(view);
        }

        @Override
        public ImageView frescoView(@NonNull Context context, @FrescoType int type) {
            return null;
        }
    }
}
