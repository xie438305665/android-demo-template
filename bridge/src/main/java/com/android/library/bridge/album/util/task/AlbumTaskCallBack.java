package com.android.library.bridge.album.util.task;

/**
 * HandlerThread 回调
 */

public interface AlbumTaskCallBack {
    void start(Call call);

    void quit();

    interface Call {
        void start();
    }
}
