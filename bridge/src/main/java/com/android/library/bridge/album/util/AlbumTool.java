package com.android.library.bridge.album.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.library.bridge.album.AlbumConstant;

/**
 * AlbumTool
 */
public class AlbumTool {
    private AlbumTool() {
    }

    public static boolean hasL() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static Drawable getDrawable(Context context, int id, int color) {
        Drawable drawable = context.getResources().getDrawable(id);
        drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    public static int openCamera(Object activity, Uri cameraUri, boolean video) {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        Activity cameraActivity = null;
        if (activity instanceof Activity) {
            cameraActivity = (Activity) activity;
        }
        if (activity instanceof Fragment) {
            Fragment fragment = (Fragment) activity;
            cameraActivity = fragment.getActivity();
        }
        if (PermissionUtils.camera(cameraActivity) && PermissionUtils.storage(cameraActivity)) {
            Intent intent = video ? new Intent(MediaStore.ACTION_VIDEO_CAPTURE) : new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            assert cameraActivity != null;
            if (intent.resolveActivity(cameraActivity.getPackageManager()) != null) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
                } else {
                    ContentValues contentValues = new ContentValues(1);
                    contentValues.put(MediaStore.Images.Media.DATA, cameraUri.getPath());
                    contentValues.put(MediaStore.Images.Media.MIME_TYPE, AlbumConstant.CAMERA_IMAGE_TYPE);
                    Uri uri = cameraActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                if (activity instanceof Activity) {
                    cameraActivity.startActivityForResult(intent, AlbumConstant.ITEM_CAMERA);
                }
                if (activity instanceof Fragment) {
                    Fragment fragment = (Fragment) activity;
                    fragment.startActivityForResult(intent, AlbumConstant.ITEM_CAMERA);
                }
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    public static void setStatusBarColor(@ColorInt int color, Window window) {
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(color);
            }
        }
    }

    public static int getImageViewWidth(Activity activity, int count) {
        Display display = activity.getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.widthPixels / count;
    }
}
