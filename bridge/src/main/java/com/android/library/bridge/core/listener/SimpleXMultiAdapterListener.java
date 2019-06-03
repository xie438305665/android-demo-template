package com.android.library.bridge.core.listener;

import android.support.v7.widget.GridLayoutManager;

import com.xadapter.adapter.multi.XMultiAdapterListener;

/**
 * @author xcl
 * @create 2019/1/3
 */
public abstract class SimpleXMultiAdapterListener<T> implements XMultiAdapterListener<T> {

    @Override
    public int getGridLayoutManagerSpanSize(int itemViewType, GridLayoutManager gridManager, int position) {
        return 0;
    }

    @Override
    public boolean getStaggeredGridLayoutManagerFullSpan(int itemViewType) {
        return false;
    }
}
