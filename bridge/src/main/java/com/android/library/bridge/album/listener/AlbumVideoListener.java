package com.android.library.bridge.album.listener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * by y on 28/08/2017.
 * <p>
 * customize video ui
 */

public interface AlbumVideoListener {
    /**
     * 自定义相机
     *
     * @param fragment {@link com.android.library.bridge.album.ui.fragment.AlbumFragment}
     */
    void startVideo(@NonNull Fragment fragment);
}
