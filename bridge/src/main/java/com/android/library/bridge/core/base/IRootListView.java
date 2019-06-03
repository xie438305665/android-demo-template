package com.android.library.bridge.core.base;

import java.util.List;

/**
 * @author xcl
 */
public interface IRootListView<T> extends IView<List<T>> {
    void onRemoveAll();

    void onNetComplete(int type);

    void onNetError(int type);

    void onLoadNoMore();

    void onPagePlus();

    int getPage();
}
