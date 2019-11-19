package com.android.library.bridge.album.listener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * 自定义相机
 */

public interface AlbumCameraListener {

    /**
     * 自定义相机
     *
     * @param fragment {@link com.android.library.bridge.album.ui.fragment.AlbumFragment}
     */
    void startCamera(@NonNull Fragment fragment);
}
