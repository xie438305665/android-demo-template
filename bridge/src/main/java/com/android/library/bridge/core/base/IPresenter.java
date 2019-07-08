package com.android.library.bridge.core.base;

import androidx.lifecycle.DefaultLifecycleObserver;

/**
 * @author xcl
 */
public interface IPresenter extends DefaultLifecycleObserver {
    void onDestroy();
}
