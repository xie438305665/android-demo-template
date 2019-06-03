package com.android.library.bridge.core.base;

/**
 * @author xcl
 */
public interface IRefreshView<T> extends IView<T> {
    void onRemoveAll();

    void onRefreshState(int state);
}
