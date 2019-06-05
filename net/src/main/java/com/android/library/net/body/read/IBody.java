package com.android.library.net.body.read;

import android.util.Log;

import com.android.library.net.BuildConfig;

/**
 * @author y
 * @create 2019/4/8
 */
public interface IBody {

    String TAG = "NetBody";

    default void Log() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, toString());
        }
    }

}
