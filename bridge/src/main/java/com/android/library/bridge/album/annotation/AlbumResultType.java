package com.android.library.bridge.album.annotation;

import androidx.annotation.IntDef;

import com.android.library.bridge.album.AlbumConstant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * by y on 22/08/2017.
 */

@IntDef({AlbumConstant.TYPE_RESULT_CAMERA, AlbumConstant.TYPE_RESULT_CROP})
@Retention(RetentionPolicy.SOURCE)
public @interface AlbumResultType {
}
