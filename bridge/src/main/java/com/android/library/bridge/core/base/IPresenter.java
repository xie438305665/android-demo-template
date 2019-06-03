package com.android.library.bridge.core.base;

import android.arch.lifecycle.DefaultLifecycleObserver;

/**
 * @author xcl
 */
public interface IPresenter extends DefaultLifecycleObserver {
    void onDestroy();
}
