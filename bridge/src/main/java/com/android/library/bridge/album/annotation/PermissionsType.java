package com.android.library.bridge.album.annotation;

import androidx.annotation.IntDef;

import com.android.library.bridge.album.AlbumConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * by y on 22/08/2017.
 */

@IntDef({AlbumConstant.TYPE_PERMISSIONS_ALBUM, AlbumConstant.TYPE_PERMISSIONS_CAMERA})
@Retention(RetentionPolicy.SOURCE)
public @interface PermissionsType {
}
