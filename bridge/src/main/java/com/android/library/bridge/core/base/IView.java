package com.android.library.bridge.core.base;

/**
 * @param <T>
 * @author xcl
 */
public interface IView<T> extends IRootView {

    void showProgress();

    void hideProgress();

    void onViewSuccess(T entity);

    void onViewError(Throwable throwable);
}
