package com.android.library.bridge.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xadapter.adapter.XRecyclerViewAdapter;
import com.xadapter.widget.SimpleLoadMore;
import com.xadapter.widget.XLoadMoreView;
import com.xadapter.widget.XRefreshView;

/**
 * @author xcl
 */
public class ListAdapter<T> extends XRecyclerViewAdapter<T> {

    public static final int TYPE_STATUS = 0;
    public static final int TYPE_REFRESH = 1;
    public static final int TYPE_LOAD_MORE = 2;

    private XLoadMoreView xLoadMoreView;

    @Override
    public XRecyclerViewAdapter<T> addRecyclerView(@NonNull RecyclerView recyclerView) {
        xLoadMoreView = new SimpleLoadMore(recyclerView.getContext());
        setLoadMoreView(xLoadMoreView);
        return super.addRecyclerView(recyclerView);
    }

    @Override
    public void onScrollBottom() {
        if (getLoadMoreState() != XLoadMoreView.LOAD) {
            super.onScrollBottom();
        }
    }

    @Override
    public void refreshState(int state) {
        super.refreshState(state);
        if (getRefreshState() == XRefreshView.ERROR) {
            xLoadMoreView.hideHeight(false);
        }
    }

    public void onComplete(int type) {
        if (type == TYPE_REFRESH) {
            refreshState(XRefreshView.SUCCESS);
        } else {
            loadMoreState(XLoadMoreView.SUCCESS);
        }
    }

    public void onError(int type) {
        if (type == TYPE_REFRESH) {
            refreshState(XRefreshView.ERROR);
        } else {
            loadMoreState(XLoadMoreView.ERROR);
        }
    }


    @Override
    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void loadNoMore() {
        loadMoreState(XLoadMoreView.NOMORE);
    }

    public ListAdapter<T> onLoadMoreRetry(XLoadMoreRetryListener loadMoreRetryListener) {
        setFooterListener(view -> {
            if (getLoadMoreState() == XLoadMoreView.ERROR) {
                loadMoreState(XLoadMoreView.LOAD);
                loadMoreRetryListener.onLoadMoreRetry();
            }
        });
        return this;
    }
}
