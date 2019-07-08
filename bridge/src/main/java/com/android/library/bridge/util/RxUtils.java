package com.android.library.bridge.util;

import androidx.annotation.Nullable;

import io.reactivex.disposables.Disposable;

/**
 * @author xcl
 * @create 2019/3/29
 */
public class RxUtils {

    public static void dispose(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
